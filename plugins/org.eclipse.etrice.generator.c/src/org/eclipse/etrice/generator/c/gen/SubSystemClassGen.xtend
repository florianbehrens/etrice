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
import org.eclipse.etrice.core.room.SubSystemClass
import org.eclipse.etrice.generator.base.ILogger
import org.eclipse.etrice.generator.etricegen.Root
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
		
		
		/* lifecycle functions
		 * init -> start -> run (loop) -> stop -> destroy
		 */
		
		void �ssc.name�_init(void);		/* lifecycle init  	 */
		void �ssc.name�_start(void);	/* lifecycle start 	 */
		
		void �ssc.name�_run(void);		/* lifecycle run 	 */
		
		void �ssc.name�_stop(void); 	/* lifecycle stop	 */
		void �ssc.name�_destroy(void); 	/* lifecycle destroy */
		
		
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

		#include "etLogger.h"
		#include "etMSCLogger.h"
		
		
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

		#include "etMessageService.h"
		
		/* instantiation of message services */
		#define MESSAGE_POOL_MAX 10
		#define MESSAGE_BLOCK_SIZE 32
		/* MessageService for Thread1 */
		uint8 msgBuffer_Thread1[MESSAGE_POOL_MAX*MESSAGE_BLOCK_SIZE];
		etMessageService msgService_Thread1;


		/* include all used ActorClasses */
���		TODO: only include used Actor Classes for current SubSystem
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
			static const �ai.actorClass.name�_const �ai.path.getPathName()�_const = {
				&�ai.path.getPathName()�,
				/* Ports: {myActor, etReceiveMessage, msgService, peerAddress, localId} */
				�FOR pi : ai.ports�
���				TODO: not robust if not connected and not implemented for replication
				{&�ai.path.getPathName()�, �ai.actorClass.name�_ReceiveMessage, &msgService_Thread1, �pi.peers.get(0).objId�, 123} /* Port �pi.name� */
				�ENDFOR�
				
			};
			static �ai.actorClass.name� �ai.path.getPathName()� = {&�ai.path.getPathName()�_const};
		�ENDFOR�
		
		
		

