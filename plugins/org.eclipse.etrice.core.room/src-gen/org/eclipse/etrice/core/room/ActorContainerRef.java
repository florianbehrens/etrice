/**
 */
package org.eclipse.etrice.core.room;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.etrice.core.common.base.Documentation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor Container Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>The super class of
 * <ul>
 *   <li>{@link SubSystemRef}</li>
 *   <li>{@link ActorRef}</li>
 * </ul>
 * The reference designates a specific role of the referenced structure class.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.room.ActorContainerRef#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorContainerRef#getDocu <em>Docu</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.room.RoomPackage#getActorContainerRef()
 * @model
 * @generated
 */
public interface ActorContainerRef extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>By this name the actor container reference is referred to in the model.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorContainerRef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.ActorContainerRef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Docu</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is an optional documentation.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Docu</em>' containment reference.
   * @see #setDocu(Documentation)
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorContainerRef_Docu()
   * @model containment="true"
   * @generated
   */
  Documentation getDocu();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.ActorContainerRef#getDocu <em>Docu</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Docu</em>' containment reference.
   * @see #getDocu()
   * @generated
   */
  void setDocu(Documentation value);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (this instanceof <%org.eclipse.etrice.core.room.ActorRef%>)\r\n\treturn ((ActorRef)this).getType();\r\nelse if (this instanceof <%org.eclipse.etrice.core.room.SubSystemRef%>)\r\n\treturn ((SubSystemRef)this).getType();\r\nelse\r\n\treturn null;\r\n'"
   * @generated
   */
  StructureClass getStructureClass();

} // ActorContainerRef
