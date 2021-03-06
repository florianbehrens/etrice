/*
 * generated by Xtext
 */
package org.eclipse.etrice.core.fsm.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.eclipse.etrice.core.fsm.ui.internal.FSMActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class FSMExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FSMActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return FSMActivator.getInstance().getInjector(FSMActivator.ORG_ECLIPSE_ETRICE_CORE_FSM_FSM);
	}
	
}
