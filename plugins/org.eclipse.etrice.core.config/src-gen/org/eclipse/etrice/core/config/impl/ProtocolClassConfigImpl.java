/**
 */
package org.eclipse.etrice.core.config.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.etrice.core.config.ConfigPackage;
import org.eclipse.etrice.core.config.PortClassConfig;
import org.eclipse.etrice.core.config.ProtocolClassConfig;

import org.eclipse.etrice.core.room.ProtocolClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Protocol Class Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.config.impl.ProtocolClassConfigImpl#getProtocol <em>Protocol</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.config.impl.ProtocolClassConfigImpl#getRegular <em>Regular</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.config.impl.ProtocolClassConfigImpl#getConjugated <em>Conjugated</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProtocolClassConfigImpl extends ConfigElementImpl implements ProtocolClassConfig
{
  /**
   * The cached value of the '{@link #getProtocol() <em>Protocol</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProtocol()
   * @generated
   * @ordered
   */
  protected ProtocolClass protocol;

  /**
   * The cached value of the '{@link #getRegular() <em>Regular</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRegular()
   * @generated
   * @ordered
   */
  protected PortClassConfig regular;

  /**
   * The cached value of the '{@link #getConjugated() <em>Conjugated</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConjugated()
   * @generated
   * @ordered
   */
  protected PortClassConfig conjugated;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProtocolClassConfigImpl()
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
    return ConfigPackage.Literals.PROTOCOL_CLASS_CONFIG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProtocolClass getProtocol()
  {
    if (protocol != null && protocol.eIsProxy())
    {
      InternalEObject oldProtocol = (InternalEObject)protocol;
      protocol = (ProtocolClass)eResolveProxy(oldProtocol);
      if (protocol != oldProtocol)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL, oldProtocol, protocol));
      }
    }
    return protocol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProtocolClass basicGetProtocol()
  {
    return protocol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProtocol(ProtocolClass newProtocol)
  {
    ProtocolClass oldProtocol = protocol;
    protocol = newProtocol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL, oldProtocol, protocol));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PortClassConfig getRegular()
  {
    return regular;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRegular(PortClassConfig newRegular, NotificationChain msgs)
  {
    PortClassConfig oldRegular = regular;
    regular = newRegular;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR, oldRegular, newRegular);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRegular(PortClassConfig newRegular)
  {
    if (newRegular != regular)
    {
      NotificationChain msgs = null;
      if (regular != null)
        msgs = ((InternalEObject)regular).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR, null, msgs);
      if (newRegular != null)
        msgs = ((InternalEObject)newRegular).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR, null, msgs);
      msgs = basicSetRegular(newRegular, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR, newRegular, newRegular));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PortClassConfig getConjugated()
  {
    return conjugated;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConjugated(PortClassConfig newConjugated, NotificationChain msgs)
  {
    PortClassConfig oldConjugated = conjugated;
    conjugated = newConjugated;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED, oldConjugated, newConjugated);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConjugated(PortClassConfig newConjugated)
  {
    if (newConjugated != conjugated)
    {
      NotificationChain msgs = null;
      if (conjugated != null)
        msgs = ((InternalEObject)conjugated).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED, null, msgs);
      if (newConjugated != null)
        msgs = ((InternalEObject)newConjugated).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED, null, msgs);
      msgs = basicSetConjugated(newConjugated, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED, newConjugated, newConjugated));
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
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR:
        return basicSetRegular(null, msgs);
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED:
        return basicSetConjugated(null, msgs);
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
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL:
        if (resolve) return getProtocol();
        return basicGetProtocol();
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR:
        return getRegular();
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED:
        return getConjugated();
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
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL:
        setProtocol((ProtocolClass)newValue);
        return;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR:
        setRegular((PortClassConfig)newValue);
        return;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED:
        setConjugated((PortClassConfig)newValue);
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
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL:
        setProtocol((ProtocolClass)null);
        return;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR:
        setRegular((PortClassConfig)null);
        return;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED:
        setConjugated((PortClassConfig)null);
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
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__PROTOCOL:
        return protocol != null;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__REGULAR:
        return regular != null;
      case ConfigPackage.PROTOCOL_CLASS_CONFIG__CONJUGATED:
        return conjugated != null;
    }
    return super.eIsSet(featureID);
  }

} //ProtocolClassConfigImpl
