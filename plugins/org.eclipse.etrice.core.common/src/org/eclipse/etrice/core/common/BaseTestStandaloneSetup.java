/*******************************************************************************
 * Copyright (c) 2013 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * generated by Xtext
 * 
 *******************************************************************************/
package org.eclipse.etrice.core.common;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class BaseTestStandaloneSetup extends BaseTestStandaloneSetupGenerated{

	public static void doSetup() {
		new BaseTestStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

