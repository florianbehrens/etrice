/*
 * generated by Xtext
 */
package org.eclipse.etrice.core.etphys;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class ETPhysUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return org.eclipse.etrice.core.etphys.ui.internal.ETPhysActivator.getInstance().getInjector("org.eclipse.etrice.core.etphys.ETPhys");
	}

}
