/*******************************************************************************
 * Copyright (c) 2011 Draeger Medical GmbH (http://www.draeger.com).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Peter Karlitschek (initial contribution)
 * 
 *******************************************************************************/

package org.eclipse.etrice.generator.cpp.gen

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.etrice.core.genmodel.base.ILogger
import org.eclipse.etrice.core.genmodel.etricegen.ActorInstance
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.etrice.core.room.SubSystemClass
import org.eclipse.etrice.generator.generic.ProcedureHelpers
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.xtext.generator.JavaIoFileSystemAccess

import static extension org.eclipse.etrice.generator.base.Indexed.*
import org.eclipse.etrice.generator.base.IDataConfiguration

/**
 * @author Peter Karlitschek
 *
 */
@Singleton
class SubSystemClassGen {
	
	@Inject JavaIoFileSystemAccess fileAccess
	@Inject extension CppExtensions
	@Inject extension RoomExtensions
	@Inject extension ProcedureHelpers
	@Inject IDataConfiguration dataConfigExt
	@Inject ConfigGenAddon configGenAddon
	@Inject ConfigGenAddon configAddon
	@Inject ILogger logger
	
	def doGenerate(Root root) {
		for (ssi: root.subSystemInstances) {
			var path = ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath
			var file = ssi.subSystemClass.getCppHeaderFileName
			logger.logInfo("generating SubSystemClass declaration: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateHeaderFile(ssi, ssi.subSystemClass))
			
			file = ssi.subSystemClass.getCppSourceFileName
			logger.logInfo("generating SubSystemClass implementation: '"+file+"' in '"+path+"'")
			fileAccess.setOutputPath(path)
			fileAccess.generateFile(file, root.generateSourceFile(ssi, ssi.subSystemClass))
			
//			file = ssi.subSystemClass.getInstSourceFileName
//			logger.logInfo("generating SubSystemClass instance file: '"+file+"' in '"+path+"'")
//			fileAccess.setOutputPath(path)
//			fileAccess.generateFile(file, root.generateInstanceFile(ssi, ssi.subSystemClass))
//
//			file = ssi.subSystemClass.getDispSourceFileName
//			logger.logInfo("generating SubSystemClass dispatcher file: '"+file+"' in '"+path+"'")
//			fileAccess.setOutputPath(path)
//			fileAccess.generateFile(file, root.generateDispatcherFile(ssi, ssi.subSystemClass))
		}
	}

	def generateHeaderFile(Root root, SubSystemInstance comp, SubSystemClass cc) {'''
		
		/**
		 * @author generated by eTrice
		 *
		 * Header File of SubSystemClass �cc.name�
		 * 
		 */

		�generateIncludeGuardBegin(cc.name)�
	
		#include "platforms/generic/etDatatypes.h"
		#include "common/messaging/IRTObject.h"
		#include "common/modelbase/PortBase.h"
		#include "common/modelbase/InterfaceItemBase.h"
		#include "common/modelbase/ActorClassBase.h"
		#include "common/modelbase/SubSystemClassBase.h"
		#include "common/messaging/Address.h"
		#include "common/messaging/IMessageReceiver.h"
		#include "common/debugging/DebuggingService.h"
		#include <string>
		#include <vector>
		
		�FOR model : root.getReferencedModels(cc)�
���			#include "�model.name�.h"
		�ENDFOR�
		
		
		�cc.userCode(1, false)�
		
		class �cc.name� : public etRuntime::SubSystemClassBase{
		
			�cc.userCode(2, false)�
			
			public:
				�cc.name�(IRTObject* parent, std::string name)
					: etRuntime::SubSystemClassBase(parent, name)
				{
				}
				
				virtual void receiveEvent(etRuntime::InterfaceItemBase* ifitem, int evt, void* data);
				virtual void instantiateMessageServices();
				virtual void instantiateActors();
				
			private:
				std::vector<etRuntime::MessageService*> m_msgServices;
		};
		
		�generateIncludeGuardEnd(cc.name)�
	'''
	}
	
	def generateSourceFile(Root root, SubSystemInstance comp, SubSystemClass cc) {'''
		
		/**
		 * @author generated by eTrice
		 *
		 * Source File of SubsystemClass �cc.name�
		 * 
		 */

		#include "�cc.getCppHeaderFileName�"
		#include "common/debugging/DebuggingService.h"
		#include "common/messaging/RTSystemServicesProtocol.h"
		�FOR ai : comp.allContainedInstances�
			#include "�ai.actorClass.path��ai.actorClass.name�.h"
		�ENDFOR�
		#include <iostream>
		
		using namespace etRuntime;
		
		void �cc.name�::receiveEvent(InterfaceItemBase* ifitem, int evt, void* data){
		}
			
		void �cc.name�::instantiateMessageServices(){
		
			m_msgServices.push_back( new MessageService(this, Address(0, 0, 0),"MessageService_Main") );
			�FOR thread : cc.threads�
				m_msgServices.push_back(new MessageService(this, Address(0, �cc.threads.indexOf(thread)+1�, 0),"MessageService_�thread.name�", /* threadprio */ 0));
			�ENDFOR�
			for (std::vector<MessageService*>::iterator it=m_msgServices.begin(); it != m_msgServices.end(); ++it) {
				RTServices::getInstance().getMsgSvcCtrl().addMsgSvc( *(*it));
			}
		}
		
		void �cc.name�::instantiateActors(){
		// all addresses
		// Addresses for the Subsystem Systemport
		�FOR ai : comp.allContainedInstances.indexed(comp.maxObjId)�
			Address addr_item_SystemPort_�comp.allContainedInstances.indexOf(ai.value)�(0,0,�ai.index1�);
		�ENDFOR�
		
		�FOR ai : comp.allContainedInstances�
			// actor instance �ai.path� itself => Systemport Address
			// TODOTJ: For each Actor, multiple addresses should be generated (actor?, systemport, debugport)
			Address addr_item_�ai.path.getPathName()�(0,�ai.threadId�,�ai.objId�);
			// interface items of �ai.path�
			�FOR pi : ai.orderedIfItemInstances�
				�IF pi.replicated�
					�FOR peer : pi.peers�
						�var i = pi.peers.indexOf(peer)�
						Address addr_item_�pi.path.getPathName()�_�i�(0,�pi.threadId�,�pi.objId+i�);
					�ENDFOR�
				�ELSE�
					Address addr_item_�pi.path.getPathName()�(0,�ai.threadId�,�pi.objId�);
				�ENDIF�
			�ENDFOR�
		�ENDFOR�

		// instantiate all actor instances
		m_instances.reserve(�comp.allContainedInstances.size�);
		�FOR ai : comp.allContainedInstances�
			//----------------------------------------------------------------------------------------------
			// addresses for actor instance: �ai.name�
			//----------------------------------------------------------------------------------------------
			�generateOwnInterfaceItemAddresses(ai)�
			�generatePeerInterfaceItemAddresses(ai, comp)�
			
			m_instances[�comp.allContainedInstances.indexOf(ai)�] = new �ai.actorClass.name�(
				�IF ai.eContainer instanceof SubSystemInstance�
					this,
				�ELSE�
					m_instances[�comp.allContainedInstances.indexOf(ai.eContainer)�],
				�ENDIF�
				"�ai.name�",
				�ai.name�_ownInterfaceItemAddresses,
				�ai.name�_peerInterfaceItemAddresses
			); 
		�ENDFOR�
		
���	// apply instance attribute configurations
���	�FOR ai : comp.allContainedInstances�
���		�IF !(ai.configAttributes.empty && ai.getConfigPorts.empty)�
���			{
���				�ai.actorClass.name� inst = (�ai.actorClass.name�) instances[�comp.allContainedInstances.indexOf(ai)�];
���				�configAddon.applyInstanceConfig("inst", ai.actorClass.name, ai.configAttributes)�
���				�FOR portConfig : ai.configPorts�
���					�configAddon.applyInstanceConfig(("inst."+portConfig.item.name.invokeGetter(ai.actorClass.name)), portConfig.item.portClassName, portConfig.attributes)�
���				�ENDFOR�
���			}
���		�ENDIF�
���	�ENDFOR�

	// apply instance attribute configurations
	�FOR ai: comp.allContainedInstances�
		�val cfg = configGenAddon.genActorInstanceConfig(ai, "inst")�
		�IF cfg.length>0�
			{
				�ai.actorClass.name� inst = (�ai.actorClass.name�) instances[�comp.allContainedInstances.indexOf(ai)�];
				�cfg�
			}
		�ENDIF�
	�ENDFOR�

		//----------------------------------------------------------------------------------------------
		// addresses for the subsystem system port
		//----------------------------------------------------------------------------------------------

		std::vector<Address> ownAddresses(�comp.allContainedInstances.size�);
		�FOR ai : comp.allContainedInstances�
			ownAddresses[�comp.allContainedInstances.indexOf(ai)�] = addr_item_SystemPort_�comp.allContainedInstances.indexOf(ai)�;
		�ENDFOR�
		std::vector<Address> peerAddresses(�comp.allContainedInstances.size�);
		�FOR ai : comp.allContainedInstances�
			peerAddresses[�comp.allContainedInstances.indexOf(ai)�] = addr_item_�ai.path.getPathName()�;
		�ENDFOR�
		
		
		// create the subsystem system port	
		m_RTSystemPort = new RTSystemServicesProtocolConjPortRepl(*this, this, "RTSystemPort",
				0, //local ID
				ownAddresses,
				peerAddresses);
				
	}
		
		
	'''
	}
	def generateOwnInterfaceItemAddresses(ActorInstance ai) ''' 
		std::vector<std::vector<Address> > �ai.name�_ownInterfaceItemAddresses;
		
		std::vector<Address> �ai.name�_actorInstanceAddresses(1);
		�ai.name�_actorInstanceAddresses[0] = addr_item_�ai.path.getPathName()�;
		
		�ai.name�_ownInterfaceItemAddresses.push_back(�ai.name�_actorInstanceAddresses);
		�FOR pi : ai.orderedIfItemInstances�
			�IF pi.replicated�
				�IF pi.peers.empty�
					std::vector<Address>  �ai.name�_�pi.name�Addresses;
				�ELSE�
					std::vector<Address>  �ai.name�_�pi.name�Addresses(�pi.peers.size�);
					�FOR peer : pi.peers�
						�ai.name�_�pi.name�Addresses[�pi.peers.indexOf(peer)�] = addr_item_�pi.path.getPathName()�_�pi.peers.indexOf(peer)�;
					�ENDFOR�
				�ENDIF�
			�ELSE�
				std::vector<Address>  �ai.name�_�pi.name�Addresses(1);
				�ai.name�_�pi.name�Addresses[0] = addr_item_�pi.path.getPathName()�;
			�ENDIF�
			�ai.name�_ownInterfaceItemAddresses.push_back(�ai.name�_�pi.name�Addresses);
			
		�ENDFOR�
	'''

	def generatePeerInterfaceItemAddresses(ActorInstance ai, SubSystemInstance comp) ''' 
		std::vector<std::vector<Address> > �ai.name�_peerInterfaceItemAddresses;
		
		std::vector<Address> �ai.name�_systemPortAddresses(1);
		�ai.name�_systemPortAddresses[0] = addr_item_SystemPort_�comp.allContainedInstances.indexOf(ai)�;
		
		�ai.name�_peerInterfaceItemAddresses.push_back(�ai.name�_systemPortAddresses);
		�FOR pi : ai.orderedIfItemInstances �
			�IF pi.replicated && pi.peers.isEmpty�
			�ELSE�
				�IF pi.peers.empty�
					std::vector<Address>  �ai.name�_�pi.name�PeerAddresses;
				�ELSE�
					std::vector<Address>  �ai.name�_�pi.name�PeerAddresses(�pi.peers.size�);
					�FOR pp : pi.peers�
						�IF pp.replicated�
							�ai.name�_�pi.name�PeerAddresses[�pi.peers.indexOf(pp)�] = addr_item_�pp.path.getPathName()�_�pp.peers.indexOf(pi)�;
						�ELSE�
							�ai.name�_�pi.name�PeerAddresses[�pi.peers.indexOf(pp)�] = addr_item_�pp.path.getPathName()�;
						�ENDIF�
					�ENDFOR�
				�ENDIF�
				�ai.name�_peerInterfaceItemAddresses.push_back(�ai.name�_�pi.name�PeerAddresses);
			�ENDIF�
		�ENDFOR�
		'''
	
}