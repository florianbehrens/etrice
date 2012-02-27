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
import org.eclipse.etrice.core.room.SubSystemClass
import org.eclipse.etrice.generator.base.ILogger
import org.eclipse.etrice.generator.etricegen.Root
import org.eclipse.etrice.generator.etricegen.ActorInstance
import org.eclipse.etrice.generator.etricegen.PortInstance
import org.eclipse.etrice.generator.etricegen.SubSystemInstance
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.etrice.generator.extensions.RoomExtensions
import org.eclipse.etrice.generator.generic.ProcedureHelpers

import static extension org.eclipse.etrice.generator.base.Indexed.*


@Singleton
class SubSystemClassGen {
	
	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions stdExt
	@Inject extension RoomExtensions roomExt
	@Inject extension ProcedureHelpers helpers
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (ssi: root.subSystemInstances) {
			var path = ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath
			var file = ssi.subSystemClass.getCHeaderFileName
			logger.logInfo("generating SubSystemClass declaration: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateHeaderFile(ssi, ssi.subSystemClass))
			
			file = ssi.subSystemClass.getCSourceFileName
			logger.logInfo("generating SubSystemClass implementation: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateSourceFile(ssi, ssi.subSystemClass))
			
			file = ssi.subSystemClass.getInstSourceFileName
			logger.logInfo("generating SubSystemClass instance file: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateInstanceFile(ssi, ssi.subSystemClass))

			file = ssi.subSystemClass.getDispSourceFileName
			logger.logInfo("generating SubSystemClass dispatcher file: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateDispatcherFile(ssi, ssi.subSystemClass))
		}
	}

	def generateHeaderFile(Root root, SubSystemInstance ssi, SubSystemClass ssc) {'''
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
		
		�helpers.userCode(ssc.userCode2)�
		
		�generateIncludeGuardEnd(ssc.name)�
		
		
	'''
	}
	
	def generateSourceFile(Root root, SubSystemInstance ssi, SubSystemClass ssc) {'''
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
		

		�helpers.userCode(ssc.userCode3)�
		
		/* data for SubSysten �ssc.name� */
		typedef struct �ssc.name� {
			char *name;
		} �ssc.name�;
		
		static �ssc.name� �ssc.name�Inst = {"�ssc.name�"};
		
		void �ssc.name�_initActorInstances(void);

		void �ssc.name�_init(void){
			ET_MSC_LOGGER_SYNC_ENTRY("SubSys", "init")
			etLogger_logInfoF("%s_init", �ssc.name�Inst.name);
			
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
			int32 i;
			for (i=0; i<100; i++){
				etLogger_logInfoF("%s Scheduler tick %d", �ssc.name�Inst.name, i);
				etMessageService_execute(&msgService_Thread1);
			}
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

	def generateInstanceFile(Root root, SubSystemInstance ssi, SubSystemClass ssc) {'''
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
		�FOR actorClass : root.getUsedActorClasses()�#include "�actorClass.name�.h"
		�ENDFOR�

		/* include all used ProtcolClasses */
		�FOR protocolClass : root.getUsedProtocolClasses()�#include "�protocolClass.name�.h"
		�ENDFOR�
		
		
		/* declarations of all ActorClass instances (const and variable structs) */

		/* forward declaration of variable actor structs */
		�FOR ai : ssi.allContainedInstances�
			static �ai.actorClass.name� �ai.path.getPathName()�;
		�ENDFOR�

		�FOR ai : ssi.allContainedInstances�
			
			/* instance �ai.path.getPathName()� */
			�IF ai.ports.empty�
				/* no ports, nothing to initialize statically */
			�ELSE�
				�genActorInstanceInitializer(root, ai)�
			�ENDIF�
		�ENDFOR�
		
	'''
	}

	def private genActorInstanceInitializer(Root root, ActorInstance ai) {
		var instName = ai.path.pathName
		
		// list of replicated ports
		var replPorts = new ArrayList<PortInstance>()
		replPorts.addAll(ai.ports.filter(e|e.kind.literal!="RELAY" && e.port.multiplicity!=1))

		// list of ports, simple first, then replicated
		var ports = new ArrayList<PortInstance>()
		ports.addAll(ai.ports.filter(e|e.kind.literal!="RELAY" && e.port.multiplicity==1).union(replPorts))

		// compute replicated port offsets		
		var offsets = new HashMap<PortInstance, Integer>()
		var offset = 0
		for (p: replPorts) {
			offsets.put(p, offset)
			offset = offset + p.peers.size
		}
		
	'''
		�IF !replPorts.empty�
			static const etReplSubPort �instName�_repl_sub_ports[�offset�] = {
				/* Replicated Sub Ports: {myActor, etReceiveMessage, msgService, peerAddress, localId, idx} */
				�FOR pi : replPorts SEPARATOR ","�
					�genReplSubPortInitializers(root, ai, pi)�
				�ENDFOR�
			};
		�ENDIF�
		static const �ai.actorClass.name�_const �instName�_const = {
			/* Ports: {myActor, etReceiveMessage, msgService, peerAddress, localId} */
			�FOR pi : ports SEPARATOR ","�
				�IF pi.port.multiplicity==1�
					�genPortInitializer(root, ai, pi)�
				�ELSE�
					{�pi.peers.size�, �instName�_repl_sub_ports+�offsets.get(pi)�}
				�ENDIF�
			�ENDFOR�
		};
		static �ai.actorClass.name� �instName� = {&�instName�_const};
	'''}
		
	def private String genPortInitializer(Root root, ActorInstance ai, PortInstance pi) {
		var recvMsg = if (pi.peers.empty) "NULL" else ai.actorClass.name+"_ReceiveMessage"
		var objId = if (pi.peers.empty) 0 else pi.peers.get(0).objId
		var idx = if (pi.peers.empty) 0 else pi.peers.get(0).peers.indexOf(pi)
		
		"{&"+ai.path.getPathName()+", "
		+recvMsg+", " 
		+"&msgService_Thread1, "
		+(objId+idx)+", "
		+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.port)+1)
		+"} /* Port "+pi.name+" */"
	}
	
	def private String genReplSubPortInitializers(Root root, ActorInstance ai, PortInstance pi) {
		var result = ""
		
		for (p: pi.peers) {
			var idx = pi.peers.indexOf(p)
			var comma = if (idx<pi.peers.size-1) "," else ""
			result = result +
				"{&"+ai.path.getPathName()+", "
				+ai.actorClass.name+"_ReceiveMessage, " 
				+"&msgService_Thread1, "
				+p.objId+", "
				+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.port)+1)+", "
				+idx
				+"}"+comma+" /* Repl Sub Port "+pi.name+" idx +"+idx+"*/\n"
		}
		
		return result
	}
	
	def generateDispatcherFile(Root root, SubSystemInstance ssi, SubSystemClass ssc) {'''
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
					�FOR pi : ai.orderedIfItemInstances�
						�IF pi instanceof PortInstance && (pi as PortInstance).port.multiplicity!=1�
							�FOR peer: pi.peers�
								case �pi.objId+pi.peers.indexOf(peer)�:
									etPort_receive((etPort*)&�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�], msg);
									break;
							�ENDFOR�
						�ELSE�
							case �pi.objId�:
								etPort_receive(&�ai.path.pathName�_const.�pi.name�, msg);
								break;
						�ENDIF�
					�ENDFOR�
				�ENDFOR�

				default:
					etLogger_logErrorF("MessageService_Thread1_ReceiveMessage: address %d does not exist ", msg->address);
					break;
			}
			ET_MSC_LOGGER_SYNC_EXIT
		}
		'''
	}

	
}