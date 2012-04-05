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
import org.eclipse.etrice.core.room.ActorClass
import org.eclipse.etrice.core.room.ActorCommunicationType
import org.eclipse.etrice.core.room.CommunicationType
import org.eclipse.etrice.generator.base.ILogger
import org.eclipse.etrice.generator.etricegen.ExpandedActorClass
import org.eclipse.etrice.generator.etricegen.Root
import org.eclipse.xtext.generator.JavaIoFileSystemAccess

import org.eclipse.etrice.generator.extensions.RoomExtensions
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.TypeHelpers
import org.eclipse.etrice.generator.generic.GenericActorClassGenerator


@Singleton
class ActorClassGen extends GenericActorClassGenerator {
	
	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions stdExt
	@Inject extension RoomExtensions roomExt
	
	@Inject extension ProcedureHelpers helpers
	@Inject extension TypeHelpers
	@Inject extension StateMachineGen stateMachineGen
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (xpac: root.xpActorClasses) {
			var path = xpac.actorClass.generationTargetPath+xpac.actorClass.getPath
			
			// header file
			logger.logInfo("generating ActorClass header '"+xpac.actorClass.getCHeaderFileName+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(xpac.actorClass.getCHeaderFileName, root.generateHeaderFile(xpac, xpac.actorClass))

			// source file
			if (hasBehaviorAnnotation(xpac, "BehaviorManual")) {
				logger.logInfo("omitting ActorClass source for '"+xpac.actorClass.name+"' since @BehaviorManual is specified")
			}
			else {
				logger.logInfo("generating ActorClass source '"+xpac.actorClass.getCSourceFileName +"' in '"+path+"'")
				fileAccess.setOutputPath(path)
				fileAccess.generateFile(xpac.actorClass.getCSourceFileName , root.generateSourceFile(xpac, xpac.actorClass))
			}
		}
	}
	
	def private hasBehaviorAnnotation(ExpandedActorClass xpac, String annotation) {
		if (xpac.actorClass.annotations != null){
			if(xpac.actorClass.annotations.findFirst(e|e.name == annotation) != null){
				return true;
			}
		}
		return false;		
	}
	
