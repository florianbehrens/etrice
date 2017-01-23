/**
 */
package org.eclipse.etrice.core.fsm.fSM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Terminal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>The terminal point (source or target) of a transition ending
 * at a {@link State}.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.StateTerminal#getState <em>State</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getStateTerminal()
 * @model
 * @generated
 */
public interface StateTerminal extends TransitionTerminal
{
  /**
   * Returns the value of the '<em><b>State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is the target state.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>State</em>' reference.
   * @see #setState(State)
   * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getStateTerminal_State()
   * @model
   * @generated
   */
  State getState();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.fsm.fSM.StateTerminal#getState <em>State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State</em>' reference.
   * @see #getState()
   * @generated
   */
  void setState(State value);

} // StateTerminal
