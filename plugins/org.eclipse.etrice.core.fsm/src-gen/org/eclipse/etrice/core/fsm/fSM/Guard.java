/**
 */
package org.eclipse.etrice.core.fsm.fSM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Guard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>A detail code that evaluates to a boolean in the target
 * language.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.Guard#getGuard <em>Guard</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getGuard()
 * @model
 * @generated
 */
public interface Guard extends EObject
{
  /**
   * Returns the value of the '<em><b>Guard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a guard condition specified in the code generator's target language.
   * It has to evaluate to a boolean value.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Guard</em>' containment reference.
   * @see #setGuard(DetailCode)
   * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getGuard_Guard()
   * @model containment="true"
   * @generated
   */
  DetailCode getGuard();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.fsm.fSM.Guard#getGuard <em>Guard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Guard</em>' containment reference.
   * @see #getGuard()
   * @generated
   */
  void setGuard(DetailCode value);

} // Guard
