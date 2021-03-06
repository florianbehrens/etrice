/**
 */
package org.eclipse.etrice.core.room;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>A binding connects two {@link Port}s.
 * To be able to distinguish the ports of two {@link ActorContainerRef}s of the same type
 * a {@link BindingEndPoint} is needed for disambiguation.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.room.Binding#getEndpoint1 <em>Endpoint1</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.Binding#getEndpoint2 <em>Endpoint2</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.room.RoomPackage#getBinding()
 * @model
 * @generated
 */
public interface Binding extends EObject
{
  /**
   * Returns the value of the '<em><b>Endpoint1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>The first end point of a binding.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Endpoint1</em>' containment reference.
   * @see #setEndpoint1(BindingEndPoint)
   * @see org.eclipse.etrice.core.room.RoomPackage#getBinding_Endpoint1()
   * @model containment="true"
   * @generated
   */
  BindingEndPoint getEndpoint1();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.Binding#getEndpoint1 <em>Endpoint1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endpoint1</em>' containment reference.
   * @see #getEndpoint1()
   * @generated
   */
  void setEndpoint1(BindingEndPoint value);

  /**
   * Returns the value of the '<em><b>Endpoint2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>The second end point of a binding.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Endpoint2</em>' containment reference.
   * @see #setEndpoint2(BindingEndPoint)
   * @see org.eclipse.etrice.core.room.RoomPackage#getBinding_Endpoint2()
   * @model containment="true"
   * @generated
   */
  BindingEndPoint getEndpoint2();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.Binding#getEndpoint2 <em>Endpoint2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endpoint2</em>' containment reference.
   * @see #getEndpoint2()
   * @generated
   */
  void setEndpoint2(BindingEndPoint value);

} // Binding
