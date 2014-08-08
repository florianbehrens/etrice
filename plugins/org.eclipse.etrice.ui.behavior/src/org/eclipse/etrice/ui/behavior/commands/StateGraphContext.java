package org.eclipse.etrice.ui.behavior.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.etrice.core.room.ActorClass;
import org.eclipse.etrice.core.fsm.fSM.ChoicePoint;
import org.eclipse.etrice.core.fsm.fSM.InitialTransition;
import org.eclipse.etrice.core.fsm.fSM.RefinedState;
import org.eclipse.etrice.core.fsm.fSM.FSMFactory;
import org.eclipse.etrice.core.fsm.fSM.SimpleState;
import org.eclipse.etrice.core.fsm.fSM.State;
import org.eclipse.etrice.core.fsm.fSM.StateGraph;
import org.eclipse.etrice.core.fsm.fSM.StateGraphItem;
import org.eclipse.etrice.core.fsm.fSM.TrPoint;
import org.eclipse.etrice.core.fsm.fSM.Transition;
import org.eclipse.etrice.ui.behavior.support.DefaultPositionProvider;
import org.eclipse.etrice.ui.behavior.support.IPositionProvider;
import org.eclipse.etrice.ui.behavior.support.SupportUtil;

public class StateGraphContext {
	private ArrayList<StateGraphContext> children = new ArrayList<StateGraphContext>();
	
	private StateGraph stateGraph;
	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<ChoicePoint> chPoints = new ArrayList<ChoicePoint>();
	private ArrayList<TrPoint> trPoints = new ArrayList<TrPoint>();
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private IPositionProvider positionProvider;
	private HashMap<StateGraphItem, StateGraphContext> obj2ctx;
	
	public static StateGraphContext createContextTree(ActorClass ac) {
		
		// the top level state graph is always the one of our actor class
		if (ac.getStateMachine()==null || ac.getStateMachine().eIsProxy()) {
			ac.setStateMachine(FSMFactory.eINSTANCE.createStateGraph());
		}
		
		// base classes in reverse order
		ArrayList<ActorClass> classes = new ArrayList<ActorClass>();
		{
			ActorClass a = ac;
			while (a!=null) {
				classes.add(0, a);
				a = a.getActorBase();
			}
		}
		
		// build and merge contexts from base classes to derived classes
		StateGraphContext tree = null;
		for (ActorClass cls : classes) {
			if (cls.getStateMachine()!=null) {
				if (tree==null)
					tree = new StateGraphContext(cls.getStateMachine(), new HashMap<StateGraphItem, StateGraphContext>());
				else
					tree.merge(cls.getStateMachine());
			}
		}
		
		tree.positionProvider = new DefaultPositionProvider(ac);
		makePositionsAvailableToChildrenContexts(tree);
		
		return tree;
	}

	private StateGraphContext(StateGraph sg, HashMap<StateGraphItem, StateGraphContext> obj2ctx) {
		this.stateGraph = sg;
		this.obj2ctx = obj2ctx;
		
		init(sg);
	}

	private void init(StateGraph sg) {
		for (State s : sg.getStates()) {
			states.add(s);
			obj2ctx.put(s, this);
		}
		for (ChoicePoint cp : sg.getChPoints()) {
			chPoints.add(cp);
			obj2ctx.put(cp, this);
		}
		for (TrPoint tp : sg.getTrPoints()) {
			trPoints.add(tp);
			obj2ctx.put(tp, this);
		}
		for (Transition t : sg.getTransitions()) {
			transitions.add(t);
			obj2ctx.put(t, this);
		}

		// recurse
		for (State s : sg.getStates()) {
			if (s.getSubgraph()!=null)
				children.add(new StateGraphContext(s.getSubgraph(), obj2ctx));
		}
	}

