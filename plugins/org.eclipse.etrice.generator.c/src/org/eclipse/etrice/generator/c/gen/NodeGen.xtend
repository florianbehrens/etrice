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
import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import org.eclipse.etrice.core.common.base.LiteralType
import org.eclipse.etrice.core.etmap.util.ETMapUtil
import org.eclipse.etrice.core.etphys.eTPhys.ExecMode
import org.eclipse.etrice.core.etphys.eTPhys.PhysicalThread
import org.eclipse.etrice.core.genmodel.fsm.base.ILogger
import org.eclipse.etrice.core.genmodel.etricegen.ActorInstance
import org.eclipse.etrice.core.genmodel.fsm.fsmgen.IDiagnostician
import org.eclipse.etrice.core.genmodel.etricegen.InterfaceItemInstance
import org.eclipse.etrice.core.genmodel.etricegen.PortInstance
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.etrice.core.fsm.fSM.ComponentCommunicationType
import org.eclipse.etrice.core.room.CommunicationType
import org.eclipse.etrice.core.room.EnumerationType
import org.eclipse.etrice.core.room.Port
import org.eclipse.etrice.core.room.PrimitiveType
import org.eclipse.etrice.core.room.ProtocolClass
import org.eclipse.etrice.core.room.SAP
import org.eclipse.etrice.core.room.SPP
import org.eclipse.etrice.generator.fsm.base.IGeneratorFileIo
import org.eclipse.etrice.generator.fsm.base.IntelligentSeparator
import org.eclipse.etrice.generator.c.Main
import org.eclipse.etrice.generator.generic.ILanguageExtension
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.generator.generic.TypeHelpers
import org.eclipse.etrice.core.room.util.RoomHelpers

import org.eclipse.etrice.core.common.converter.TimeConverter

@Singleton
class NodeGen {
	
	@Inject extension RoomHelpers
	@Inject extension CExtensions
	@Inject extension RoomExtensions
	@Inject extension TypeHelpers
	@Inject extension ProcedureHelpers helpers
	
	@Inject IGeneratorFileIo fileIO
	@Inject Initialization attrInitGenAddon
	@Inject ILanguageExtension languageExt
	@Inject IDiagnostician diagnostician
	@Inject ILogger logger;
	
	def doGenerate(Root root) {
		for (nr : ETMapUtil::getNodeRefs()) {
			for (instpath : ETMapUtil::getSubSystemInstancePaths(nr)) {
				val ssi = root.getInstance(instpath) as SubSystemInstance
				if (ssi!=null) {
					val filepath = ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath
					val infopath = ssi.subSystemClass.generationInfoPath+ssi.subSystemClass.getPath
					var file = nr.getCHeaderFileName(ssi)
				
					checkDataPorts(ssi)
					
					val usedThreads = ETMapUtil::getUsedThreads(nr, ssi)
					
					fileIO.generateFile("generating Node declaration", filepath, infopath, file, root.generateHeaderFile(ssi))
					
					file = nr.getCSourceFileName(ssi)
					fileIO.generateFile("generating Node implementation", filepath, infopath, file, root.generateSourceFile(ssi, usedThreads))
					
					file = nr.getInstSourceFileName(ssi)
					fileIO.generateFile("generating Node instance file", filepath, infopath, file, root.generateInstanceFile(ssi, usedThreads))
		
					file = nr.getDispSourceFileName(ssi)
					fileIO.generateFile("generating Node dispatcher file", filepath, infopath, file, root.generateDispatcherFile(ssi, usedThreads))
				}
			}
		}
	}

