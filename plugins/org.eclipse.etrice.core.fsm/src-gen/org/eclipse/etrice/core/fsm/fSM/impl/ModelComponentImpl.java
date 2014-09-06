/**
 */
package org.eclipse.etrice.core.fsm.fSM.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.etrice.core.fsm.fSM.AbstractInterfaceItem;
import org.eclipse.etrice.core.fsm.fSM.ComponentCommunicationType;
import org.eclipse.etrice.core.fsm.fSM.FSMPackage;
import org.eclipse.etrice.core.fsm.fSM.ModelComponent;
import org.eclipse.etrice.core.fsm.fSM.StateGraph;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.ModelComponentImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.ModelComponentImpl#getCommType <em>Comm Type</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.ModelComponentImpl#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.ModelComponentImpl#getBase <em>Base</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.ModelComponentImpl#getStateMachine <em>State Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelComponentImpl extends MinimalEObjectImpl.Container implements ModelComponent
{
  /**
   * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final boolean ABSTRACT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected boolean abstract_ = ABSTRACT_EDEFAULT;

  /**
   * The default value of the '{@link #getCommType() <em>Comm Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommType()
   * @generated
   * @ordered
   */
  protected static final ComponentCommunicationType COMM_TYPE_EDEFAULT = ComponentCommunicationType.EVENT_DRIVEN;

  /**
   * The cached value of the '{@link #getCommType() <em>Comm Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCommType()
   * @generated
   * @ordered
   */
  protected ComponentCommunicationType commType = COMM_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getComponentName() <em>Component Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponentName()
   * @generated
   * @ordered
   */
  protected static final String COMPONENT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComponentName() <em>Component Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComponentName()
   * @generated
   * @ordered
   */
  protected String componentName = COMPONENT_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getBase() <em>Base</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBase()
   * @generated
   * @ordered
   */
  protected ModelComponent base;

  /**
   * The cached value of the '{@link #getStateMachine() <em>State Machine</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStateMachine()
   * @generated
   * @ordered
   */
  protected StateGraph stateMachine;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelComponentImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FSMPackage.Literals.MODEL_COMPONENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAbstract()
  {
    return abstract_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbstract(boolean newAbstract)
  {
    boolean oldAbstract = abstract_;
    abstract_ = newAbstract;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__ABSTRACT, oldAbstract, abstract_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentCommunicationType getCommType()
  {
    return commType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCommType(ComponentCommunicationType newCommType)
  {
    ComponentCommunicationType oldCommType = commType;
    commType = newCommType == null ? COMM_TYPE_EDEFAULT : newCommType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__COMM_TYPE, oldCommType, commType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComponentName()
  {
    return componentName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponentName(String newComponentName)
  {
    String oldComponentName = componentName;
    componentName = newComponentName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__COMPONENT_NAME, oldComponentName, componentName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelComponent getBase()
  {
    if (base != null && base.eIsProxy())
    {
      InternalEObject oldBase = (InternalEObject)base;
      base = (ModelComponent)eResolveProxy(oldBase);
      if (base != oldBase)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.MODEL_COMPONENT__BASE, oldBase, base));
      }
    }
    return base;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelComponent basicGetBase()
  {
    return base;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBase(ModelComponent newBase)
  {
    ModelComponent oldBase = base;
    base = newBase;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__BASE, oldBase, base));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StateGraph getStateMachine()
  {
    return stateMachine;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStateMachine(StateGraph newStateMachine, NotificationChain msgs)
  {
    StateGraph oldStateMachine = stateMachine;
    stateMachine = newStateMachine;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__STATE_MACHINE, oldStateMachine, newStateMachine);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStateMachine(StateGraph newStateMachine)
  {
    if (newStateMachine != stateMachine)
    {
      NotificationChain msgs = null;
      if (stateMachine != null)
        msgs = ((InternalEObject)stateMachine).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FSMPackage.MODEL_COMPONENT__STATE_MACHINE, null, msgs);
      if (newStateMachine != null)
        msgs = ((InternalEObject)newStateMachine).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FSMPackage.MODEL_COMPONENT__STATE_MACHINE, null, msgs);
      msgs = basicSetStateMachine(newStateMachine, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MODEL_COMPONENT__STATE_MACHINE, newStateMachine, newStateMachine));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AbstractInterfaceItem> getAbstractInterfaceItems()
  {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AbstractInterfaceItem> getAllAbstractInterfaceItems()
  {
    // TODO: implement this method
    // Ensure that you remove @generated or mark it @generated NOT
    throw new UnsupportedOperationException();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FSMPackage.MODEL_COMPONENT__STATE_MACHINE:
        return basicSetStateMachine(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FSMPackage.MODEL_COMPONENT__ABSTRACT:
        return isAbstract();
      case FSMPackage.MODEL_COMPONENT__COMM_TYPE:
        return getCommType();
      case FSMPackage.MODEL_COMPONENT__COMPONENT_NAME:
        return getComponentName();
      case FSMPackage.MODEL_COMPONENT__BASE:
        if (resolve) return getBase();
        return basicGetBase();
      case FSMPackage.MODEL_COMPONENT__STATE_MACHINE:
        return getStateMachine();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FSMPackage.MODEL_COMPONENT__ABSTRACT:
        setAbstract((Boolean)newValue);
        return;
      case FSMPackage.MODEL_COMPONENT__COMM_TYPE:
        setCommType((ComponentCommunicationType)newValue);
        return;
      case FSMPackage.MODEL_COMPONENT__COMPONENT_NAME:
        setComponentName((String)newValue);
        return;
      case FSMPackage.MODEL_COMPONENT__BASE:
        setBase((ModelComponent)newValue);
        return;
      case FSMPackage.MODEL_COMPONENT__STATE_MACHINE:
        setStateMachine((StateGraph)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FSMPackage.MODEL_COMPONENT__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case FSMPackage.MODEL_COMPONENT__COMM_TYPE:
        setCommType(COMM_TYPE_EDEFAULT);
        return;
      case FSMPackage.MODEL_COMPONENT__COMPONENT_NAME:
        setComponentName(COMPONENT_NAME_EDEFAULT);
        return;
      case FSMPackage.MODEL_COMPONENT__BASE:
        setBase((ModelComponent)null);
        return;
      case FSMPackage.MODEL_COMPONENT__STATE_MACHINE:
        setStateMachine((StateGraph)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FSMPackage.MODEL_COMPONENT__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case FSMPackage.MODEL_COMPONENT__COMM_TYPE:
        return commType != COMM_TYPE_EDEFAULT;
      case FSMPackage.MODEL_COMPONENT__COMPONENT_NAME:
        return COMPONENT_NAME_EDEFAULT == null ? componentName != null : !COMPONENT_NAME_EDEFAULT.equals(componentName);
      case FSMPackage.MODEL_COMPONENT__BASE:
        return base != null;
      case FSMPackage.MODEL_COMPONENT__STATE_MACHINE:
        return stateMachine != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (abstract: ");
    result.append(abstract_);
    result.append(", commType: ");
    result.append(commType);
    result.append(", componentName: ");
    result.append(componentName);
    result.append(')');
    return result.toString();
  }

} //ModelComponentImpl