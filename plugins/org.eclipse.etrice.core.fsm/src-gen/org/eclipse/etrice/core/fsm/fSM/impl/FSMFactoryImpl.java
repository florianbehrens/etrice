/**
 */
package org.eclipse.etrice.core.fsm.fSM.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.etrice.core.fsm.fSM.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FSMFactoryImpl extends EFactoryImpl implements FSMFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FSMFactory init()
  {
    try
    {
      FSMFactory theFSMFactory = (FSMFactory)EPackage.Registry.INSTANCE.getEFactory(FSMPackage.eNS_URI);
      if (theFSMFactory != null)
      {
        return theFSMFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FSMFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FSMFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case FSMPackage.FSM_MODEL: return createFSMModel();
      case FSMPackage.MODEL_COMPONENT: return createModelComponent();
      case FSMPackage.STATE_GRAPH_NODE: return createStateGraphNode();
      case FSMPackage.STATE_GRAPH_ITEM: return createStateGraphItem();
      case FSMPackage.STATE_GRAPH: return createStateGraph();
      case FSMPackage.STATE: return createState();
      case FSMPackage.SIMPLE_STATE: return createSimpleState();
      case FSMPackage.REFINED_STATE: return createRefinedState();
      case FSMPackage.DETAIL_CODE: return createDetailCode();
      case FSMPackage.TR_POINT: return createTrPoint();
      case FSMPackage.TRANSITION_POINT: return createTransitionPoint();
      case FSMPackage.ENTRY_POINT: return createEntryPoint();
      case FSMPackage.EXIT_POINT: return createExitPoint();
      case FSMPackage.CHOICE_POINT: return createChoicePoint();
      case FSMPackage.TRANSITION_BASE: return createTransitionBase();
      case FSMPackage.TRANSITION: return createTransition();
      case FSMPackage.NON_INITIAL_TRANSITION: return createNonInitialTransition();
      case FSMPackage.TRANSITION_CHAIN_START_TRANSITION: return createTransitionChainStartTransition();
      case FSMPackage.INITIAL_TRANSITION: return createInitialTransition();
      case FSMPackage.CONTINUATION_TRANSITION: return createContinuationTransition();
      case FSMPackage.TRIGGERED_TRANSITION: return createTriggeredTransition();
      case FSMPackage.GUARDED_TRANSITION: return createGuardedTransition();
      case FSMPackage.CP_BRANCH_TRANSITION: return createCPBranchTransition();
      case FSMPackage.REFINED_TRANSITION: return createRefinedTransition();
      case FSMPackage.TRANSITION_TERMINAL: return createTransitionTerminal();
      case FSMPackage.STATE_TERMINAL: return createStateTerminal();
      case FSMPackage.TR_POINT_TERMINAL: return createTrPointTerminal();
      case FSMPackage.SUB_STATE_TR_POINT_TERMINAL: return createSubStateTrPointTerminal();
      case FSMPackage.CHOICEPOINT_TERMINAL: return createChoicepointTerminal();
      case FSMPackage.TRIGGER: return createTrigger();
      case FSMPackage.MESSAGE_FROM_IF: return createMessageFromIf();
      case FSMPackage.ABSTRACT_INTERFACE_ITEM: return createAbstractInterfaceItem();
      case FSMPackage.GUARD: return createGuard();
      case FSMPackage.PROTOCOL_SEMANTICS: return createProtocolSemantics();
      case FSMPackage.SEMANTICS_RULE: return createSemanticsRule();
      case FSMPackage.IN_SEMANTICS_RULE: return createInSemanticsRule();
      case FSMPackage.OUT_SEMANTICS_RULE: return createOutSemanticsRule();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FSMPackage.COMPONENT_COMMUNICATION_TYPE:
        return createComponentCommunicationTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FSMPackage.COMPONENT_COMMUNICATION_TYPE:
        return convertComponentCommunicationTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FSMModel createFSMModel()
  {
    FSMModelImpl fsmModel = new FSMModelImpl();
    return fsmModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelComponent createModelComponent()
  {
    ModelComponentImpl modelComponent = new ModelComponentImpl();
    return modelComponent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StateGraphNode createStateGraphNode()
  {
    StateGraphNodeImpl stateGraphNode = new StateGraphNodeImpl();
    return stateGraphNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StateGraphItem createStateGraphItem()
  {
    StateGraphItemImpl stateGraphItem = new StateGraphItemImpl();
    return stateGraphItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StateGraph createStateGraph()
  {
    StateGraphImpl stateGraph = new StateGraphImpl();
    return stateGraph;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public State createState()
  {
    StateImpl state = new StateImpl();
    return state;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleState createSimpleState()
  {
    SimpleStateImpl simpleState = new SimpleStateImpl();
    return simpleState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RefinedState createRefinedState()
  {
    RefinedStateImpl refinedState = new RefinedStateImpl();
    return refinedState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DetailCode createDetailCode()
  {
    DetailCodeImpl detailCode = new DetailCodeImpl();
    return detailCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TrPoint createTrPoint()
  {
    TrPointImpl trPoint = new TrPointImpl();
    return trPoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransitionPoint createTransitionPoint()
  {
    TransitionPointImpl transitionPoint = new TransitionPointImpl();
    return transitionPoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntryPoint createEntryPoint()
  {
    EntryPointImpl entryPoint = new EntryPointImpl();
    return entryPoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExitPoint createExitPoint()
  {
    ExitPointImpl exitPoint = new ExitPointImpl();
    return exitPoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChoicePoint createChoicePoint()
  {
    ChoicePointImpl choicePoint = new ChoicePointImpl();
    return choicePoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransitionBase createTransitionBase()
  {
    TransitionBaseImpl transitionBase = new TransitionBaseImpl();
    return transitionBase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Transition createTransition()
  {
    TransitionImpl transition = new TransitionImpl();
    return transition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NonInitialTransition createNonInitialTransition()
  {
    NonInitialTransitionImpl nonInitialTransition = new NonInitialTransitionImpl();
    return nonInitialTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransitionChainStartTransition createTransitionChainStartTransition()
  {
    TransitionChainStartTransitionImpl transitionChainStartTransition = new TransitionChainStartTransitionImpl();
    return transitionChainStartTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InitialTransition createInitialTransition()
  {
    InitialTransitionImpl initialTransition = new InitialTransitionImpl();
    return initialTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContinuationTransition createContinuationTransition()
  {
    ContinuationTransitionImpl continuationTransition = new ContinuationTransitionImpl();
    return continuationTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TriggeredTransition createTriggeredTransition()
  {
    TriggeredTransitionImpl triggeredTransition = new TriggeredTransitionImpl();
    return triggeredTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GuardedTransition createGuardedTransition()
  {
    GuardedTransitionImpl guardedTransition = new GuardedTransitionImpl();
    return guardedTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CPBranchTransition createCPBranchTransition()
  {
    CPBranchTransitionImpl cpBranchTransition = new CPBranchTransitionImpl();
    return cpBranchTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RefinedTransition createRefinedTransition()
  {
    RefinedTransitionImpl refinedTransition = new RefinedTransitionImpl();
    return refinedTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransitionTerminal createTransitionTerminal()
  {
    TransitionTerminalImpl transitionTerminal = new TransitionTerminalImpl();
    return transitionTerminal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StateTerminal createStateTerminal()
  {
    StateTerminalImpl stateTerminal = new StateTerminalImpl();
    return stateTerminal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TrPointTerminal createTrPointTerminal()
  {
    TrPointTerminalImpl trPointTerminal = new TrPointTerminalImpl();
    return trPointTerminal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubStateTrPointTerminal createSubStateTrPointTerminal()
  {
    SubStateTrPointTerminalImpl subStateTrPointTerminal = new SubStateTrPointTerminalImpl();
    return subStateTrPointTerminal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChoicepointTerminal createChoicepointTerminal()
  {
    ChoicepointTerminalImpl choicepointTerminal = new ChoicepointTerminalImpl();
    return choicepointTerminal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Trigger createTrigger()
  {
    TriggerImpl trigger = new TriggerImpl();
    return trigger;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MessageFromIf createMessageFromIf()
  {
    MessageFromIfImpl messageFromIf = new MessageFromIfImpl();
    return messageFromIf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractInterfaceItem createAbstractInterfaceItem()
  {
    AbstractInterfaceItemImpl abstractInterfaceItem = new AbstractInterfaceItemImpl();
    return abstractInterfaceItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Guard createGuard()
  {
    GuardImpl guard = new GuardImpl();
    return guard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProtocolSemantics createProtocolSemantics()
  {
    ProtocolSemanticsImpl protocolSemantics = new ProtocolSemanticsImpl();
    return protocolSemantics;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SemanticsRule createSemanticsRule()
  {
    SemanticsRuleImpl semanticsRule = new SemanticsRuleImpl();
    return semanticsRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InSemanticsRule createInSemanticsRule()
  {
    InSemanticsRuleImpl inSemanticsRule = new InSemanticsRuleImpl();
    return inSemanticsRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutSemanticsRule createOutSemanticsRule()
  {
    OutSemanticsRuleImpl outSemanticsRule = new OutSemanticsRuleImpl();
    return outSemanticsRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentCommunicationType createComponentCommunicationTypeFromString(EDataType eDataType, String initialValue)
  {
    ComponentCommunicationType result = ComponentCommunicationType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertComponentCommunicationTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FSMPackage getFSMPackage()
  {
    return (FSMPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FSMPackage getPackage()
  {
    return FSMPackage.eINSTANCE;
  }

} //FSMFactoryImpl
