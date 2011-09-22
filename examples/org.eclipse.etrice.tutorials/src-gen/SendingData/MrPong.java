package SendingData;

import org.eclipse.etrice.runtime.java.messaging.Address;
import org.eclipse.etrice.runtime.java.messaging.IRTObject;
import org.eclipse.etrice.runtime.java.messaging.IMessageReceiver;
import org.eclipse.etrice.runtime.java.modelbase.ActorClassBase;
import org.eclipse.etrice.runtime.java.modelbase.SubSystemClassBase;
import org.eclipse.etrice.runtime.java.modelbase.InterfaceItemBase;
import org.eclipse.etrice.runtime.java.debugging.DebuggingService;


import SendingData.PingPongProtocol.*;



public class MrPong extends ActorClassBase {

	
	//--------------------- ports
	protected PingPongProtocolPort PingPongPort = null;
	//--------------------- saps
	//--------------------- services

	//--------------------- interface item IDs
	protected static final int IFITEM_PingPongPort = 1;
	
	//--------------------- attributes
	//--------------------- operations

	//--------------------- construction
	public MrPong(IRTObject parent, String name, Address[][] port_addr, Address[][] peer_addr){
		super(parent, name, port_addr[0][0], peer_addr[0][0]);
		setClassName("MrPong");
		
		// initialize attributes

		// own ports
		PingPongPort = new PingPongProtocolPort(this, "PingPongPort", IFITEM_PingPongPort, 0, port_addr[IFITEM_PingPongPort][0], peer_addr[IFITEM_PingPongPort][0]); 
		// own saps
		// own service implementations
	}
	

	//--------------------- lifecycle functions
	public void init(){
		initUser();
	}

	public void start(){
		startUser();
	}

	public void stop(){
		stopUser();
	}
	
	public void destroy(){
		destroyUser();
	}	

	//******************************************
	// START of generated code for FSM
	//******************************************
	
	// State IDs for FSM
	protected static final int STATE_looping = 2;
		protected static final String stateStrings[] = {"<no state>","<top>","looping"
			};
	
	// history
	// TODOHRR: history defined in ActorClassBase, init in constructor
	//		history = new int[5];
	//		for (int i = 0; i < history.length; i++) {
	//			history[i] = NO_STATE;
	//		}
	protected int history[] = {NO_STATE,NO_STATE,NO_STATE};
	
	// transition chains
	protected static final int CHAIN_TRANS_INITIAL_TO__looping = 1;
	protected static final int CHAIN_TRANS_looping_TO_looping_BY_pingPingPongPort_tr0 = 2;
	protected static final int CHAIN_TRANS_looping_TO_looping_BY_pingSimplePingPongPort_tr1 = 3;
	
	// triggers for FSM
	protected static final int TRIG_PingPongPort__ping = IFITEM_PingPongPort + EVT_SHIFT*PingPongProtocol.IN_ping;
	protected static final int TRIG_PingPongPort__pingSimple = IFITEM_PingPongPort + EVT_SHIFT*PingPongProtocol.IN_pingSimple;
	
	// receiveEvent contains the main implementation of the FSM
	@Override
	public void receiveEvent(InterfaceItemBase ifitem, int evt, Object generic_data) {
		int trigger = ifitem.getLocalId() + EVT_SHIFT*evt;
		int chain = NOT_CAUGHT;
		int catching_state = NO_STATE;
		boolean is_handler = false;
		boolean skip_entry = false;
		
		if (!handleSystemEvent(ifitem, evt, generic_data)) {
			switch (state) {
				case STATE_looping:
					switch(trigger) {
					case TRIG_PingPongPort__ping:
						{
							chain = CHAIN_TRANS_looping_TO_looping_BY_pingPingPongPort_tr0;
							catching_state = STATE_TOP;
						}
					break;
					case TRIG_PingPongPort__pingSimple:
						{
							chain = CHAIN_TRANS_looping_TO_looping_BY_pingSimplePingPongPort_tr1;
							catching_state = STATE_TOP;
						}
					break;
					}
					break;
			}
		}
		if (chain != NOT_CAUGHT) {
			exitTo(state, catching_state, is_handler);
			int next = executeTransitionChain(chain, ifitem, generic_data);
			next = enterHistory(next, is_handler, skip_entry);
			setState(next);
		}
	}
	
	private void setState(int new_state) {
		DebuggingService.getInstance().addActorState(this,stateStrings[new_state]);
		if (stateStrings[new_state]!="Idle") {
			// TODOTS: model switch for activation
			System.out.println(getInstancePath() + " -> " + stateStrings[new_state]);
		}	
		this.state = new_state;
	}
	
	@Override
	public void executeInitTransition() {
		int chain = CHAIN_TRANS_INITIAL_TO__looping;
		int next = executeTransitionChain(chain, null, null);
		next = enterHistory(next, false, false);
		setState(next);
	}
	
	/**
	 * calls exit codes while exiting from the current state to one of its
	 * parent states while remembering the history
	 * @param current - the current state
	 * @param to - the final parent state
	 * @param handler - entry and exit codes are called only if not handler (for handler TransitionPoints)
	 */
	private void exitTo(int current, int to, boolean handler) {
		while (current!=to) {
			switch (current) {
				case STATE_looping:
					history[STATE_TOP] = STATE_looping;
					current = STATE_TOP;
					break;
			}
		}
	}
	/**
	 * calls action, entry and exit codes along a transition chain. The generic data are cast to typed data
	 * matching the trigger of this chain. The ID of the final state is returned
	 * @param chain - the chain ID
	 * @param generic_data - the generic data pointer
	 * @return the ID of the final state
	 */
	private int executeTransitionChain(int chain, InterfaceItemBase ifitem, Object generic_data) {
		switch (chain) {
			case CHAIN_TRANS_INITIAL_TO__looping:
			{
				return STATE_looping;
			}
			case CHAIN_TRANS_looping_TO_looping_BY_pingPingPongPort_tr0:
			{
				DemoData data = (DemoData) generic_data;
				action_TRANS_looping_TO_looping_BY_pingPingPongPort_tr0(ifitem, data);
				return STATE_looping;
			}
			case CHAIN_TRANS_looping_TO_looping_BY_pingSimplePingPongPort_tr1:
			{
				int data = (Integer) generic_data;
				action_TRANS_looping_TO_looping_BY_pingSimplePingPongPort_tr1(ifitem, data);
				return STATE_looping;
			}
		}
		return NO_STATE;
	}
	/**
	 * calls entry codes while entering a state's history. The ID of the final leaf state is returned
	 * @param state - the state which is entered
	 * @param handler - entry code is executed if not handler
	 * @return - the ID of the final leaf state
	 */
	private int enterHistory(int state, boolean handler, boolean skip_entry) {
		while (true) {
			switch (state) {
				case STATE_looping:
					// in leaf state: return state id
					return STATE_looping;
				case STATE_TOP:
					state = history[STATE_TOP];
					break;
			}
			skip_entry = false;
		}
		//return NO_STATE; // required by CDT but detected as unreachable by JDT because of while (true)
	}
	
	//*** Entry and Exit Codes
	
	//*** Action Codes
	protected void action_TRANS_looping_TO_looping_BY_pingPingPongPort_tr0(InterfaceItemBase ifitem, DemoData data) {
		PingPongPort.pong(data);
		}
	protected void action_TRANS_looping_TO_looping_BY_pingSimplePingPongPort_tr1(InterfaceItemBase ifitem, int data) {
		PingPongPort.pongSimple(data+1);
		}
		 
	//******************************************
	// END of generated code for FSM
	//******************************************
};