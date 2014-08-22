/**
 */
package org.eclipse.etrice.core.fsm.fSM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IInterface Item Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getIInterfaceItemOwner()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IInterfaceItemOwner extends EObject
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EList<AbstractInterfaceItem> getAbstractInterfaceItems();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   * @generated
   */
  EList<AbstractInterfaceItem> getAllAbstractInterfaceItems();

} // IInterfaceItemOwner
