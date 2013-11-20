package org.eclipse.etrice.examples.dynamicactors5;

import org.eclipse.etrice.runtime.java.messaging.Address;
import org.eclipse.etrice.runtime.java.messaging.IRTObject;
import org.eclipse.etrice.runtime.java.messaging.IMessageReceiver;
import org.eclipse.etrice.runtime.java.modelbase.ActorClassBase;
import org.eclipse.etrice.runtime.java.modelbase.SubSystemClassBase;
import org.eclipse.etrice.runtime.java.modelbase.DataPortBase;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBase;
import org.eclipse.etrice.runtime.java.debugging.DebuggingService;
import static org.eclipse.etrice.runtime.java.etunit.EtUnit.*;


import org.eclipse.etrice.examples.dynamicactors5.PC.*;

import org.eclipse.etrice.examples.dynamicactors5.OptionalInterface;



public class Container extends ActorClassBase {

	
	//--------------------- ports
	protected PCConjPort p0 = null;
	
	//--------------------- saps
	
	//--------------------- services
	
	//--------------------- optional actors
	protected OptionalInterface opt = null;
	
	//--------------------- interface item IDs
	public static final int IFITEM_p0 = 1;
	
	/*--------------------- attributes ---------------------*/
	/*--------------------- operations ---------------------*/
	public void dumpTree(String msg) {
		System.out.println(msg);
		System.out.println(((org.eclipse.etrice.runtime.java.messaging.RTObject)getRoot()).toStringRecursive());
	}

	//--------------------- construction
	public Container(IRTObject parent, String name) {
		super(parent, name);
		setClassName("Container");
		
		// initialize attributes

		// own ports
		p0 = new PCConjPort(this, "p0", IFITEM_p0);
		
		// own saps
		
		// own service implementations
		
		// sub actors
		opt = new OptionalInterface(this, "opt");
		
		// wiring
		InterfaceItemBase.connect(this, "opt/p0", "p0");
		

	}
	
	/* --------------------- attribute setters and getters */
	
	
	//--------------------- port getters
	public PCConjPort getP0 (){
		return this.p0;
	}

	//--------------------- lifecycle functions
	public void stop(){
		stopUser();
		super.stop();
	}
	
	public void destroy() {
		DebuggingService.getInstance().addMessageActorDestroy(this);
		super.destroy();
	}

	/* state IDs */
	public static final int STATE_CreateOptional = 2;
	public static final int STATE_Done = 3;
	public static final int STATE_MAX = 4;
	
	/* transition chains */
	public static final int CHAIN_TRANS_INITIAL_TO__CreateOptional = 1;
	public static final int CHAIN_TRANS_tr0_FROM_CreateOptional_TO_Done_BY_hellop0 = 2;
	
	/* triggers */
	public static final int POLLING = 0;
	public static final int TRIG_p0__hello = IFITEM_p0 + EVT_SHIFT*PC.OUT_hello;
	
	// state names
	protected static final String stateStrings[] = {
		"<no state>",
		"<top>",
		"CreateOptional",
		"Done"
	};
		
	// history
	protected int history[] = {NO_STATE, NO_STATE, NO_STATE, NO_STATE};
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		this.state = new_state;
	}
	
	/* Entry and Exit Codes */
	protected void entry_CreateOptional() {
		opt.createOptionalActor("Optional", getThread());
		p0.sayHello();
	}
	protected void entry_Done() {
		System.out.println("Done, enter 'quit' to exit"); 
	}
	
	/* Action Codes */
	protected void action_TRANS_tr0_FROM_CreateOptional_TO_Done_BY_hellop0(InterfaceItemBase ifitem, String txt) {
		System.out.println(txt+"\n");
		opt.destroyOptionalActor();
		dumpTree("after deletion of Optional2");
	}
	
	/**
	 * calls exit codes while exiting from the current state to one of its
	 * parent states while remembering the history
	 * @param current - the current state
	 * @param to - the final parent state
	 */
	private void exitTo(int current, int to) {
		while (current!=to) {
			switch (current) {
				case STATE_CreateOptional:
					this.history[STATE_TOP] = STATE_CreateOptional;
					current = STATE_TOP;
					break;
				case STATE_Done:
					this.history[STATE_TOP] = STATE_Done;
					current = STATE_TOP;
					break;
				default:
					/* should not occur */
					break;
			}
		}
	}
	
	/**
	 * calls action, entry and exit codes along a transition chain. The generic data are cast to typed data
	 * matching the trigger of this chain. The ID of the final state is returned
	 * @param chain - the chain ID
	 * @param generic_data - the generic data pointer
	 * @return the +/- ID of the final state either with a positive sign, that indicates to execute the state's entry code, or a negative sign vice versa
	 */
	private int executeTransitionChain(int chain, InterfaceItemBase ifitem, Object generic_data) {
		switch (chain) {
			case CHAIN_TRANS_INITIAL_TO__CreateOptional:
			{
				return STATE_CreateOptional;
			}
			case CHAIN_TRANS_tr0_FROM_CreateOptional_TO_Done_BY_hellop0:
			{
				String txt = (String) generic_data;
				action_TRANS_tr0_FROM_CreateOptional_TO_Done_BY_hellop0(ifitem, txt);
				return STATE_Done;
			}
				default:
					/* should not occur */
					break;
		}
		return NO_STATE;
	}
	
	/**
	 * calls entry codes while entering a state's history. The ID of the final leaf state is returned
	 * @param state - the state which is entered
	 * @return - the ID of the final leaf state
	 */
	private int enterHistory(int state) {
		boolean skip_entry = false;
		if (state >= STATE_MAX) {
			state =  (state - STATE_MAX);
			skip_entry = true;
		}
		while (true) {
			switch (state) {
				case STATE_CreateOptional:
					if (!(skip_entry)) entry_CreateOptional();
					/* in leaf state: return state id */
					return STATE_CreateOptional;
				case STATE_Done:
					if (!(skip_entry)) entry_Done();
					/* in leaf state: return state id */
					return STATE_Done;
				case STATE_TOP:
					state = this.history[STATE_TOP];
					break;
				default:
					/* should not occur */
					break;
			}
			skip_entry = false;
		}
		/* return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true) */
	}
	
	public void executeInitTransition() {
		int chain = CHAIN_TRANS_INITIAL_TO__CreateOptional;
		int next = executeTransitionChain(chain, null, null);
		next = enterHistory(next);
		setState(next);
	}
	
	/* receiveEvent contains the main implementation of the FSM */
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object generic_data) {
		int trigger = ifitem.getLocalId() + EVT_SHIFT*evt;
		int chain = NOT_CAUGHT;
		int catching_state = NO_STATE;
		
		if (!handleSystemEvent(ifitem, evt, generic_data)) {
			switch (getState()) {
				case STATE_CreateOptional:
					switch(trigger) {
							case TRIG_p0__hello:
								{
									chain = CHAIN_TRANS_tr0_FROM_CreateOptional_TO_Done_BY_hellop0;
									catching_state = STATE_TOP;
								}
							break;
							default:
								/* should not occur */
								break;
					}
					break;
				case STATE_Done:
					break;
				default:
					/* should not occur */
					break;
			}
		}
		if (chain != NOT_CAUGHT) {
			exitTo(getState(), catching_state);
			{
				int next = executeTransitionChain(chain, ifitem, generic_data);
				next = enterHistory(next);
				setState(next);
			}
		}
	}
};
