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
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.core.etmap.util.ETMapUtil

@Singleton
class NodeRunnerGen {

	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension CExtensions
	@Inject extension RoomExtensions
	
	def doGenerate(Root root) {
		var first = true
		for (nr : ETMapUtil::getNodeRefs()) {
			for (instpath : ETMapUtil::getSubSystemInstancePaths(nr)) {
				val ssi = root.getInstance(instpath) as SubSystemInstance
				val clsname = nr.name+"_"+ssi.name
				fileAccess.setOutputPath(ssi.subSystemClass.generationTargetPath+ssi.subSystemClass.getPath)
				fileAccess.generateFile( clsname+"_Runner.c", root.generateSourceFile(ssi, first))
				first = false
			}
		}
	}
	
	def generateSourceFile(Root root, SubSystemInstance ssi, boolean first) {
	val nr = ETMapUtil::getNodeRef(ssi)
	val clsname = nr.name+"_"+ssi.name
	'''
		/**
		 * @author generated by eTrice
		 *
		 * this class contains the main function running Node �nr.name� with SubSystem �ssi.name�
		 * it instantiates Node �nr.name� with SubSystem �ssi.name� and starts and ends the lifecycle
		 */
		
		#include <string.h>
		
		#include "�nr.getCHeaderFileName(ssi)�"

		#include "debugging/etLogger.h"
		#include "debugging/etMSCLogger.h"
		
		#include "osal/etPlatformLifecycle.h"
		
		/**
		 * main function
		 * creates component and starts and stops the lifecycle
		 */
		
		int main(int argc, char** argv) {
			etBool runAsTest = FALSE;
		
			if (argc>1 && strcmp(argv[1], "-headless")==0)
				runAsTest = TRUE;
			if (argc>1 && strcmp(argv[1], "-run_as_test")==0)
				runAsTest = TRUE;
		
			etUserEntry(); /* platform specific */
			
			etLogger_logInfo("***   T H E   B E G I N   ***");
			ET_MSC_LOGGER_OPEN("main");
		
			/* startup sequence  of lifecycle */
			�clsname�_init(); 		/* lifecycle init */
			�clsname�_start(); 	/* lifecycle start */
		
			etUserPreRun(); /* platform specific */

			/* run Scheduler */
			�clsname�_run(runAsTest);
		
			etUserPostRun(); /* platform specific */

			/* shutdown sequence of lifecycle */
			�clsname�_stop(); 		/* lifecycle stop */
			�clsname�_destroy(); 	/* lifecycle destroy */
		
			ET_MSC_LOGGER_CLOSE
			etLogger_logInfo("***   T H E   E N D   ***");
		
			etUserExit(); /* platform specific */
		
			return 0;
		}
		
	'''
	}	
}