	def private generateHeaderFile(Root root, SubSystemInstance ssi) {
	val nr = ETMapUtil::getNodeRef(ssi)
	val ssc = ssi.subSystemClass
	val clsname = nr.name+"_"+ssi.name
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Header File of Node �nr.name� with SubSystem �ssi.name�
		 * 
		 */
		
		�generateIncludeGuardBegin(clsname)�
		
		#include "etDatatypes.h"

���		TODOCGENPHYS: user code?
		�helpers.userCode(ssc.userCode1)�
		
		
		/* lifecycle functions
		 * init -> start -> run (loop) -> stop -> destroy
		 */
		
		void �clsname�_init(void);		/* lifecycle init  	 */
		void �clsname�_start(void);	/* lifecycle start 	 */
		
		void �clsname�_run(etBool runAsTest);		/* lifecycle run 	 */
		
		void �clsname�_stop(void); 	/* lifecycle stop	 */
		void �clsname�_destroy(void); 	/* lifecycle destroy */
		
		void �clsname�_shutdown(void);  /* shutdown the dispatcher loop */
		
���		TODOCGENPHYS: user code?
		�helpers.userCode(ssc.userCode2)�
		
		�generateIncludeGuardEnd(clsname)�
		
		
	'''
	}
	
	def private generateSourceFile(Root root, SubSystemInstance ssi, Collection<PhysicalThread> usedThreads) {
	val nr = ETMapUtil::getNodeRef(ssi)
	val ssc = ssi.subSystemClass
	val clsname = nr.name+"_"+ssi.name
	val threads = nr.type.threads.filter(t|usedThreads.contains(t))
	val logData = ssc.annotations.isAnnotationPresent("DataLogging")
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Source File of Node �nr.name� with SubSystem �ssi.name�
		 * 
		 */
		
		#include <stdio.h>
		#include <string.h>
		
		
		#include "�nr.getCHeaderFileName(ssi)�"

		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		#include "debugging/etDataLogger.h"
		#include "messaging/etSystemProtocol.h"
		#include "osal/etTimer.h"
		#include "osal/etSema.h"
		#include "runtime/etRuntime.h"
		#include "etRuntimeConfig.h"

���		TODOCGENPHYS: user code?
		�helpers.userCode(ssc.userCode3)�
		
		/* data for Node �nr.name� with SubSystem �ssi.name� */
		typedef struct �clsname� {
			char *name;
			volatile int shutdownRequest;
		} �clsname�;
		
		static �clsname� �clsname�Inst = {"�clsname�", 0};
		
		static void �clsname�_initActorInstances(void);
		static void �clsname�_constructActorInstances(void);
		
		/* include instances for all classes */
		#include "�nr.getInstSourceFileName(ssi)�"
		#include "�nr.getDispSourceFileName(ssi)�"

		static void �clsname�_initMessageServices(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "initMessageServices")
			{
				etTime interval;
				
				/* initialization of all message services */
				�FOR thread: threads�
					�IF thread.execmode==ExecMode::POLLED || thread.execmode==ExecMode::MIXED�
						interval.sec = �TimeConverter::split(thread.time, TimeConverter.SEC, true)�;
						interval.nSec = �TimeConverter::split(thread.time, TimeConverter.MILLI_SEC, false)�;
					�ENDIF�
					etMessageService_init(
						&msgService_�thread.name�,
						msgBuffer_�thread.name�,
						�thread.name.toUpperCase�_POOL_SIZE,
						�thread.name.toUpperCase�_BLOCK_SIZE,
						�thread.stacksize�,
						�thread.prio�,
						interval,
						MsgDispatcher_�thread.name�_receiveMessage,
						EXECMODE_�thread.execmode.toString.toUpperCase�);
						
				�ENDFOR�
			}
			
			ET_MSC_LOGGER_SYNC_EXIT
		}

		static void �clsname�_startMessageServices(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "startMessageServices")
			
			�FOR thread: threads.sortBy[getPrio].reverse�
				etMessageService_start(&msgService_�thread.name�);
			�ENDFOR�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}

		static void �clsname�_stopMessageServices(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "stopMessageServices")
			
			�FOR thread: threads�
				etMessageService_stop(&msgService_�thread.name�);
			�ENDFOR�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}

		static void �clsname�_destroyMessageServices(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "destroyMessageServices")
			
			�FOR thread: threads�
				etMessageService_destroy(&msgService_�thread.name�);
			�ENDFOR�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �clsname�_init(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "init")
			etLogger_logInfoF("%s_init", �clsname�Inst.name);
			
			/* construct all actors */
			�clsname�_constructActorInstances();
			
			/* initialization of all message services */
			�clsname�_initMessageServices();
			
			/* init all actors */
			�clsname�_initActorInstances();
			
			�IF logData�
				�FOR thread: threads�
					MsgDispatcher_�thread.name�_logDataHeaders();
				�ENDFOR�
				�FOR thread: threads�
					MsgDispatcher_�thread.name�_logData();
				�ENDFOR�
			�ENDIF�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �clsname�_start(void) {
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "start")
			etLogger_logInfoF("%s_start", �clsname�Inst.name);
			�clsname�_startMessageServices();
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �clsname�_run(etBool runAsTest) {
		#ifdef ET_RUNNER_ACTIVATE 
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "run")
			
			if (runAsTest) {
				etSema_waitForWakeup(etRuntime_getTerminateSemaphore());
			}
			else {
				printf("type quit to exit\n");
				fflush(stdout);
				while (ET_TRUE) {
					char line[64];
		
					if (fgets(line, 64, stdin) != NULL) {
						if (strncmp(line, "quit", 4)==0)
							break;
					}
				}
			}
			
			ET_MSC_LOGGER_SYNC_EXIT
		#endif	
		}
		
		void �clsname�_stop(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "stop")
			etLogger_logInfoF("%s_stop", �clsname�Inst.name);
		
			�clsname�_stopMessageServices();
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		void �clsname�_destroy(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "destroy")
			etLogger_logInfoF("%s_destroy", �clsname�Inst.name);
			�FOR ai : ssi.allContainedInstances.reverseView�
				�IF !ai.actorClass.operations.filter(op|op.destructor).empty�
					�languageExt.memberInUse(ai.actorClass.name, languageExt.destructorName(ai.actorClass.name))�(&�ai.path.getPathName()�);
				�ENDIF�
			�ENDFOR�
			
			�clsname�_destroyMessageServices();
			
			ET_MSC_LOGGER_SYNC_EXIT
		}

		void �clsname�_shutdown(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "shutdown")
			etLogger_logInfoF("%s_shutdown", �clsname�Inst.name);
			
			�clsname�Inst.shutdownRequest = 1;
			
			ET_MSC_LOGGER_SYNC_EXIT
		}


		static void �clsname�_constructActorInstances(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "constructActorInstances")
			
			�FOR ai : ssi.allContainedInstances�
				�IF !ai.actorClass.operations.filter(op|op.constructor).empty�
					�languageExt.memberInUse(ai.actorClass.name, languageExt.constructorName(ai.actorClass.name))�(&�ai.path.getPathName()�);
				�ENDIF�
			�ENDFOR�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}

		static void �clsname�_initActorInstances(void){
			ET_MSC_LOGGER_SYNC_ENTRY("�clsname�", "initActorInstances")
			
			�FOR ai : ssi.allContainedInstances�
				�ai.actorClass.name�_init(&�ai.path.getPathName()�);
			�ENDFOR�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
	'''
	}

