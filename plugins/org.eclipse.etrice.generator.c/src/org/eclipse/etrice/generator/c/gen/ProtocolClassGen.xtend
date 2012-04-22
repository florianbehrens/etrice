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
import org.eclipse.etrice.core.room.Message
import org.eclipse.etrice.core.room.ProtocolClass
import org.eclipse.etrice.core.room.CommunicationType
import org.eclipse.etrice.core.room.PrimitiveType
import org.eclipse.etrice.generator.base.ILogger
import org.eclipse.etrice.generator.etricegen.Root
import org.eclipse.xtext.generator.JavaIoFileSystemAccess

import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.TypeHelpers
import org.eclipse.etrice.generator.generic.GenericProtocolClassGenerator


@Singleton
class ProtocolClassGen extends GenericProtocolClassGenerator {

	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions stdExt
	@Inject extension RoomExtensions roomExt
	@Inject extension ProcedureHelpers helpers
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
		
		�helpers.userCode(pc.userCode1)�
		
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

		�helpers.userCode(pc.userCode2)�
		
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

		�helpers.userCode(pc.userCode3)�
		
		/*--------------------- port methods */
		�IF pc.commType==CommunicationType::EVENT_DRIVEN�
			�portClassSource(pc, false)�
			�portClassSource(pc, true)�
			
			/*--------------------- debug helpers */
			�generateDebugHelpersImplementation(root, pc)�
		�ELSEIF pc.commType==CommunicationType::DATA_DRIVEN�
			�pc.genDataDrivenPortSources�
		�ELSEIF pc.commType==CommunicationType::SYNCHRONOUS�
			#error "synchronoue protocols not implemented yet"
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
			
			�FOR message : messages�
				�var hasData = message.data!=null�
				�var typeName = if (hasData) message.data.refType.type.typeName else ""�
				�var refp = if (hasData && (!(message.data.refType.type instanceof PrimitiveType)||(message.data.refType.ref))) "*" else ""�
				�var data = if (hasData) ", "+typeName+refp+" data" else ""�
				�messageSignature(portClassName, message.name, "", data)�;
				�messageSignature(replPortClassName, message.name, "_broadcast", data)�;
				�messageSignature(replPortClassName, message.name, "", ", int idx"+data)�;
			�ENDFOR�
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
				�var refa = if ((message.data.refType.type instanceof PrimitiveType)) "&" else ""�
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
				
				�messageSignature(portClassName, message.name, "", data)� {
					ET_MSC_LOGGER_SYNC_ENTRY("�portClassName�", "�message.name�")
					if (self->receiveMessageFunc!=NULL) {
						�sendMessageCall(hasData, "self", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
					}
					ET_MSC_LOGGER_SYNC_EXIT
				}
				
				�messageSignature(replPortClassName, message.name, "_broadcast", data)� {
					int i;
					ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
					for (i=0; i<self->size; ++i) {
						�sendMessageCall(hasData, "(etPort*)(&self->ports[i])", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
					}
					ET_MSC_LOGGER_SYNC_EXIT
				}
				
				�messageSignature(replPortClassName, message.name, "", ", int idx"+data)� {
					ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
					if (0<=idx && idx<self->size) {
						�sendMessageCall(hasData, "(etPort*)(&self->ports[idx])", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data")�
					}
					ET_MSC_LOGGER_SYNC_EXIT
				}
			�ENDFOR�
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

	def private messageCall(Message m) {'''
	�m.name�(�IF m.data!=null� �m.data.name��ENDIF�)
	'''}
	
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
	
	def private generateDebugHelpersImplementation(Root root, ProtocolClass pc){'''
		
���		TODO: make this optional or different for smaller footprint
		/* message names as strings for debugging (generate MSC) */
		static const char* �pc.name�_messageStrings[] = {"MIN", �FOR m : pc.getAllOutgoingMessages()�"�m.name�",�ENDFOR��FOR m : pc.getAllIncomingMessages()�"�m.name�", �ENDFOR�"MAX"};

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