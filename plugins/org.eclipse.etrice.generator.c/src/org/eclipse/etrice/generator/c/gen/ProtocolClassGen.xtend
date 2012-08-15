/*******************************************************************************
 * Copyright (c) 2011 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 * 		Thomas Schuetz (changed for C code generator)
 * 
 *******************************************************************************/

package org.eclipse.etrice.generator.c.gen

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.etrice.core.room.ProtocolClass
import org.eclipse.etrice.core.room.CommunicationType
import org.eclipse.etrice.core.room.PrimitiveType
import org.eclipse.etrice.core.genmodel.base.ILogger
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.xtext.generator.JavaIoFileSystemAccess

import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.TypeHelpers
import org.eclipse.etrice.generator.generic.GenericProtocolClassGenerator


@Singleton
class ProtocolClassGen extends GenericProtocolClassGenerator {

	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions
	@Inject extension RoomExtensions
	@Inject extension ProcedureHelpers
	@Inject extension TypeHelpers
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (pc: root.usedProtocolClasses) {
			var path = pc.generationTargetPath+pc.getPath

			logger.logInfo("generating ProtocolClass header '"+pc.getCHeaderFileName+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(pc.getCHeaderFileName, root.generateHeaderFile(pc))

			logger.logInfo("generating ProtocolClass source '"+pc.getCSourceFileName+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(pc.getCSourceFileName, root.generateSourceFile(pc))
		}
	}

	
	def private generateHeaderFile(Root root, ProtocolClass pc) {'''
		/**
		 * @author generated by eTrice
		 *
		 * Header File of ProtocolClass �pc.name�
		 * 
		 */

		�generateIncludeGuardBegin(pc.name)�
		
		#include "etDatatypes.h"
		#include "modelbase/etPort.h"
		
		�pc.userCode(1)�
		
		�FOR dataClass : root.getReferencedDataClasses(pc)�
			#include "�dataClass.name�.h"
		�ENDFOR�
		
		�IF pc.commType==CommunicationType::EVENT_DRIVEN�
			
			/* message IDs */
			�genMessageIDs(pc)�
			
			/*--------------------- port structs and methods */
			�portClassHeader(pc, false)�
			�portClassHeader(pc, true)�
		�ELSEIF pc.commType==CommunicationType::DATA_DRIVEN�
			/*--------------------- port structs and methods */
			�pc.genDataDrivenPortHeaders�
		�ELSEIF pc.commType==CommunicationType::SYNCHRONOUS�
			#error "synchronoue protocols not implemented yet"
		�ENDIF�
���			�portClass(pc, false)�
���			�portClass(pc, true)�

		/*--------------------- debug helpers */
		
		/* get message string for message id */
		const char* �pc.name�_getMessageString(int msg_id);

		�pc.userCode(2)�
		
		�generateIncludeGuardEnd(pc.name)�
		
	'''
	}
	
	def private generateSourceFile(Root root, ProtocolClass pc) {'''
		/**
		 * @author generated by eTrice
		 *
		 * Source File of ProtocolClass �pc.name�
		 * 
		 */

		#include "�pc.getCHeaderFileName�"
		#include "debugging/etMSCLogger.h"

		�pc.userCode(3)�
		
		/*--------------------- port methods */
		�IF pc.commType==CommunicationType::EVENT_DRIVEN�
			�portClassSource(pc, false)�
			�portClassSource(pc, true)�
			
			/*--------------------- debug helpers */
			�generateDebugHelpersImplementation(root, pc)�
		�ELSEIF pc.commType==CommunicationType::DATA_DRIVEN�
			�pc.genDataDrivenPortSources�
		�ELSEIF pc.commType==CommunicationType::SYNCHRONOUS�
			#error "synchronous protocols not implemented yet"
		�ENDIF�
	'''
	}
	
	def private portClassHeader(ProtocolClass pc, Boolean conj){
		var portClassName = pc.getPortClassName(conj)
		var replPortClassName = pc.getPortClassName(conj, true)
		var messages = if (conj) pc.allIncomingMessages else pc.allOutgoingMessages
		
		'''
		typedef etPort �portClassName�;
		typedef etReplPort �replPortClassName�;
		
		�IF pc.getPortClass(conj)!=null�	
			�IF !(pc.getPortClass(conj).attributes.empty)�
				/* variable part of PortClass (RAM) */
				typedef struct �portClassName�_var �portClassName�_var; 
				struct �portClassName�_var {
					�pc.getPortClass(conj).attributes.attributes�
					};
				�FOR a:pc.getPortClass(conj).attributes�
					�IF a.defaultValueLiteral!=null�
						�logger.logInfo(portClassName+" "+a.name+": Attribute initialization not supported in C")�
					�ENDIF�
				�ENDFOR�
			�ENDIF�
		�ENDIF�
		
		�FOR message : messages�
			�var hasData = message.data!=null�
			�var typeName = if (hasData) message.data.refType.type.typeName else ""�
			�var refp = if (hasData && (!(message.data.refType.type instanceof PrimitiveType)||(message.data.refType.ref))) "*" else ""�
			�var data = if (hasData) ", "+typeName+refp+" data" else ""�
			�messageSignature(portClassName, message.name, "", data)�;
			�messageSignature(replPortClassName, message.name, "_broadcast", data)�;
			�messageSignature(replPortClassName, message.name, "", ", int idx"+data)�;
		�ENDFOR�
		
		�IF (pc.getPortClass(conj) != null)�	
			�pc.getPortClass(conj).operations.operationsDeclaration(portClassName)�
			�pc.getPortClass(conj).operations.operationsDeclaration(replPortClassName)�
		�ENDIF�
		
		�IF pc.handlesReceive(conj)�
			�FOR h:getReceiveHandlers(pc,conj)�
				void �portClassName�_�h.msg.name�_receiveHandler(�portClassName�* self, const etMessage* msg, void * actor, etActorReceiveMessage receiveMessageFunc);
			�ENDFOR�
		�ENDIF�
		etInt32 �replPortClassName�_getReplication(const �replPortClassName�* self);
		'''
	}


	
	def private genDataDrivenPortHeaders(ProtocolClass pc) {
		var sentMsgs = pc.allIncomingMessages.filter(m|m.data!=null)
		
		'''
			/* data driven send port (conjugated) */
			typedef struct {
				�FOR msg : sentMsgs�
					�var typeName = msg.data.refType.type.typeName�
					�var refp = if (msg.data.refType.ref) "*" else ""�
					�typeName��refp� �msg.name�;
				�ENDFOR�
			}
			�pc.getPortClassName(true)�;
			
			/* data driven receive port (regular) */
			typedef struct {
				const �pc.getPortClassName(true)�* peer;
			}
			�pc.getPortClassName(false)�;
			
			�FOR message : sentMsgs�
				�var hasData = message.data!=null�
				�var typeName = if (hasData) message.data.refType.type.typeName else ""�
				�var refp = if (hasData && !(message.data.refType.type instanceof PrimitiveType)) "*" else ""�
				�var data = if (hasData) ", "+typeName+refp+" data" else ""�
				�messageSetterSignature(pc.getPortClassName(true), message.name, data)�;
				�messageGetterSignature(pc.getPortClassName(false), message.name, typeName)�;
				
			�ENDFOR�
		'''
	}
	def private genDataDrivenPortSources(ProtocolClass pc) {
		var messages = pc.allIncomingMessages.filter(m|m.data!=null)
	'''
			�FOR message : messages�
				�var typeName =message.data.refType.type.typeName�
				�var refp = if (!(message.data.refType.type instanceof PrimitiveType)) "*" else ""�
				�var data = ", "+typeName+refp+" data"�
				�messageSetterSignature(pc.getPortClassName(true), message.name, data)� {
					self->�message.name� = data;
				}
				�messageGetterSignature(pc.getPortClassName(false), message.name, typeName)� {
					return self->peer->�message.name�;
				}
				
			�ENDFOR�
	'''
	}
	
	def private portClassSource(ProtocolClass pc, Boolean conj){
		var portClassName = pc.getPortClassName(conj)
		var replPortClassName = pc.getPortClassName(conj, true)
		var messages = if (conj) pc.allIncomingMessages else pc.allOutgoingMessages
		var dir = if (conj) "IN_" else "OUT_"

		'''
			�FOR message : messages�
				�var hasData = message.data!=null�
				�var typeName = if (hasData) message.data.refType.type.typeName else ""�
				�var refp = if (hasData && ((message.data.refType.ref))) "*" else ""�
				�var refpd = if (hasData && (!(message.data.refType.type instanceof PrimitiveType)||(message.data.refType.ref))) "*" else ""�
				�var refa = if (hasData && (!(message.data.refType.type instanceof PrimitiveType))&&(!(message.data.refType.ref))) "" else "&"�
				�var data = if (hasData) ", "+typeName+refpd+" data" else ""�
				�var dataCall = if (hasData) ", data" else ""�
				�var hdlr = message.getSendHandler(conj)�
				
				�messageSignature(portClassName, message.name, "", data)� {
					�IF hdlr != null�
						�FOR command : hdlr.detailCode.commands�	�command�
						�ENDFOR�									
					�ELSE�
						ET_MSC_LOGGER_SYNC_ENTRY("�portClassName�", "�message.name�")
							�sendMessageCall(hasData, "self", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
				
				�messageSignature(replPortClassName, message.name, "_broadcast", data)� {
					�IF hdlr != null�
						int i;
						for (i=0; i<((etReplPort*)self)->size; ++i) {
							�portClassName�_�message.name�((etPort*)&((etReplPort*)self)->ports[i]�dataCall�);
						}					
					�ELSE�
						int i;
						ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
						for (i=0; i<((etReplPort*)self)->size; ++i) {
							�sendMessageCall(hasData, "((etPort*)&((etReplPort*)self)->ports[i])", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
						}
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
				
				�messageSignature(replPortClassName, message.name, "", ", int idx"+data)� {
					�IF hdlr != null�
						�portClassName�_�message.name�((etPort*)&((etReplPort*)self)->ports[idx]�dataCall�);
					�ELSE�					
						ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
						if (0<=idx && idx<((etReplPort*)self)->size) {
							�sendMessageCall(hasData, "((etPort*)&((etReplPort*)self)->ports[idx])", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
						}
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
			�ENDFOR�
			
			�IF (pc.getPortClass(conj) != null)�
				�pc.getPortClass(conj).operations.operationsImplementation(portClassName)�
				�pc.getPortClass(conj).operations.operationsImplementation(replPortClassName)�
			�ENDIF�

			// getReplication
			etInt32 �replPortClassName�_getReplication(const �replPortClassName�* self) {
				return ((etReplPort*)self)->size;
			}
			
			�IF pc.handlesReceive(conj)�
				�genReceiveHandlers(pc,conj)�
			�ENDIF�
			
		'''
	}

	def private sendMessageCall(boolean hasData, String self, String msg, String typeName, String data) {
		if (hasData)
			"etPort_sendMessage("+self+", "+msg+", sizeof("+typeName+"), "+data+");"
		else
			"etPort_sendMessage("+self+", "+msg+", 0, NULL);"
	}
	
	def private messageSignature(String className, String messageName, String methodSuffix, String data) {
		"void "+className+"_"+messageName+methodSuffix+"(const "+className+"* self"+data+")"
	}
	
	def private messageSetterSignature(String className, String messageName, String data) {
		"void "+className+"_"+messageName+"_set("+className+"* self"+data+")"
	}
	
	def private messageGetterSignature(String className, String messageName, String type) {
		type+" "+className+"_"+messageName+"_get(const "+className+"* const self)"
	}
	
//	def sendMessage(Message m, boolean conj) {'''
//	�var dir = if (conj) "IN" else "OUT"�
//	�var hdlr = m.getSendHandler(conj)�
//	�messageSignature(m)�{
//		if (getPeerAddress()!=null)
//				�IF m.data==null�getPeerMsgReceiver().receive(new EventMessage(getPeerAddress(), �dir�_�m.name�));
//				�ELSE�getPeerMsgReceiver().receive(new EventWithDataMessage(getPeerAddress(), �dir�_�m.name�, �m.data.name��IF (!m.data.ref)�.deepCopy()�ENDIF�));
//			�ENDIF�
//	}
//	'''
//	}
	
	def private genReceiveHandlers(ProtocolClass pc, Boolean conj){
	var portClassName = pc.getPortClassName(conj)
	
	'''
	/* receiver handlers */
	�FOR h:getReceiveHandlers(pc,conj)�
		void �portClassName�_�h.msg.name�_receiveHandler(�portClassName�* self, const etMessage* msg, void * actor, etActorReceiveMessage receiveMessageFunc){
			�userCode(h.detailCode)�
			/* hand over the message to the actor:      */
			/* (*receiveMessageFunc)(actor, self, msg); */
		}
	�ENDFOR�
	'''}	

	
	
	def private generateDebugHelpersImplementation(Root root, ProtocolClass pc){'''
		
���		TODO: make this optional or different for smaller footprint
		/* message names as strings for debugging (generate MSC) */
		static const char* const �pc.name�_messageStrings[] = {"MIN", �FOR m : pc.getAllOutgoingMessages()�"�m.name�",�ENDFOR��FOR m : pc.getAllIncomingMessages()�"�m.name�", �ENDFOR�"MAX"};

		const char* �pc.name�_getMessageString(int msg_id) {
			if (msg_id<�pc.name�_MSG_MIN || msg_id>�pc.name�_MSG_MAX+1){
				/* id out of range */
				return "Message ID out of range";
			}
			else{
				return �pc.name�_messageStrings[msg_id];
			}
		}
		'''
	}
	
}