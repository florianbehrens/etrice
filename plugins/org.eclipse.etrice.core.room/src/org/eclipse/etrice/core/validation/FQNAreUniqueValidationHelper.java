/*******************************************************************************
 * Copyright (c) 2015 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Juergen Haug (initial contribution)
 * 
 *******************************************************************************/

package org.eclipse.etrice.core.validation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.validation.NamesAreUniqueValidationHelper;

/**
 *  Clustering is deactivated.<br>
 *  Names are unique: two objects sharing the same fqn prefix have distinct names.<br>
 *  Called by {@link org.eclipse.xtext.validation.NamesAreUniqueValidator NamesAreUniqueValidator}. 
 */
public class FQNAreUniqueValidationHelper extends NamesAreUniqueValidationHelper {
	
	@Override
	protected EClass getAssociatedClusterType(EClass eClass) {
		return EcorePackage.eINSTANCE.eClass();
	}
	
	@Override
	protected String getTypeLabel(EClass eClass) {
		return "";
	}
}
