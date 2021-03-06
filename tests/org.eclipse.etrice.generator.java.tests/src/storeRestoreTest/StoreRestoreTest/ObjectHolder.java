/*******************************************************************************
 * Copyright (c) 2013 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		hrentz (initial contribution)
 * 
 *******************************************************************************/

package StoreRestoreTest;

import org.eclipse.etrice.runtime.java.modelbase.IActorClassDataObject;

import StoreRestoreTest.ObjectHolder;

/**
 * @author hrentz
 *
 */
public class ObjectHolder {

	private static IActorClassDataObject object = null;

	public static IActorClassDataObject getObject() {
		return object;
	}

	public static void setObject(IActorClassDataObject object) {
		ObjectHolder.object = object;
	}
}