	def private generateInstanceFile(Root root, SubSystemInstance ssi, Collection<PhysicalThread> usedThreads) {
	val nr = ETMapUtil::getNodeRef(ssi)
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Instance File of Node �nr.name� with SubSystem �ssi.name�
		 * - instantiation of all actor instances and port instances
		 * - configuration of data and connection of ports
		 */

		#include "messaging/etMessageService.h"

		/* include all referenced ActorClasses */
		�FOR actorClass : root.getReferencedActorClasses(ssi.subSystemClass)�
			#include �actorClass.includePath�
		�ENDFOR�

		/* include all referenced ProtcolClasses */
		�FOR protocolClass : root.getReferencedProtocolClasses(ssi.subSystemClass)�
			#include �protocolClass.includePath�
		�ENDFOR�
		
		/* instantiation of message services and message buffers */
		�FOR thread: nr.type.threads.filter(t|usedThreads.contains(t))�
			/* �thread.name� */
			#define �thread.name.toUpperCase�_POOL_SIZE		�thread.msgpoolsize�
			#define �thread.name.toUpperCase�_BLOCK_SIZE	�thread.msgblocksize�
			static uint8 msgBuffer_�thread.name�[�thread.name.toUpperCase�_POOL_SIZE * �thread.name.toUpperCase�_BLOCK_SIZE]; 
			static etMessageService msgService_�thread.name�;
		�ENDFOR�
		
		/* declarations of all ActorClass instances (const and variable structs) */

		/* forward declaration of variable actor structs */
		�FOR ai : ssi.allContainedInstances�
			static �ai.actorClass.name� �ai.path.getPathName()�;
		�ENDFOR�
		
		/* forward declaration of variable port structs */
		�FOR ai: ssi.allContainedInstances�
			�IF ai.orderedIfItemInstances.empty�
				/* nothing to do */
			�ELSE�
				�FOR pi:ai.orderedIfItemInstances�
					�IF pi.protocol.getPortClass(pi.conjugated)?.attributes?.size > 0�
						static �pi.protocol.getPortClassName(pi.conjugated)�_var �pi.path.pathName�_var�IF pi.replicated�[�pi.peers.size�]�ENDIF�={
							�FOR Integer i:1.. if(pi.peers.size==0)1 else pi.peers.size SEPARATOR ', '�
								�attrInitGenAddon.generateAttributeInit(pi, pi.interfaceItem.portClass.attributes)�
							�ENDFOR�};
					�ENDIF�		
				�ENDFOR�
			�ENDIF� 
		�ENDFOR�
		
		�FOR ai : ssi.allContainedInstances�
			
			/* instance �ai.path.getPathName()� */
			�IF !Main::settings.generateMSCInstrumentation && ai.orderedIfItemInstances.empty�
				/* no ports/saps/services - nothing to initialize statically */
			�ELSE�
				�genActorInstanceInitializer(root, ai)�
			�ENDIF�
		�ENDFOR�
		
	'''
	}
	
	def private genActorInstanceInitializer(Root root, ActorInstance ai) {
		val instName = ai.path.pathName
		
		// list of replicated interface items (all are event driven ports)
		val replEventItems = new ArrayList<InterfaceItemInstance>()
		replEventItems.addAll(ai.orderedIfItemInstances.filter(e|e.replicated))
		val haveReplSubItems = replEventItems.findFirst(e|!e.peers.empty)!=null
		val replEventPorts = replEventItems.filter(i|i.interfaceItem instanceof Port)
		val replEventSPPs = replEventItems.filter(i|i.interfaceItem instanceof SPP)
		
		val simplePorts = ai.orderedIfItemInstances.filter(e|e.simple)
		
		// list of simple event interface items
		val simpleEventItems = new ArrayList<InterfaceItemInstance>()
		simpleEventItems.addAll(simplePorts.filter(p|p.protocol.commType==CommunicationType::EVENT_DRIVEN))
		
		// lists of event driven ports and saps
		val simpleEventPorts = simpleEventItems.filter(i|i.interfaceItem instanceof Port)
		val simpleEventSAPs = simpleEventItems.filter(i|i.interfaceItem instanceof SAP)
		
		val dataPorts = simplePorts.filter(p|p.protocol.commType==CommunicationType::DATA_DRIVEN)
		val recvPorts = dataPorts.filter(p|p instanceof PortInstance && !(p as PortInstance).port.conjugated)
		val sendPorts = dataPorts.filter(p|p instanceof PortInstance && (p as PortInstance).port.conjugated)
		
		// compute replicated port offsets		
		val offsets = new HashMap<InterfaceItemInstance, Integer>()
		var offset = 0
		for (p: replEventItems) {
			offsets.put(p, offset)
			offset = offset + p.peers.size
		}
		
		var replSubPortsArray = if (haveReplSubItems) instName+"_repl_sub_ports" else "NULL"
		val haveConstData = !simpleEventItems.empty || !recvPorts.empty || !replEventItems.empty
				|| Main::settings.generateMSCInstrumentation
		val sep = new IntelligentSeparator(",");
		
		val const = if (Main::settings.generateMSCInstrumentation) "/*const*/" else "const"
	'''
		�IF Main::settings.generateMSCInstrumentation�
			�genPeerPortArrays(root, ai)�
		�ENDIF�
		�IF haveReplSubItems�
			static const etReplSubPort �replSubPortsArray�[�offset�] = {
				/* Replicated Sub Ports: {varData, msgService, peerAddress, localId, index} */
				�FOR pi : replEventItems.filter(e|!e.peers.empty) SEPARATOR ","�
					�genReplSubPortInitializers(root, ai, pi)�
				�ENDFOR�
			};
		�ENDIF�
		�IF haveConstData�
			static �const� �ai.actorClass.name�_const �instName�_const = {
				�IF Main::settings.generateMSCInstrumentation�
					�sep�"�ai.path�"
					
				�ENDIF�
				/* Ports: {varData, msgService, peerAddress, localId} */
				/* simple ports */
				�FOR pi : simpleEventPorts�
					�sep��genPortInitializer(root, ai, pi)�
				�ENDFOR�
				
				/* data receive ports */
				�FOR pi : recvPorts�
					�sep��genRecvPortInitializer(root, ai, pi)�
				�ENDFOR�
				
				/* saps */
				�FOR pi : simpleEventSAPs�
					�sep��genPortInitializer(root, ai, pi)�
				�ENDFOR�
				
				/* replicated ports */
				�FOR pi : replEventPorts�
					�sep�{�pi.peers.size�, �replSubPortsArray�+�offsets.get(pi)�}
				�ENDFOR�
				
				/* services */
				�FOR pi : replEventSPPs�
					�sep�{�pi.peers.size�, �replSubPortsArray�+�offsets.get(pi)�}
				�ENDFOR�
			};
		�ENDIF�
		static �ai.actorClass.name� �instName� = {
			�IF haveConstData�
				&�instName�_const,
				
			�ENDIF�			
			/* data send ports */
			�FOR pi : sendPorts�
				�pi.genSendPortInitializer�,
			�ENDFOR�
			
			/* attributes */
			�attrInitGenAddon.generateAttributeInit(ai, ai.actorClass.allAttributes)�
			
			/* state and history are initialized in init function */
		};
	'''}
	
	private def genPeerPortArrays(Root root, ActorInstance ai) {
		val simplePorts = ai.orderedIfItemInstances.filter(e|e.simple && e instanceof PortInstance).map(inst|inst as PortInstance)
		val sendPorts = simplePorts.filter(p|p.port.conjugated && p.protocol.commType==CommunicationType::DATA_DRIVEN)
		val loggedPorts = sendPorts.filter(p|!p.port.outgoing.filter(m|m.data.refType.type.isEnumeration || m.data.refType.type.isBoolean).empty)
		'''
		�IF !loggedPorts.empty�
			#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
			�FOR pi: loggedPorts�
				static const char* �pi.path.pathName�_peers[�pi.peers.size+1�] = {
					�FOR peer: pi.peers�
						"�(peer.eContainer as ActorInstance).path�",
					�ENDFOR�
					NULL
				};
			�ENDFOR�
			#endif
		�ENDIF�
		'''
	}
		
	def private String genPortInitializer(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		val objId = if (pi.peers.empty) 0 else pi.peers.get(0).objId
		val idx = if (pi.peers.empty) 0 else pi.peers.get(0).peers.indexOf(pi)
		val msgSvc = if (pi.peers.empty) "NULL" else "&msgService_"+ETMapUtil::getMappedThread(pi.peers.get(0).eContainer as ActorInstance).thread.name
		var myInst = ""
		var peerInst = ""
		if(Main::settings.generateMSCInstrumentation) {
			myInst = "\n#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE\n,\""+(pi.eContainer as ActorInstance).path+"\","
			peerInst = if (!pi.peers.empty) "\""+(pi.peers.get(0).eContainer as ActorInstance).path+"\"\n#endif\n"
				else "\n#endif\n"
		}
		
		"{"+getInterfaceItemInstanceData(pi)+", " 
		+msgSvc+", "
		+(objId+idx)+"+BASE_ADDRESS, "
		+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.interfaceItem)+1)
		+myInst
		+peerInst
		+"} /* Port "+pi.name+" */"
	}
	
	def private genSendPortInitializer(InterfaceItemInstance pi) {
		val pc = (pi as PortInstance).port.protocol as ProtocolClass
		var messages = pc.allIncomingMessages.filter(m|m.data!=null)
		val enumMsgs = messages.filter(m|m.data.refType.type.isEnumeration)
		val boolMsgs = messages.filter(m|m.data.refType.type.isBoolean)
		val usesMSC = Main::settings.generateMSCInstrumentation && !(enumMsgs.empty && boolMsgs.empty)
		val instName = (pi.eContainer as ActorInstance).path
		
		'''
			{
				�FOR m : pc.incomingMessages SEPARATOR ","�
					�m.data.refType.type.defaultValue�
				�ENDFOR�
				�IF usesMSC�
					#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE
						, "�instName�",
						�pi.path.pathName�_peers
					#endif
				�ENDIF�
			} /* send port �pi.name� */
		'''
	}
	
	def private getInterfaceItemInstanceData(InterfaceItemInstance pi){
		if (pi.protocol.getPortClass(pi.conjugated)== null) return "NULL"
		if (pi.protocol.getPortClass(pi.conjugated).attributes.empty){
			return "NULL"
		}else{
			return "&"+pi.path.pathName+"_var"
		}
	}
	
	
	def private String genRecvPortInitializer(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		var sentMsgs = pi.interfaceItem.incoming.filter(m|m.data!=null)
		val enumMsgs = sentMsgs.filter(m|m.data.refType.type.isEnumeration)
		val boolMsgs = sentMsgs.filter(m|m.data.refType.type.isBoolean)
		val usesMSC = Main::settings.generateMSCInstrumentation && !(enumMsgs.empty && boolMsgs.empty)
		var enumVal = ""
		if (usesMSC) {
			enumVal = "\n, \""+ai.path+"\""
			for (msg : enumMsgs) {
				enumVal = enumVal + "\n, "+msg.data.refType.type.defaultValue
			}
			for (msg : boolMsgs) {
				enumVal = enumVal + "\n, "+msg.data.refType.type.defaultValue
			}
			enumVal = "\n#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE"+enumVal+"\n#endif\n"
		}
		
		if (pi.peers.empty)
			return "{NULL"+enumVal+"}"

		var peer = pi.peers.get(0)
		var peerInst = pi.peers.get(0).eContainer() as ActorInstance
		var instName = peerInst.path.pathName
			
		"{&"+instName+"."+peer.name+enumVal+"}"
	}
	
	def private String genReplSubPortInitializers(Root root, ActorInstance ai, InterfaceItemInstance pi) {
		var result = ""
		val myInst = if (Main::settings.generateMSCInstrumentation) "\n#ifdef ET_ASYNC_MSC_LOGGER_ACTIVATE\n,\""+(pi.eContainer as ActorInstance).path+"\"\n"
			else ""
		
		for (p: pi.peers) {
			val idx = pi.peers.indexOf(p)
			val comma = if (idx<pi.peers.size-1) "," else ""
			val thread = ETMapUtil::getMappedThread(p.eContainer as ActorInstance).thread.name
			var iiiD = getInterfaceItemInstanceData(pi)
			val peerInst = if (Main::settings.generateMSCInstrumentation) ",\""+(p.eContainer as ActorInstance).path+"\"\n#endif\n"
				else ""
			iiiD = if (iiiD.equals("NULL")) iiiD+"," else iiiD+"["+idx+"],"
			result = result +
				"{{"+iiiD 
				+"&msgService_"+thread+", "
				+p.objId+"+BASE_ADDRESS, "
				+(root.getExpandedActorClass(ai).getInterfaceItemLocalId(pi.interfaceItem)+1)
				+myInst
				+peerInst
				+"},"
				+idx
				+"}"+comma+" /* Repl Sub Port "+pi.name+" idx +"+idx+"*/\n"
		}
		
		return result
	}
	
	def private generateDispatcherFile(Root root, SubSystemInstance ssi, Collection<PhysicalThread> usedThreads) {
	val nr = ETMapUtil::getNodeRef(ssi)
	val loggedPorts = ssi.loggedPorts
	val logData = ssi.subSystemClass.annotations.isAnnotationPresent("DataLogging")
	
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Dispatcher File of Node �nr.name� with SubSystem �ssi.name�
		 * contains a generated message dispatcher (receiveMessage) for each MessageService (Thread)
		 */
		
		#include "messaging/etMessageReceiver.h"
		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		
		�FOR thread: nr.type.threads.filter(t|usedThreads.contains(t)) SEPARATOR "\n"�
			�val instancesOnThread = ssi.allContainedInstances.filter(ai|ETMapUtil::getMappedThread(ai).thread==thread)�
			�val dispatchedInstances = instancesOnThread.filter(ai|ai.actorClass.commType == ComponentCommunicationType::EVENT_DRIVEN || ai.actorClass.commType == ComponentCommunicationType::ASYNCHRONOUS)�
			�val executedInstances = instancesOnThread.filter(ai|ai.actorClass.commType == ComponentCommunicationType::DATA_DRIVEN || ai.actorClass.commType == ComponentCommunicationType::ASYNCHRONOUS)�
			
			�IF executedInstances.size > 0�
				/**
				 * generated execute function for all cyclic execute calls for the async or datadriven actor instances of thread "�thread.name�"
				 */
				static void MsgDispatcher_�thread.name�_poll(void){
					ET_MSC_LOGGER_SYNC_ENTRY("MsgDispatcher_�thread.name�", "execute")
					�FOR ai : executedInstances�
						�ai.actorClass.name�_execute((void*)&�ai.path.pathName�);
					�ENDFOR�
					ET_MSC_LOGGER_SYNC_EXIT
				}
				�IF logData�
					static void MsgDispatcher_�thread.name�_logDataHeaders(void){
						�FOR pi: loggedPorts�
							ET_DATA_LOGGER_LOG_STRING("�pi.path�")
						�ENDFOR�
					}
					static void MsgDispatcher_�thread.name�_logData(void){
						ET_DATA_LOGGER_NEW_ROW
						�FOR pi: loggedPorts�
							�pi.createLoggerCall�
						�ENDFOR�
					}
				�ENDIF�
			�ENDIF�
			
			/**
			 * generated dispatch function for all messages for the thread "�thread.name�"
			 */
			static etBool MsgDispatcher_�thread.name�_receiveMessage(const etMessage* msg){
				ET_MSC_LOGGER_SYNC_ENTRY("MsgDispatcher_�thread.name�", "receiveMessage")
				switch(msg->address){
				
					case MESSAGESERVICE_ADDRESS:
						�IF !executedInstances.empty�
							if (msg->evtID == etSystemProtocol_IN_poll) {
								MsgDispatcher_�thread.name�_poll();
								�IF logData�
									MsgDispatcher_�thread.name�_logData();
								�ENDIF�
							}
							else
						�ENDIF�
						if (msg->evtID == etSystemProtocol_IN_terminate)
							return ET_FALSE;
						break;
					�FOR ai : dispatchedInstances�
						
						/* interface items of �ai.path� */
						�FOR pi : ai. orderedIfItemInstances.filter(p|p.protocol.commType==CommunicationType::EVENT_DRIVEN)�
							�IF pi.replicated�
								�FOR peer: pi.peers�
									case �pi.objId+pi.peers.indexOf(peer)�+BASE_ADDRESS:
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
											�IF Main::settings.generateMSCInstrumentation�
												ET_MSC_LOGGER_ASYNC_IN(
													�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�].port.peerInstName,
													�pi.protocol.name�_getMessageString(msg->evtID),
													�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�].port.myInstName
													)
											�ENDIF�
											�ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,&�ai.path.pathName�_const.�pi.name�.ports[�pi.peers.indexOf(peer)�].port, msg);
										�ENDIF�
									break;
								�ENDFOR�
							�ELSE�
								case �pi.objId�+BASE_ADDRESS:
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
										�IF Main::settings.generateMSCInstrumentation�
											ET_MSC_LOGGER_ASYNC_IN(
												((etPort*)&�ai.path.pathName�_const.�pi.name�)->peerInstName,
												�pi.protocol.name�_getMessageString(msg->evtID),
												((etPort*)&�ai.path.pathName�_const.�pi.name�)->myInstName
												)
										�ENDIF�
										�ai.actorClass.name�_receiveMessage((void*)&�ai.path.pathName�,(etPort*)&�ai.path.pathName�_const.�pi.name�, msg);
									�ENDIF�
									break;
							�ENDIF�
						�ENDFOR�
					�ENDFOR�
					
					default:
						etLogger_logErrorF("MessageService_�thread.name�_receiveMessage: address %d does not exist ", msg->address);
						break;
				}
				ET_MSC_LOGGER_SYNC_EXIT
				return ET_TRUE;
			}
		�ENDFOR�
		'''
	}
	
