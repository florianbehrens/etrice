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

import java.util.HashMap
import java.util.ArrayList
import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.etrice.core.room.ProtocolClass
import org.eclipse.etrice.core.room.CommunicationType
import static extension org.eclipse.etrice.core.room.util.RoomHelpers.*

import org.eclipse.etrice.core.genmodel.base.ILogger
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.etricegen.ActorInstance
import org.eclipse.etrice.core.genmodel.etricegen.PortInstance
import org.eclipse.etrice.core.genmodel.etricegen.InterfaceItemInstance
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.core.room.ActorCommunicationType
import org.eclipse.etrice.generator.generic.ILanguageExtension
import static extension org.eclipse.etrice.generator.base.Indexed.*
import org.eclipse.etrice.core.room.Attribute

@Singleton
class SubSystemClassGen {
	
	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions stdExt
	@Inject extension RoomExtensions roomExt
	@Inject extension ProcedureHelpers helpers
	@Inject ILanguageExtension languageExt
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (ssi: root.subSystemInstances) {
			var path = ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath
			var file = ssi.subSystemClass.getCHeaderFileName
			logger.logInfo("generating SubSystemClass declaration: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateHeaderFile(ssi))
			
			file = ssi.subSystemClass.getCSourceFileName
			logger.logInfo("generating SubSystemClass implementation: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateSourceFile(ssi))
			
			file = ssi.subSystemClass.getInstSourceFileName
			logger.logInfo("generating SubSystemClass instance file: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateInstanceFile(ssi))

			file = ssi.subSystemClass.getDispSourceFileName
			logger.logInfo("generating SubSystemClass dispatcher file: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateDispatcherFile(ssi))
		}
	}

	def private generateHeaderFile(Root root, SubSystemInstance ssi) {
	val ssc = ssi.subSystemClass
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Header File of SubSystemClass �ssc.name�
		 * 
		 */
		
		�generateIncludeGuardBegin(ssc.name)�

		�helpers.userCode(ssc.userCode1)�
		
		
		/* lifecycle functions
		 * init -> start -> run (loop) -> stop -> destroy
		 */
		
		void �ssc.name�_init(void);		/* lifecycle init  	 */
		void �ssc.name�_start(void);	/* lifecycle start 	 */
		
		void �ssc.name�_run(void);		/* lifecycle run 	 */
		
		void �ssc.name�_stop(void); 	/* lifecycle stop	 */
		void �ssc.name�_destroy(void); 	/* lifecycle destroy */
		
		void SubSysClass_shutdown(void);  /* shutdown the dispatcher loop */
		
		�helpers.userCode(ssc.userCode2)�
		
		�generateIncludeGuardEnd(ssc.name)�
		
		
	'''
	}
	
	def private generateSourceFile(Root root, SubSystemInstance ssi) {
	val ssc = ssi.subSystemClass
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Source File of SubSystemClass �ssc.name�
		 * 
		 */
		
		#include "�ssc.getCHeaderFileName�"

		/* include instances for all classes */
		#include "�ssc.getInstSourceFileName�"
		#include "�ssc.getDispSourceFileName�"

		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		
		#include "platform/etTimer.h"
		#include "etRuntimeConfig.h"

		�helpers.userCode(ssc.userCode3)�
		
		/* data for SubSysten �ssc.name� */
		typedef struct �ssc.name� {
			char *name;
			volatile int shutdownRequest;
		} �ssc.name�;
		
		static �ssc.name� �ssc.name�Inst = {"�ssc.name�",0};
		
		void �ssc.name�_initActorInstances(void);
		void �ssc.name�_constructActorInstances(void);

		void �ssc.name�_init(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "init")
			etLogger_logInfoF("%s_init", �ssc.name�Inst.name);
			
			/* construct all actors */
			�ssc.name�_constructActorInstances();
			
			/* initialization of all message services */
			etMessageService_init(&msgService_Thread1, msgBuffer_Thread1, MESSAGE_POOL_MAX, MESSAGE_BLOCK_SIZE, MsgDispatcher_Thread1_receiveMessage);
			
			/* init all actors */
			�ssc.name�_initActorInstances();
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �ssc.name�_start(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "start")
			etLogger_logInfoF("%s_start", �ssc.name�Inst.name);
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �ssc.name�_run(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "run")
			
			#ifdef ET_RUNTIME_ENDLESS
				while(!(�ssc.name�Inst.shutdownRequest)){
					if (etTimer_executeNeeded()){
						etMessageService_execute(&msgService_Thread1);
						�generateDatadrivenExecutes(root, ssi)�
					}
				}
			#else
				uint32 loopCounter = 0;
				while(!(�ssc.name�Inst.shutdownRequest)){
					if (etTimer_executeNeeded()){
						etMessageService_execute(&msgService_Thread1);
						�generateDatadrivenExecutes(root, ssi)�
						etLogger_logInfo("Execute");
						if (loopCounter++ > ET_RUNTIME_MAXLOOP){
							break;
						}
					}
				}
			#endif
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �ssc.name�_stop(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "stop")
			etLogger_logInfoF("%s_stop", �ssc.name�Inst.name);
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �ssc.name�_destroy(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "destroy")
			etLogger_logInfoF("%s_destroy", �ssc.name�Inst.name);
			�FOR ai : ssi.allContainedInstances.reverseView�
				�IF !ai.actorClass.operations.filter(op|op.destructor).empty�
					�languageExt.memberInUse(ai.actorClass.name, languageExt.destructorName(ai.actorClass.name))�(&�ai.path.getPathName()�);
				�ENDIF�
			�ENDFOR�
			ET_MSC_LOGGER_SYNC_EXIT
		}

		void SubSysClass_shutdown(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "shutdown")
			etLogger_logInfoF("%s_shutdown", �ssc.name�Inst.name);
			�ssc.name�Inst.shutdownRequest = 1;
			ET_MSC_LOGGER_SYNC_EXIT
		}


		void �ssc.name�_constructActorInstances(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�ssc.name�", "constructActorInstances")
			�FOR ai : ssi.allContainedInstances�
				�IF !ai.actorClass.operations.filter(op|op.constructor).empty�
					�languageExt.memberInUse(ai.actorClass.name, languageExt.constructorName(ai.actorClass.name))�(&�ai.path.getPathName()�);
				�ENDIF�
			�ENDFOR�
			ET_MSC_LOGGER_SYNC_EXIT
		}

		void �ssc.name�_initActorInstances(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�ssc.name�", "initActorInstances")
			�FOR ai : ssi.allContainedInstances�
				�ai.actorClass.name�_init(&�ai.path.getPathName()�);
			�ENDFOR�
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
	'''
	}

	def private generateInstanceFile(Root root, SubSystemInstance ssi) {
	val ssc = ssi.subSystemClass
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Instance File of SubSystemClass �ssc.name�
		 * - instantiation of all actor instances and port instances
		 * - configuration of data and connection of ports
		 */

		#include "messaging/etMessageService.h"
		#include "platform/etMemory.h"
		
		/* instantiation of message services */
		
		/* MessageService for Thread1 */
		static uint8 msgBuffer_Thread1[MESSAGE_POOL_MAX*MESSAGE_BLOCK_SIZE];
		static etMessageService msgService_Thread1;


		/* include all used ActorClasses */
		�FOR actorClass : root.getUsedActorClasses()�
			#include "�actorClass.name�.h"
		�ENDFOR�

		/* include all used ProtcolClasses */
		�FOR protocolClass : root.getUsedProtocolClasses()�
			#include "�protocolClass.name�.h"
		�ENDFOR�
		
		
		/* declarations of all ActorClass instances (const and variable structs) */

		/* forward declaration of variable actor structs */
		�FOR ai : ssi.allContainedInstances�
			static �ai.actorClass.name� �ai.path.getPathName()�;
		�ENDFOR�
		
		/* forward declaration of variable port structs */		
		�FOR ai: ssi.allContainedInstances�
			�IF ai.orderedIfItemInstances.empty�
				/*nothing to do */
			�ELSE�
				�FOR pi:ai.orderedIfItemInstances�
					�IF pi.protocol.getPortClass(pi.conjugated)!=null�
						�IF !pi.protocol.getPortClass(pi.conjugated).attributes.empty�
							�IF pi.replicated�
								static �pi.protocol.getPortClassName(pi.conjugated)�_var �pi.path.pathName�_var[�pi.peers.size�]={�genReplPortAttributeInitializer(pi)�};
							�ELSE�
								static �pi.protocol.getPortClassName(pi.conjugated)�_var �pi.path.pathName�_var={�genPortAttributeInitializer(pi)�};
							�ENDIF�
						�ENDIF�
					�ENDIF�		
				�ENDFOR�
			�ENDIF� 
		�ENDFOR�
		
		�FOR ai : ssi.allContainedInstances�
			
			/* instance �ai.path.getPathName()� */
			�IF ai.orderedIfItemInstances.empty�
				/* no ports/saps/services - nothing to initialize statically */
			�ELSE�
				�genActorInstanceInitializer(root, ai)�
			�ENDIF�
		�ENDFOR�
		
	'''
	}
		
	def private genReplPortAttributeInitializer(InterfaceItemInstance pi){
		var int i
		var retval="" 
		i=pi.peers.size
		
		while (i>0){
			retval=retval+"
			{"+genPortAttributeInitializer(pi)+"}"
			i=i-1
			if (i>0){retval=retval+","}
		}
		return retval
	}	
	
	def private genPortAttributeInitializer(InterfaceItemInstance pi){
		'''
		�FOR attr:pi.protocol.getPortClass(pi.conjugated).attributes SEPARATOR ","��IF attr.defaultValueLiteral != null��attr.defaultValueLiteral��ELSE��attr.refType.type.defaultValue��ENDIF��ENDFOR�'''
	}
	
	def private genActorInstanceInitializer(Root root, ActorInstance ai) {
		var instName = ai.path.pathName
		
		// list of replicated ports
		var replPorts = new ArrayList<InterfaceItemInstance>()
		replPorts.addAll(ai.orderedIfItemInstances.filter(e|e.replicated))
		var haveReplSubPorts = replPorts.findFirst(e|!e.peers.empty)!=null
		
		var simplePorts = ai.orderedIfItemInstances.filter(e|e.simple)
		
		// list of event ports, simple first, then replicated
		var eventPorts = new ArrayList<InterfaceItemInstance>()
		eventPorts.addAll(simplePorts.filter(p|p.protocol.commType==CommunicationType::EVENT_DRIVEN).union(replPorts))

		var dataPorts = simplePorts.filter(p|p.protocol.commType==CommunicationType::DATA_DRIVEN)
		var recvPorts = dataPorts.filter(p|p instanceof PortInstance && !(p as PortInstance).port.conjugated)
		var sendPorts = dataPorts.filter(p|p instanceof PortInstance && (p as PortInstance).port.conjugated)
		
		// compute replicated port offsets		
		var offsets = new HashMap<InterfaceItemInstance, Integer>()
		var offset = 0
		for (p: replPorts) {
			offsets.put(p, offset)
			offset = offset + p.peers.size
		}
		
		var replSubPortsArray = if (haveReplSubPorts) instName+"_repl_sub_ports" else "NULL"
		
	'''
		�IF haveReplSubPorts�
			static const etReplSubPort �replSubPortsArray�[�offset�] = {
				/* Replicated Sub Ports: {varData, msgService, peerAddress, localId, index} */
				�FOR pi : replPorts.filter(e|!e.peers.empty) SEPARATOR ","�
					�genReplSubPortInitializers(root, ai, pi)�
				�ENDFOR�
			};
		�ENDIF�
		�IF !(eventPorts.empty && recvPorts.empty)�
			static const �ai.actorClass.name�_const �instName�_const = {
				/* Ports: {varData, msgService, peerAddress, localId} */
				�FOR pi : eventPorts SEPARATOR ","�
					�IF pi.simple�
						�genPortInitializer(root, ai, pi)�
					�ELSE�
						{�pi.peers.size�, �replSubPortsArray�+�offsets.get(pi)�}
					�ENDIF�
				�ENDFOR�
				�IF !eventPorts.empty && !recvPorts.empty�,�ENDIF�
				
				/* data receive ports */
				�FOR pi : recvPorts SEPARATOR ","�
					�genRecvPortInitializer(root, ai, pi)�
				�ENDFOR�
			};
			static �ai.actorClass.name� �instName� = {
				&�instName�_const,
				
				/* data send ports */
				�FOR pi : sendPorts�
					�pi.genSendPortInitializer�,
				�ENDFOR�
				
				/* attributes */
				�FOR att : ai.actorClass.allAttributes�
					�ai.genAttributeInitializer(att)�,	/* �att.name��IF att.size>1�[�att.size�]�ENDIF� */
				�ENDFOR�
				
				/* state and history are initialized in init fuction */
			};
		�ENDIF�
	'''}
		
	def private String genPortInitializer(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		var objId = if (pi.peers.empty) 0 else pi.peers.get(0).objId
		var idx = if (pi.peers.empty) 0 else pi.peers.get(0).peers.indexOf(pi)
		
		"{"+getInterfaceItemInstanceData(pi)+"," 
		+"&msgService_Thread1, "
		+(objId+idx)+", "
		+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.interfaceItem)+1)
		+"} /* Port "+pi.name+" */"
	}
	
	def private genSendPortInitializer(InterfaceItemInstance pi) {
		val pc = (pi as PortInstance).port.protocol as ProtocolClass
		
		'''
			{
				�FOR m : pc.incomingMessages SEPARATOR ","�
					�m.data.refType.type.defaultValue�
				�ENDFOR�
			}
		'''
	}
	
	def private genAttributeInitializer(ActorInstance ai, Attribute att) {
		val value = null as String // att.initValueLiteral
		if (value==null)
			att.initializationWithDefaultValues
		else
			value.toString
	}
	
	def private getInterfaceItemInstanceData(InterfaceItemInstance pi){
		if (pi.protocol.getPortClass(pi.conjugated)== null) return "0"
		if (pi.protocol.getPortClass(pi.conjugated).attributes.empty){
			return "0"
		}else{
			return "&"+pi.path.pathName+"_var"
		}
	}
	
	
	def private String genRecvPortInitializer(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		if (pi.peers.empty)
			return "{NULL}"

		var peer = pi.peers.get(0)
		var peerInst = pi.peers.get(0).eContainer() as ActorInstance
		var instName = peerInst.path.pathName
			
		"{&"+instName+"."+peer.name+"}"
	}
	
	def private String genReplSubPortInitializers(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		var result = ""
		
		for (p: pi.peers) {
			var idx = pi.peers.indexOf(p)
			var comma = if (idx<pi.peers.size-1) "," else ""
			var iiiD = getInterfaceItemInstanceData(pi)
			iiiD = if (iiiD.equals("0")) iiiD+"," else iiiD+"["+idx+"],"
			result = result +
				"{"+iiiD 
				+"&msgService_Thread1, "
				+p.objId+", "
				+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.interfaceItem)+1)+", "
				+idx
				+"}"+comma+" /* Repl Sub Port "+pi.name+" idx +"+idx+"*/\n"
		}
		
		return result
	}
	
	def private generateDispatcherFile(Root root, SubSystemInstance ssi) {
	val ssc = ssi.subSystemClass
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Dispatcher File of SubSystemClass �ssc.name�
		 * - one generated dispatcher for each MessageService (Thread)
		 */

		#include "messaging/etMessageReceiver.h"
		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		
		static void MsgDispatcher_Thread1_receiveMessage(const etMessage* msg){
			ET_MSC_LOGGER_SYNC_ENTRY("MsgDispatcher_Thread1", "receiveMessage")
			switch(msg->address){
			
				�FOR ai : ssi.allContainedInstances�
					/* interface items of �ai.path� */
					�FOR pi : ai. orderedIfItemInstances.filter(p|p.protocol.commType==CommunicationType::EVENT_DRIVEN)�
						�IF pi.replicated�
							�FOR peer: pi.peers�
								case �pi.objId+pi.peers.indexOf(peer)�:
								�IF (pi.protocol.handlesReceive(pi.isConjugated()))�
									switch (msg->evtID){
										�FOR h:getReceiveHandlers(pi.protocol,pi.isConjugated())�
											case �pi.protocol.name�_�h.msg.codeName�:
												�pi.protocol.getPortClassName(pi.isConjugated)�_�h.msg.name�_receiveHandler((etPort *)&�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�],msg,(void*)&�ai.path.pathName�,�ai.actorClass.name�_receiveMessage);
											break;
										�ENDFOR�
										default: �ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,(etPort*)&�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�], msg);
										break;
										}										
								�ELSE�
									�ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,(etPort*)&�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�], msg);
								�ENDIF�
								break;
							�ENDFOR�
						�ELSE�
							case �pi.objId�:
							�IF (pi.protocol.handlesReceive(pi.isConjugated()))� 
								switch (msg->evtID){
								�FOR h:getReceiveHandlers(pi.protocol,pi.isConjugated())�
									case �pi.protocol.name�_�h.msg.codeName�:
										�pi.protocol.getPortClassName(pi.isConjugated)�_�h.msg.name�_receiveHandler((etPort *)&�ai.path.pathName�_const.�pi.name�,msg,(void*)&�ai.path.pathName�,�ai.actorClass.name�_receiveMessage);
									break;
								�ENDFOR�
								default: �ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,(etPort*)&�ai.path.pathName�_const.�pi.name�, msg);
								break;
								}
							�ELSE�
								�ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,(etPort*)&�ai.path.pathName�_const.�pi.name�, msg);
							�ENDIF�
							break;
						�ENDIF�
					�ENDFOR�
				�ENDFOR�

				default:
					etLogger_logErrorF("MessageService_Thread1_receiveMessage: address %d does not exist ", msg->address);
				break;
			}
			ET_MSC_LOGGER_SYNC_EXIT
		}
		'''
	}
	
	def private generateDatadrivenExecutes(Root root, SubSystemInstance ssi) {'''
		�FOR ai : ssi.allContainedInstances�
			�IF ai.actorClass.commType == ActorCommunicationType::ASYNCHRONOUS || ai.actorClass.commType == ActorCommunicationType::DATA_DRIVEN�
				�ai.actorClass.name�_execute(&�ai.path.getPathName()�);
			�ENDIF�
		�ENDFOR�
	'''
	}
	
}
