/*
* generated by Xtext
*/
package org.eclipse.etrice.core.fsm;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.ISetup;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class FSMStandaloneSetupGenerated implements ISetup {

	public Injector createInjectorAndDoEMFRegistration() {
		org.eclipse.etrice.core.common.BaseStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new org.eclipse.etrice.core.fsm.FSMRuntimeModule());
	}
	
	public void register(Injector injector) {
	if (!EPackage.Registry.INSTANCE.containsKey("http://www.eclipse.org/etrice/core/fsm/FSM")) {
		EPackage.Registry.INSTANCE.put("http://www.eclipse.org/etrice/core/fsm/FSM", org.eclipse.etrice.core.fsm.fSM.FSMPackage.eINSTANCE);
	}

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fsm", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("fsm", serviceProvider);
		



	}
}