	def private createLoggerCall(PortInstance pi) {
		val msg = pi.protocol.incomingMessages.filter(m|m.data!=null && m.data.refType.type.enumerationOrPrimitive).get(0)
		val ai = pi.eContainer as ActorInstance
		val data = ai.path.pathName+"."+pi.name+"."+msg.name
		val type = if (msg.data.refType.type instanceof EnumerationType) LiteralType.INT
					else (msg.data.refType.type as PrimitiveType).type
		
		switch (type) {
			case LiteralType.BOOL: "ET_DATA_LOGGER_LOG_BOOL((int)"+data+")"
			case LiteralType.CHAR: "ET_DATA_LOGGER_LOG_INT((int)"+data+")"
			case LiteralType.INT: "ET_DATA_LOGGER_LOG_INT((int)"+data+")"
			case LiteralType.REAL: "ET_DATA_LOGGER_LOG_DOUBLE((double)"+data+")"
			default: "internal error: unknown primitive type"
		}
	}
	
	def private loggedPorts(SubSystemInstance ssi) {
		val ArrayList<PortInstance> result = newArrayList
		
		if (ssi.subSystemClass.annotations.isAnnotationPresent("DataLogging")) {
			logger.logInfo("Data Logging is configured by annotation");
			
			val filters = ssi.subSystemClass.annotations.getAttribute("DataLogging", "pathlist")
			val filterList = filters.split(",")
			for (filter: filterList) {
				logger.logInfo("  filter: "+filter);
			}
			
			val ArrayList<String> notLogged = newArrayList
			
			logger.logInfo("  logged ports:");
			var iter = ssi.eAllContents
			while (iter.hasNext) {
				val obj = iter.next
				if (obj instanceof PortInstance) {
					val pi = obj as PortInstance
					if (!pi.port.relay) {
						// only data driven
						if (pi.protocol.commType==CommunicationType::DATA_DRIVEN) {
							// only send ports
							if (pi.port.conjugated) {
								val path = pi.path
								var boolean matched = false
								var primitive = false
								for (filter: filterList) {
									if (path.matches(filter)) {
										matched = true
										if (!pi.protocol.incomingMessages.filter(m|m.data!=null && m.data.refType.type.enumerationOrPrimitive).empty) {
											result.add(pi)
											primitive = true
											logger.logInfo("    data driven port "+pi.path+" (matched "+filter+")");
										}
									}
								}
								if (!primitive) {
									if (matched) {
										notLogged.add("    data driven port "+pi.path+" (matched but contains no primitive data)");
									}
									else {
										notLogged.add("    data driven port "+pi.path+" (no match found)");
									}
								}
							}
							else {
								notLogged.add("    data driven port "+pi.path+" (receive port)");
							}
						}
					}
				}
			}
			
			logger.logInfo("  NOT logged ports:");
			for (nl: notLogged) {
				logger.logInfo(nl);
			}
		}
		
		return result
	}
	
	def private checkDataPorts(SubSystemInstance comp) {
		val found = new HashSet<String>()
		for (ai: comp.allContainedInstances) {
			val thread = ai.threadId
			for (pi: ai.orderedIfItemInstances) {
				if (pi.protocol.commType==CommunicationType::DATA_DRIVEN) {
					for (peer: pi.peers) {
						val peer_ai = peer.eContainer as ActorInstance
						val peer_thread = peer_ai.threadId
						if (thread!=peer_thread) {
							val path = pi.path
							val ppath = peer.path
							val pair = if (path.compareTo(ppath)<0) path+" and "+ppath
										else ppath+" and "+path
							if (!found.contains(pair)) {
								found.add(pair)
								diagnostician.error(pair+": data ports placed on different threads (not supported yet)",
									pi.interfaceItem, pi.interfaceItem.eContainingFeature)
							}
						}
					}
				}
			}
		}
	}	
}