	private void merge(StateGraph derived) {
		stateGraph = derived;
		
		// add derived contents up to refined states
		for (State s : derived.getStates()) {
			if (s instanceof SimpleState) {
				states.add(s);
				obj2ctx.put(s, this);
			}
		}
		for (ChoicePoint cp : derived.getChPoints()) {
			chPoints.add(cp);
			obj2ctx.put(cp, this);
		}
		for (TrPoint tp : derived.getTrPoints()) {
			trPoints.add(tp);
			obj2ctx.put(tp, this);
		}
		for (Transition t : derived.getTransitions()) {
			transitions.add(t);
			obj2ctx.put(t, this);
		}

		// recurse for non-refined states
		for (State s : derived.getStates()) {
			if (s instanceof SimpleState)
				if (s.getSubgraph()!=null)
					children.add(new StateGraphContext(s.getSubgraph(), obj2ctx));
		}
		
		// refined states
		for (State refined : derived.getStates()) {
			if (refined instanceof RefinedState) {
				State base = ((RefinedState) refined).getTarget();
				StateGraphContext ctx = obj2ctx.get(base);
				assert(ctx!=null): "should have visited base state already";
				
				// remove base and put refined in place
				ctx.getStates().remove(base);
				ctx.getStates().add(refined);
				obj2ctx.put(refined, this);
				
				// merge sub contexts
				if (SupportUtil.getInstance().getRoomHelpers().hasDirectSubStructure(base)) {
					StateGraphContext basesub = null;
					for (StateGraphContext bs : ctx.getChildren()) {
						if (bs.getParentState()==base) {
							basesub = bs;
							break;
						}
					}
					if (basesub!=null) {
						if (SupportUtil.getInstance().getRoomHelpers().hasDirectSubStructure(refined)) {
							basesub.merge(refined.getSubgraph());
						}
					}
					else {
						assert(false): "context not found";
					}
				}
				else if (SupportUtil.getInstance().getRoomHelpers().hasDirectSubStructure(refined)) {
					StateGraphContext sub = new StateGraphContext(refined.getSubgraph(), obj2ctx);
					ctx.getChildren().add(sub);
				}
			}
		}
	}
	
	/**
	 * Propagate the position map to all children recursively
	 * 
	 * @param tree
	 */
	private static void makePositionsAvailableToChildrenContexts(StateGraphContext tree) {
		for (StateGraphContext child : tree.getChildren()) {
			child.positionProvider = tree.positionProvider;
			makePositionsAvailableToChildrenContexts(child);
		}
	}

	public State getParentState() {
		if (stateGraph.eContainer() instanceof State)
			return (State) stateGraph.eContainer();
		
		return null;
	}

	public ArrayList<StateGraphContext> getChildren() {
		return children;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public ArrayList<ChoicePoint> getChPoints() {
		return chPoints;
	}

	public StateGraph getStateGraph() {
		return stateGraph;
	}

	public ArrayList<TrPoint> getTrPoints() {
		return trPoints;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public IPositionProvider getPositionProvider() {
		return positionProvider;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return dump("");
	}
	
	/**
	 * @param indent
	 * @return context tree in human readable form
	 */
	private String dump(String indent) {
		StringBuilder result = new StringBuilder();
		result.append(indent+">>> "+getText(stateGraph)+"\n");
		indent += "  ";
		for (State s : states) {
			result.append(indent+getText(s)+"\n");
		}
		
		for (StateGraphContext child : children) {
			result.append(child.dump(indent));
		}
		return result.toString();
	}

	private String getText(StateGraph sg) {
		ActorClass ac = getActorClass(sg);
		EObject parent = sg.eContainer();
		String item = parent instanceof ActorClass? "diagram" : (parent.eClass().getName()+" "+((State)parent).getName());
		return "state graph of "+item+" of "+(ac==null? "?":ac.getName());
	}

	private String getText(StateGraphItem item) {
		ActorClass ac = getActorClass(item);
		return item.eClass().getName()+" "+item.getName()+" of "+(ac==null? "?":ac.getName());
	}

	private ActorClass getActorClass(EObject obj) {
		EObject parent = obj.eContainer();
		while (parent!=null) {
			if (parent instanceof ActorClass)
				return (ActorClass) parent;
			parent = parent.eContainer();
		}
		return null;
	}
	
	public StateGraphContext getContext(StateGraphItem item) {
		return obj2ctx.get(item);
	}
	
	public StateGraph getInitialPoint(){
		for(Transition t : transitions)
			if(t instanceof InitialTransition)
				return (StateGraph) t.eContainer();
		return null;
	}
}