	def private generateHeaderFile(Root root, ExpandedActorClass xpac, ActorClass ac) {
		var eventPorts = ac.allEndPorts.filter(p|p.protocol.commType==CommunicationType::EVENT_DRIVEN)
		var sendPorts = ac.allEndPorts.filter(p|p.protocol.commType==CommunicationType::DATA_DRIVEN && p.conjugated)
		var recvPorts = ac.allEndPorts.filter(p|p.protocol.commType==CommunicationType::DATA_DRIVEN && !p.conjugated)
		var dataDriven = ac.commType==ActorCommunicationType::DATA_DRIVEN
		var async = ac.commType==ActorCommunicationType::ASYNCHRONOUS
		
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Header File of ActorClass �xpac.name�
		 * 
		 */

		�generateIncludeGuardBegin(xpac.name)�
		
		#include "etDatatypes.h"
		#include "messaging/etMessage.h"
		
		�FOR dataClass : root.getReferencedDataClasses(ac)�
			#include "�dataClass.name�.h"
		�ENDFOR�
		�FOR pc : root.getReferencedProtocolClasses(ac)�
			#include "�pc.name�.h"
		�ENDFOR�
		
		�helpers.userCode(ac.userCode1)�
		
		typedef struct �xpac.name� �xpac.name�;
		
		/* const part of ActorClass (ROM) */
		�IF eventPorts.empty && recvPorts.empty && ac.allSAPs.empty && ac.allServiceImplementations.empty�
			/* this actor class has no ports and thus no constant data */
		�ELSE�
			typedef struct �xpac.name�_const {
				/* simple ports */
				�FOR ep : eventPorts�
					�IF ep.multiplicity==1�
						const �ep.getPortClassName()� �ep.name�;
					�ENDIF�
				�ENDFOR�
				
				/* data receive ports */
				�FOR ep : recvPorts�
					�IF ep.multiplicity==1�
						const �ep.getPortClassName()� �ep.name�;
					�ENDIF�
				�ENDFOR�

				/* saps */
				�FOR sap: ac.allSAPs�
					const �sap.getPortClassName()� �sap.name�;
				�ENDFOR�
				
				/* replicated ports */
				�FOR ep : ac.allEndPorts�
					�IF ep.multiplicity!=1�
						const etReplPort �ep.name�;
					�ENDIF�
				�ENDFOR�
				
				/* services */
				�FOR svc : ac.allServiceImplementations�
					const etReplPort �svc.spp.name�;
				�ENDFOR�
			} �xpac.name�_const;
		�ENDIF�
		
		�IF xpac.hasNonEmptyStateMachine�
			
			�stateMachineGen.genHeaderConstants(xpac, ac)�
		�ENDIF�
		
		/* variable part of ActorClass (RAM) */
		struct �xpac.name� {
			�IF !(eventPorts.empty && recvPorts.empty && ac.allSAPs.empty && ac.allServiceImplementations.empty)�
				const �xpac.name�_const* const constData;
				
			�ENDIF�
			/* data send ports */
			�FOR ep : sendPorts�
				�IF ep.multiplicity==1�
					�ep.getPortClassName()� �ep.name�;
				�ENDIF�
			�ENDFOR�

			�helpers.attributes(ac.allAttributes)�

		�FOR a:ac.allAttributes�
			�IF a.defaultValueLiteral!=null�
				�logger.logInfo(ac.name+" "+a.name+": Attribute initialization not supported in C")�
			�ENDIF�
		�ENDFOR�
			
			�IF xpac.hasNonEmptyStateMachine�
			
				�stateMachineGen.genDataMembers(xpac, ac)�
			�ENDIF�
		};

		void �xpac.name�_init(�xpac.name�* self);

		void �xpac.name�_receiveMessage(void* self, void* ifitem, const etMessage* msg);
		
		�IF dataDriven || async�
			void �xpac.name�_execute(�xpac.name�* self);
		�ENDIF�
		
		�helpers.operationsDeclaration(ac.operations, ac.name)�
		
		�helpers.userCode(ac.userCode2)�
		
		�generateIncludeGuardEnd(xpac.name)�
		
	'''
	}
	
	def private generateSourceFile(Root root, ExpandedActorClass xpac, ActorClass ac) {
		var async = ac.commType==ActorCommunicationType::ASYNCHRONOUS
		var eventDriven = ac.commType==ActorCommunicationType::EVENT_DRIVEN
		var dataDriven = ac.commType==ActorCommunicationType::DATA_DRIVEN
		var handleEvents = async || eventDriven
		
	'''
		/**
		 * @author generated by eTrice
		 *
		 * Source File of ActorClass �xpac.name�
		 * 
		 */

		#include "�xpac.getCHeaderFileName�"
		
		#include "modelbase/etActor.h"
		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		#include "platform/etMemory.h"

		�FOR pc : root.getReferencedProtocolClasses(ac)�
			#include "�pc.getCHeaderFileName�"
		�ENDFOR�
		
		�helpers.userCode(ac.userCode3)�

		/* interface item IDs */
		�genInterfaceItemConstants(xpac, ac)�

		�IF xpac.hasNonEmptyStateMachine�
			�stateMachineGen.genStateMachine(xpac, ac)�
		�ENDIF�
		
		void �xpac.name�_init(�xpac.name�* self){
			ET_MSC_LOGGER_SYNC_ENTRY("�xpac.name�", "init")
			�IF xpac.hasNonEmptyStateMachine�
				�stateMachineGen.genInitialization(xpac, ac)�
			�ENDIF�
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		
		void �xpac.name�_receiveMessage(void* self, void* ifitem, const etMessage* msg){
			ET_MSC_LOGGER_SYNC_ENTRY("�xpac.name�", "_receiveMessage")
			�IF xpac.hasNonEmptyStateMachine�
				
				receiveEvent(self�IF handleEvents�, (etPort*)ifitem, msg->evtID, (void*)(((char*)msg)+MEM_CEIL(sizeof(etMessage)))�ENDIF�);
			�ENDIF�
			
			ET_MSC_LOGGER_SYNC_EXIT
		}
		
		�IF dataDriven || async�
			void �xpac.name�_execute(�xpac.name�* self) {
				ET_MSC_LOGGER_SYNC_ENTRY("�xpac.name�", "_execute")
				�IF xpac.hasNonEmptyStateMachine�
					
					receiveEvent(self�IF handleEvents�, NULL, 0, NULL�ENDIF�);
				�ENDIF�
				
				ET_MSC_LOGGER_SYNC_EXIT
			}
		�ENDIF�
		
		�helpers.operationsImplementation(ac)�
		
		'''
	}
}
