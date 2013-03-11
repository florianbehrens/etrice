/*******************************************************************************
 * Copyright (c) 2011 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 * 
 *******************************************************************************/

package org.eclipse.etrice.generator.java.gen

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.etrice.core.genmodel.etricegen.Root
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.etrice.generator.generic.RoomExtensions
import org.eclipse.etrice.core.genmodel.etricegen.SystemInstance

@Singleton
class SubSystemRunnerGen {

	@Inject extension JavaIoFileSystemAccess fileAccess
	@Inject extension RoomExtensions roomExt
	
	def doGenerate(Root root) {
		for (sc: root.subSystemInstances) {
			fileAccess.setOutputPath(sc.subSystemClass.generationTargetPath+sc.subSystemClass.getPath)
			fileAccess.generateFile(sc.subSystemClass.name+"Runner.java", root.generate(sc))
		}
	}
	
	def generate(Root root, SubSystemInstance ssc) {
	val cc = ssc.subSystemClass
	'''
		/**
		 * @author generated by eTrice
		 *
		 * this class contains the main function running component �ssc.name�
		 * it instantiates �ssc.name� and starts and ends the lifecycle
		 */
		
		package �cc.getPackage()�;
		
		import org.eclipse.etrice.runtime.java.modelbase.RTSystem;
		import org.eclipse.etrice.runtime.java.modelbase.SubSystemRunnerBase;
		
		class �cc.name+"Runner"� extends SubSystemRunnerBase {
		
			/**
		     * main function
		     * creates the main component and starts and stops the lifecycle
		     */
			public static void main(String[] args) {
				// instantiate the main component
				RTSystem sys = �IF ssc.eContainer instanceof SystemInstance�new RTSystem("�(ssc.eContainer as SystemInstance).name�")�ELSE�null�ENDIF�;
				�cc.name� main_component = new �cc.name�(sys, "�ssc.name�");
				
				run(main_component, args);
			}
		};
	'''
	}
}