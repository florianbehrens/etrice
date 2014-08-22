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
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.fsm.base.ILogger
import org.eclipse.etrice.core.room.CommunicationType
import org.eclipse.etrice.core.room.Operation
import org.eclipse.etrice.core.room.ProtocolClass
import org.eclipse.etrice.core.room.RoomModel
import org.eclipse.etrice.generator.base.AbstractGenerator
import org.eclipse.etrice.generator.c.Main
import org.eclipse.etrice.generator.fsm.base.IGeneratorFileIo
import org.eclipse.etrice.generator.generic.GenericProtocolClassGenerator
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.generator.generic.TypeHelpers

@Singleton
class ProtocolClassGen extends GenericProtocolClassGenerator {

	@Inject IGeneratorFileIo fileIO
	@Inject extension CExtensions
	@Inject extension RoomExtensions
	@Inject extension ProcedureHelpers
	@Inject extension TypeHelpers
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (pc: root.usedProtocolClasses) {
			val path = pc.generationTargetPath+pc.getPath
			val infopath = pc.generationInfoPath+pc.getPath
			var file = pc.getCHeaderFileName

			// header file
			fileIO.generateFile("generating ProtocolClass header", path, infopath, file, root.generateHeaderFile(pc))
			
			// utils file
			file = pc.getCUtilsFileName
			fileIO.generateFile("generating ProtocolClass utils", path, infopath, file, root.generateUtilsFile(pc))

			// source file
			file = pc.getCSourceFileName
			fileIO.generateFile("generating ProtocolClass source", path, infopath, file, root.generateSourceFile(pc))
		}
	}

	
	def private generateHeaderFile(Root root, ProtocolClass pc) {'''
		/**
		 * @author generated by eTrice
		 *
		 * Header File of ProtocolClass �pc.name�
		 * 
		 */

		�generateIncludeGuardBegin(pc)�
		
		#include "etDatatypes.h"
		#include "modelbase/etPort.h"
		
		�pc.userCode(1)�
		
		�FOR protocolClass : root.getReferencedProtocolClasses(pc)�
			#include �protocolClass.includePath�
		�ENDFOR�
		�FOR dataClass : root.getReferencedDataClasses(pc)�
			#include �dataClass.includePath�
		�ENDFOR�
		�FOR enumClass : root.getReferencedEnumClasses(pc)�
			#include �enumClass.includePath�
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

		�IF Main::settings.generateMSCInstrumentation�
			/*--------------------- debug helpers */
			
			/* get message string for message id */
			const char* �pc.name�_getMessageString(int msg_id);
		�ENDIF�

		�pc.userCode(2)�
		
		�generateIncludeGuardEnd(pc)�
		
	'''
	}
	
	def private generateUtilsFile(Root root, ProtocolClass pc) {
		val filename = (pc.eContainer as RoomModel).name.replaceAll("\\.","_")+"_"+pc.name+"_Utils"
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Utils File of ProtocolClass �pc.name�
		 * 
		 */
		
		�generateIncludeGuardBegin(filename)�
		
		#include �pc.includePath�
		
		/*
		 * access macros for operations and attributes
		*/
		
		�pc.generateUtilsFile(false)�
		�pc.generateUtilsFile(true)�
		
		�generateIncludeGuardEnd(filename)�
		
	'''
	}
	
	def private generateUtilsFile(ProtocolClass pc, boolean conj) {
		val portClass = pc.getPortClass(conj)
		val portClassName = pc.getPortClassName(conj)
	'''
		�IF portClass!=null�
			/* �IF conj�conjugate�ELSE�regular�ENDIF� port class */
			
			/* operations */
			�FOR op : portClass.operations�
				�val args = op.argList�
				#define �op.name�(�args�) �portClassName�_�op.name�(self�IF !op.arguments.empty�, �args��ENDIF�)
			�ENDFOR�
			
			/* attributes */
			�FOR a : portClass.attributes�
				#define �a.name� (((�portClassName�_var*)(self->varData))->�a.name�)
			�ENDFOR�
		�ENDIF�
	'''
	}
	
	private def argList(Operation op) {
		'''�FOR a : op.arguments SEPARATOR ", "��a.name��ENDFOR�'''
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
		#include "�pc.getCUtilsFileName�"

		�pc.userCode(3)�
		
		/*--------------------- port methods */
		�IF pc.commType==CommunicationType::EVENT_DRIVEN�
			�portClassSource(pc, false)�
			�portClassSource(pc, true)�
			
			�IF Main::settings.generateMSCInstrumentation�
				/*--------------------- debug helpers */
				�generateDebugHelpersImplementation(root, pc)�
			�ENDIF�
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
			�var refp = if (hasData && (!(message.data.refType.type.enumerationOrPrimitive)||(message.data.refType.ref))) "*" else ""�
			�var data = if (hasData) ", "+typeName+refp+" data" else ""�
			�messageSignature(portClassName, message.name, "", data)�;
			�messageSignature(replPortClassName, message.name, "_broadcast", data)�;
			�messageSignature(replPortClassName, message.name, "", ", int idx"+data)�;
		�ENDFOR�
		
		�IF (pc.getPortClass(conj) != null)�	
			�pc.getPortClass(conj).operations.operationsDeclaration(portClassName)�
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
		val enumMsgs = sentMsgs.filter(m|m.data.refType.type.isEnumeration)
		val boolMsgs = sentMsgs.filter(m|m.data.refType.type.isBoolean)
		val usesMSC = Main::settings.generateMSCInstrumentation && !(enumMsgs.empty && boolMsgs.empty)
		/*
		 * MSC code is generated for all enumerations and booleans of the messages of this protocol
		 */
		'''
			/* data driven send port (conjugated) */
			typedef struct �pc.getPortClassName(true)� {
				�FOR msg : sentMsgs�
					�var typeName = msg.data.refType.type.typeName�
					�var refp = if (msg.data.refType.ref) "*" else ""�
					�typeName��refp� �msg.name�;
				�ENDFOR�
				�IF usesMSC�
					#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
						const char* instName;
						const char** peerNames;
					#endif
				�ENDIF�
			}
			�pc.getPortClassName(true)�;
			
			/* data driven receive port (regular) */
			typedef struct �pc.getPortClassName(false)� {
				const �pc.getPortClassName(true)�* peer;
				�IF usesMSC�
					#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
						const char* instName;
						�FOR msg : enumMsgs�
							�var typeName = msg.data.refType.type.typeName�
							�var refp = if (msg.data.refType.ref) "*" else ""�
							�typeName��refp� �msg.name�;
						�ENDFOR�
						�FOR msg : boolMsgs�
							�var typeName = msg.data.refType.type.typeName�
							�var refp = if (msg.data.refType.ref) "*" else ""�
							�typeName��refp� �msg.name�;
						�ENDFOR�
					#endif
				�ENDIF�
			}
			�pc.getPortClassName(false)�;
			
			�FOR message : sentMsgs�
				�var hasData = message.data!=null�
				�var typeName = if (hasData) message.data.refType.type.typeName else ""�
				�var refp = if (hasData && !(message.data.refType.type.enumerationOrPrimitive)) "*" else ""�
				�var data = if (hasData) ", "+typeName+refp+" data" else ""�
				�messageSetterSignature(pc.getPortClassName(true), message.name, data)�;
				�messageGetterSignature(pc.getPortClassName(false), message.name, typeName)�;
				
			�ENDFOR�
		'''
	}
	
	def private genDataDrivenPortSources(ProtocolClass pc) {
		var messages = pc.allIncomingMessages.filter(m|m.data!=null)
		val enumMsgs = messages.filter(m|m.data.refType.type.isEnumeration)
		val boolMsgs = messages.filter(m|m.data.refType.type.isBoolean)
		val usesMSC = Main::settings.generateMSCInstrumentation && !(enumMsgs.empty && boolMsgs.empty)
		/*
		 * MSC code is generated for all enumerations and all booleans of the messages of this protocol
		 */
	'''
			�FOR message : messages�
				�var typeName =message.data.refType.type.typeName�
				�var refp = if (!(message.data.refType.type.enumerationOrPrimitive)) "*" else ""�
				�var data = ", "+typeName+refp+" data"�
				�messageSetterSignature(pc.getPortClassName(true), message.name, data)� {
					�IF usesMSC && enumMsgs.exists(m|m==message)�
						#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
							{
								const char** peerName;
								for (peerName=self->peerNames; *peerName!=NULL; ++peerName) {
									ET_MSC_LOGGER_ASYNC_OUT(self->instName, �message.data.refType.type.name�_getLiteralName(data), *peerName)
									ET_MSC_LOGGER_ASYNC_IN(self->instName, �message.data.refType.type.name�_getLiteralName(data), *peerName)
								}
							}
						#endif
					�ENDIF�
					�IF usesMSC && boolMsgs.exists(m|m==message)�
						#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
							{
								const char** peerName;
								for (peerName=self->peerNames; *peerName!=NULL; ++peerName) {
									ET_MSC_LOGGER_ASYNC_OUT(self->instName, data?"true":"false", *peerName)
									ET_MSC_LOGGER_ASYNC_IN(self->instName, data?"true":"false", *peerName)
								}
							}
						#endif
					�ENDIF�
					self->�message.name� = �refp�data;
				}
				�messageGetterSignature(pc.getPortClassName(false), message.name, typeName)� {
					return self->peer->�message.name�;
				}
				
			�ENDFOR�
	'''
	}
	
	def private portClassSource(ProtocolClass pc, Boolean conj) {
		val pclass = pc.getPortClass(conj)
		val portClassName = pc.getPortClassName(conj)
		val replPortClassName = pc.getPortClassName(conj, true)
		val messages = if (conj) pc.allIncomingMessages else pc.allOutgoingMessages
		val dir = if (conj) "IN_" else "OUT_"

		'''
			�FOR message : messages�
				�var hasData = message.data!=null�
				�var typeName = if (hasData) message.data.refType.type.typeName else ""�
				�var refp = if (hasData && ((message.data.refType.ref))) "*" else ""�
				�var refpd = if (hasData && (!(message.data.refType.type.enumerationOrPrimitive)||(message.data.refType.ref))) "*" else ""�
				�var refa = if (hasData && (!(message.data.refType.type.enumerationOrPrimitive))&&(!(message.data.refType.ref))) "" else "&"�
				�var data = if (hasData) ", "+typeName+refpd+" data__et" else ""�
				�var dataCall = if (hasData) ", data__et" else ""�
				�var hdlr = message.getSendHandler(conj)�
				
				�messageSignature(portClassName, message.name, "", data)� {
					�IF hdlr != null�
						�AbstractGenerator::getInstance().getTranslatedCode(hdlr.detailCode)�
					�ELSE�
						ET_MSC_LOGGER_SYNC_ENTRY("�portClassName�", "�message.name�")
							�sendMessageCall(hasData, "self", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data__et")�
							�IF Main::settings.generateMSCInstrumentation�
								ET_MSC_LOGGER_ASYNC_OUT(self->myInstName, "�message.name�", self->peerInstName)
							�ENDIF�
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
				
				�messageSignature(replPortClassName, message.name, "_broadcast", data)� {
					�IF hdlr != null�
						int i;
						for (i=0; i<((etReplPort*)self)->size; ++i) {
							�portClassName�_�message.name�(&((etReplPort*)self)->ports[i].port�dataCall�);
						}					
					�ELSE�
						int i;
						ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
						for (i=0; i<((etReplPort*)self)->size; ++i) {
							�sendMessageCall(hasData, "(&((etReplPort*)self)->ports[i].port)", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data__et")�
							�IF Main::settings.generateMSCInstrumentation�
								ET_MSC_LOGGER_ASYNC_OUT(((etReplPort*)self)->ports[i].port.myInstName, "�message.name�", ((etReplPort*)self)->ports[i].port.peerInstName)
							�ENDIF�
						}
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
				
				�messageSignature(replPortClassName, message.name, "", ", int idx__et"+data)� {
					�IF hdlr != null�
						�portClassName�_�message.name�(&((etReplPort*)self)->ports[idx__et].port�dataCall�);
					�ELSE�					
						ET_MSC_LOGGER_SYNC_ENTRY("�replPortClassName�", "�message.name�")
						if (0<=idx__et && idx__et<((etReplPort*)self)->size) {
							�sendMessageCall(hasData, "(&((etReplPort*)self)->ports[idx__et].port)", memberInUse(pc.name, dir+message.name), typeName+refp, refa+"data__et")�
							�IF Main::settings.generateMSCInstrumentation�
								ET_MSC_LOGGER_ASYNC_OUT(((etReplPort*)self)->ports[idx__et].port.myInstName, "�message.name�", ((etReplPort*)self)->ports[idx__et].port.peerInstName)
							�ENDIF�
						}
						ET_MSC_LOGGER_SYNC_EXIT
					�ENDIF�
				}
				
			�ENDFOR�
			�IF pclass!=null�
				/* begin �portClassName� specific */
				�pclass.userCode.userCode�
				
				�pc.getPortClass(conj).operations.operationsImplementation(portClassName)�
				/* end �portClassName� specific */
				
			�ENDIF�
			etInt32 �replPortClassName�_getReplication(const �replPortClassName�* self) {
				return ((etReplPort*)self)->size;
			}
			
			�IF pc.handlesReceive(conj)�
				�genReceiveHandlers(pc,conj)�
			�ENDIF�
			
		'''
	}

	def private sendMessageCall(boolean hasData, String selfPtr, String msg, String typeName, String data) {
		if (hasData)
			"etPort_sendMessage("+selfPtr+", "+msg+", sizeof("+typeName+"), "+data+");"
		else
			"etPort_sendMessage("+selfPtr+", "+msg+", 0, NULL);"
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

// TODO: can this be deleted?
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
			�AbstractGenerator::getInstance().getTranslatedCode(h.detailCode)�
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