/*******************************************************************************
 * Copyright (c) 2013 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 * 
 *******************************************************************************/

package org.eclipse.etrice.core.genmodel.fsm.fsmgen.util;

import org.eclipse.etrice.core.fsm.fSM.Trigger;
import org.eclipse.etrice.core.fsm.fSM.TriggeredTransition;
import org.eclipse.etrice.core.fsm.util.FSMHelpers;
import org.eclipse.etrice.core.genmodel.fsm.fsmgen.ActiveTrigger;
import org.eclipse.etrice.core.genmodel.fsm.fsmgen.FsmGenFactory;

/**
 * @author Henrik Rentz-Reichert
 *
 */
public class FsmGenUtil {
	
	private FSMHelpers fsmHelpers = new FSMHelpers();

	/**
	 * @param trig the trigger
	 * @param trigstr the encoded trigger string
	 * @return <code>true</code> if the encoded trigger string is matching the {@link Trigger}
	 * 
	 * @see org.eclipse.etrice.core.genmodel.etricegen.ExpandedActorClass#isMatching(Trigger, String)
	 */
	public boolean isMatching(Trigger trig, String trigstr) {
		return FsmGenFactory.eINSTANCE.createExpandedModelComponent().isMatching(trig, trigstr);
	}
	
	/**
	 * @param at an {@link ActiveTrigger}
	 * @return <code>true</code> if the active trigger has defined a guard
	 */
	public boolean hasGuard(ActiveTrigger at) {
		for (TriggeredTransition t : at.getTransitions()) {
			for (Trigger trig : t.getTriggers()) {
				if (isMatching(trig, at.getTrigger())
						&& fsmHelpers.hasGuard(trig))
					return true;
			}
		}
		return false;
	}
}