���		�FOR ai : ssi.allContainedInstances�
���			// actor instance �ai.path� itself => Systemport Address
���			// TODOTJ: For each Actor, multiple addresses should be generated (actor?, systemport, debugport)
���			Address addr_item_�ai.path.getPathName()� = new Address(0,�ai.threadId�,�ai.objId�);
���			// interface items of �ai.path�
���			�FOR pi : ai.orderedIfItemInstances�
���				�IF pi instanceof ServiceImplInstance || pi.peers.size>1�
���					�FOR peer : pi.peers�
���						�var i = pi.peers.indexOf(peer)�
���						Address addr_item_�pi.path.getPathName()�_�i� = new Address(0,�pi.threadId�,�pi.objId+i�);
���					�ENDFOR�
���				�ELSE�
���					Address addr_item_�pi.path.getPathName()� = new Address(0,�ai.threadId�,�pi.objId�);
���				�ENDIF�
���			�ENDFOR�
���		�ENDFOR�
		
	'''
	}
	def generateDispatcherFile(Root root, SubSystemInstance ssi, SubSystemClass ssc) {'''
		/**
		 * @author generated by eTrice
		 *
		 * Dispatcher File of SubSystemClass �ssc.name�
		 * - one generated dispatcher for each MessageService (Thread)
		 */

		#include "etMessageReceiver.h"
		#include "etLogger.h"
		#include "etMSCLogger.h"
		
		static void MsgDispatcher_Thread1_receiveMessage(const etMessage* msg){
			ET_MSC_LOGGER_SYNC_ENTRY("MsgDispatcher_Thread1", "receiveMessage")
			switch(msg->address){
			
				�FOR ai : ssi.allContainedInstances�
					/* interface items of �ai.path� */
					�FOR pi : ai.orderedIfItemInstances�
						case �pi.objId�:
							etPort_receive(&�ai.path.pathName�_const.�pi.name�, msg);
							break;
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

//		�var models = root.getReferencedModels(ssc)�
//		�FOR model : models�import �model.name�.*;�ENDFOR�
//		
//		
//		�helpers.UserCode(ssc.userCode1)�
//		
//		public class �ssi.name� extends SubSystemClassBase{
//		
//			�helpers.UserCode(ssc.userCode2)�
//			
//			public �ssi.name�(IRTObject parent, String name) {
//				super(parent, name);
//			}
//			
//			@Override
//			public void receiveEvent(InterfaceItemBase ifitem, int evt, Object data){
//			}
//			
//			@Override	
//			public void instantiateMessageServices(){
//			
//				RTServices.getInstance().getMsgSvcCtrl().addMsgSvc(new MessageService(this, new Address(0, 0, 0),"MessageService_Main"));
//				�FOR thread : ssc.threads�
//					RTServices.getInstance().getMsgSvcCtrl().addMsgSvc(new MessageService(this, new Address(0, �ssc.threads.indexOf(thread)+1�, 0),"MessageService_�thread.name�", �thread.prio�));
//				�ENDFOR�
//				}
//		
//			@Override
//			public void instantiateActors(){
//				// all addresses
//				// Addresses for the Subsystem Systemport
//				�FOR ai : ssi.allContainedInstances.indexed(ssi.maxObjId)�
//					Address addr_item_SystemPort_�ssi.allContainedInstances.indexOf(ai.value)� = new Address(0,0,�ai.index1�);
//				�ENDFOR�
//				
//				�FOR ai : ssi.allContainedInstances�
//					// actor instance �ai.path� itself => Systemport Address
//					// TODOTJ: For each Actor, multiple addresses should be generated (actor?, systemport, debugport)
//					Address addr_item_�ai.path.getPathName()� = new Address(0,�ai.threadId�,�ai.objId�);
//					// interface items of �ai.path�
//					�FOR pi : ai.orderedIfItemInstances�
//						�IF pi instanceof ServiceImplInstance || pi.peers.size>1�
//							�FOR peer : pi.peers�
//								�var i = pi.peers.indexOf(peer)�
//								Address addr_item_�pi.path.getPathName()�_�i� = new Address(0,�pi.threadId�,�pi.objId+i�);
//							�ENDFOR�
//						�ELSE�
//							Address addr_item_�pi.path.getPathName()� = new Address(0,�ai.threadId�,�pi.objId�);
//						�ENDIF�
//					�ENDFOR�
//				�ENDFOR�
//		
//				// instantiate all actor instances
//				instances = new ActorClassBase[�ssi.allContainedInstances.size�];
//				�FOR ai : ssi.allContainedInstances�
//					instances[�ssi.allContainedInstances.indexOf(ai)�] = new �ai.actorClass.name�(
//						�IF ai.eContainer instanceof SubSystemInstance�
//							this,
//						�ELSE�
//							instances[�ssi.allContainedInstances.indexOf(ai.eContainer)�],
//						�ENDIF�
//						"�ai.name�",
//						// own interface item addresses
//						new Address[][] {{addr_item_�ai.path.getPathName()�}�IF !ai.orderedIfItemInstances.empty�,�ENDIF�
//						�FOR pi : ai.orderedIfItemInstances SEPARATOR ","�
//							{
//								�IF pi instanceof ServiceImplInstance || pi.peers.size>1�
//									�FOR peer : pi.peers SEPARATOR ","�
//										addr_item_�pi.path.getPathName()�_�pi.peers.indexOf(peer)�
//									�ENDFOR�
//								�ELSE�
//									addr_item_�pi.path.getPathName()�
//								�ENDIF�
//							}
//						�ENDFOR�
//						},
//						// peer interface item addresses
//						new Address[][] {{addr_item_SystemPort_�ssi.allContainedInstances.indexOf(ai)�}�IF !ai.orderedIfItemInstances.empty�,�ENDIF�
//							�FOR pi : ai.orderedIfItemInstances SEPARATOR ","�
//							{
//								�IF !(pi instanceof ServiceImplInstance) && pi.peers.isEmpty�
//									null
//								�ELSE�
//									�FOR pp : pi.peers SEPARATOR ","�
//										�IF pp instanceof ServiceImplInstance || pp.peers.size>1�
//											addr_item_�pp.path.getPathName()�_�pp.peers.indexOf(pi)�
//										�ELSE�
//											addr_item_�pp.path.getPathName()�
//										�ENDIF�
//									�ENDFOR�
//								�ENDIF�
//							}
//							�ENDFOR�
//						}
//					); 
//				�ENDFOR�
//		
//				// create the subsystem system port	
//				RTSystemPort = new RTSystemServicesProtocolConjPortRepl(this, "RTSystemPort",
//						0, //local ID
//						// own addresses
//						new Address[]{
//							�FOR ai : ssi.allContainedInstances SEPARATOR ","�
//								addr_item_SystemPort_�ssi.allContainedInstances.indexOf(ai)�
//							�ENDFOR�
//						},
//						// peer addresses
//						new Address[]{
//							�FOR ai : ssi.allContainedInstances SEPARATOR ","�
//								addr_item_�ai.path.getPathName()�
//							�ENDFOR�
//						});
//						
//			}
//		};

	
}