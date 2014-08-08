/*******************************************************************************
 * Copyright (c) 2014 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert
 * 
 *******************************************************************************/

package org.eclipse.etrice.core.fsm.postprocessing

import org.eclipse.xtext.GeneratedMetamodel

import org.eclipse.emf.ecore.EcorePackage

import static extension org.eclipse.etrice.core.common.postprocessing.PostprocessingHelpers.*

class ImplPostprocessor {
	
	def process(GeneratedMetamodel metamodel) {
		val fsmPackage = metamodel.getEPackage
		
		val state = fsmPackage.getClass("State")
		state.addOperation("getName", EcorePackage::eINSTANCE.getEClassifier("EString"), 1,
		// HOWTO: putting a fully qualified type name in <% %> makes ecore generate an import statement
			'''return (this instanceof <%org.eclipse.etrice.core.fsm.fSM.SimpleState%>)? ((SimpleState)this).getName() :(this instanceof <%org.eclipse.etrice.core.fsm.fSM.RefinedState%>)? (((RefinedState)this).getTarget()==null? "":((RefinedState)this).getTarget().getName()) :"";''')
		
		val stateGraphItem = fsmPackage.getClass("StateGraphItem")
		stateGraphItem.addOperation("getName", EcorePackage::eINSTANCE.getEClassifier("EString"), 1,
			'''
			if (this instanceof <%org.eclipse.etrice.core.fsm.fSM.State%>) 
				return ((State)this).getName();
			else if (this instanceof <%org.eclipse.etrice.core.fsm.fSM.TrPoint%>)
				return ((TrPoint)this).getName();
			else if (this instanceof <%org.eclipse.etrice.core.fsm.fSM.ChoicePoint%>)
				return ((ChoicePoint)this).getName();
			else if (this instanceof <%org.eclipse.etrice.core.fsm.fSM.Transition%>)
				return ((Transition)this).getName();
			return "";
			''')
	}
}

