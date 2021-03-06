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
import org.eclipse.etrice.core.etmap.util.ETMapUtil
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.etrice.core.genmodel.etricegen.SystemInstance
import org.eclipse.etrice.generator.cpp.Main
import org.eclipse.etrice.generator.fsm.base.FileSystemHelpers
import org.eclipse.etrice.generator.fsm.base.IGeneratorFileIo
import org.eclipse.etrice.generator.generic.RoomExtensions

@Singleton
class NodeRunnerGen {

	@Inject extension RoomExtensions roomExt
	@Inject extension CppExtensions
	@Inject extension FileSystemHelpers

	@Inject IGeneratorFileIo fileIO

	def doGenerate(Root root) {
		for (nr : ETMapUtil::getNodeRefs()) {
			for (instpath : ETMapUtil::getSubSystemInstancePaths(nr)) {
				val ssi = root.getInstance(instpath) as SubSystemInstance
				if (ssi!=null && ssi.subSystemClass.validGenerationLocation) {
					val filepath = ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath
					val infopath = ssi.subSystemClass.generationInfoPath+ssi.subSystemClass.getPath
					fileIO.generateFile("generating SubSystemRunner declaration", filepath, infopath, nr.getCppClassName(ssi)+"Runner.h", root.generateHeaderFile(ssi))
					fileIO.generateFile("generating SubSystemRunner implementation", filepath, infopath, nr.getCppClassName(ssi)+"Runner.cpp", root.generateSourceFile(ssi))
				}
			}
		}
	}

	def generateHeaderFile(Root root, SubSystemInstance ssc) {
		val cc = ssc.subSystemClass
		val nr = ETMapUtil::getNodeRef(ssc)
		val clsname = nr.getCppClassName(ssc)
		'''
			/**
			 * @author generated by eTrice
			 *
			 * this class contains the main function running component �ssc.name�
			 * it instantiates �ssc.name� and starts and ends the lifecycle
			 */

			�generateIncludeGuardBegin(cc, 'Runner')�

			�cc.generateNamespaceBegin�

			#include "common/modelbase/SubSystemRunnerBase.h"

			class �clsname+"Runner"� : public etRuntime::SubSystemRunnerBase {

			};

			�cc.generateNamespaceEnd�

			�generateIncludeGuardEnd(cc, 'Runner')�
		'''
	}

	def generateSourceFile(Root root, SubSystemInstance ssc) {
		val cc = ssc.subSystemClass
		val nr = ETMapUtil::getNodeRef(ssc)
		val clsname = nr.getCppClassName(ssc)
		'''
			/**
			 * @author generated by eTrice
			 *
			 * this class contains the main function running component �ssc.name�
			 * it instantiates �ssc.name� and starts and ends the lifecycle
			 */


			#include "�clsname�Runner.h"

			#include "�clsname�.h"
			#include "common/modelbase/RTSystem.h"
			#include "common/containers/String.h"
			#include "common/containers/Vector.h"
			#include "common/containers/Set.h"
			#include "common/containers/Map.h"

			using namespace etRuntime;

			�cc.generateNamespaceBegin�

			/**
			 * �Main.getSettings.mainMethodName� function
			 * creates components and starts and stops the lifecycle
			 */
			int �Main.getSettings.mainMethodName�(int argc, char* argv[]) {
				// instantiate the main component
				RTSystem* sys = �IF ssc.eContainer instanceof SystemInstance�new RTSystem("�(ssc.eContainer as SystemInstance).name�")�ELSE�0�ENDIF�;
				�clsname�* main_component = new �clsname�(sys, "�ssc.name�");

				�clsname�Runner::run(*main_component, argc, argv);

���				// TODO JH crash
				//delete sys;
				
				printf("String allocations %d and deallocations %d\n", etRuntime::String::getNAllocations(), etRuntime::String::getNDeallocations());
				printf("String creations %d and destructions %d\n", etRuntime::String::getNCreated(), etRuntime::String::getNDestroyed());
				printf("Vector allocations %d and deallocations %d\n", etRuntime::VectorStats::getNAllocations(), etRuntime::VectorStats::getNDeallocations());
				printf("Vector creations %d and destructions %d\n", etRuntime::VectorStats::getNCreated(), etRuntime::VectorStats::getNDestroyed());
				printf("Set creations %d and destructions %d\n", etRuntime::SetStats::getNCreated(), etRuntime::SetStats::getNDestroyed());
				printf("Map creations %d and destructions %d\n", etRuntime::MapStats::getNCreated(), etRuntime::MapStats::getNDestroyed());
				fflush(stdout);

				return 0;
			}

			�cc.generateNamespaceEnd�

		'''
	}
}