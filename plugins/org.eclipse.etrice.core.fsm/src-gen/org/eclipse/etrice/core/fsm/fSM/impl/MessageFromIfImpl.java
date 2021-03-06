/**
 */
package org.eclipse.etrice.core.fsm.fSM.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.etrice.core.fsm.fSM.AbstractInterfaceItem;
import org.eclipse.etrice.core.fsm.fSM.FSMPackage;
import org.eclipse.etrice.core.fsm.fSM.MessageFromIf;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message From If</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.MessageFromIfImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.impl.MessageFromIfImpl#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageFromIfImpl extends MinimalEObjectImpl.Container implements MessageFromIf
{
  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected EObject message;

  /**
   * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected AbstractInterfaceItem from;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MessageFromIfImpl()
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
    return FSMPackage.Literals.MESSAGE_FROM_IF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getMessage()
  {
    if (message != null && message.eIsProxy())
    {
      InternalEObject oldMessage = (InternalEObject)message;
      message = eResolveProxy(oldMessage);
      if (message != oldMessage)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.MESSAGE_FROM_IF__MESSAGE, oldMessage, message));
      }
    }
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetMessage()
  {
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessage(EObject newMessage)
  {
    EObject oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MESSAGE_FROM_IF__MESSAGE, oldMessage, message));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractInterfaceItem getFrom()
  {
    if (from != null && from.eIsProxy())
    {
      InternalEObject oldFrom = (InternalEObject)from;
      from = (AbstractInterfaceItem)eResolveProxy(oldFrom);
      if (from != oldFrom)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, FSMPackage.MESSAGE_FROM_IF__FROM, oldFrom, from));
      }
    }
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractInterfaceItem basicGetFrom()
  {
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFrom(AbstractInterfaceItem newFrom)
  {
    AbstractInterfaceItem oldFrom = from;
    from = newFrom;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FSMPackage.MESSAGE_FROM_IF__FROM, oldFrom, from));
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
      case FSMPackage.MESSAGE_FROM_IF__MESSAGE:
        if (resolve) return getMessage();
        return basicGetMessage();
      case FSMPackage.MESSAGE_FROM_IF__FROM:
        if (resolve) return getFrom();
        return basicGetFrom();
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
      case FSMPackage.MESSAGE_FROM_IF__MESSAGE:
        setMessage((EObject)newValue);
        return;
      case FSMPackage.MESSAGE_FROM_IF__FROM:
        setFrom((AbstractInterfaceItem)newValue);
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
      case FSMPackage.MESSAGE_FROM_IF__MESSAGE:
        setMessage((EObject)null);
        return;
      case FSMPackage.MESSAGE_FROM_IF__FROM:
        setFrom((AbstractInterfaceItem)null);
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
      case FSMPackage.MESSAGE_FROM_IF__MESSAGE:
        return message != null;
      case FSMPackage.MESSAGE_FROM_IF__FROM:
        return from != null;
    }
    return super.eIsSet(featureID);
  }

} //MessageFromIfImpl
