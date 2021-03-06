/**
 */
package org.eclipse.etrice.core.fsm.fSM;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>A transition point is located at the border of a {@link State}
 * and is not visible or accessible outside. It is used
 * to globally handle messages. If a {@link Transition} starting
 * at a transition point triggers then exit and entry codes
 * are executed as usual as long as the handler flag of the
 * transition point isn't set.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.fsm.fSM.TransitionPoint#isHandler <em>Handler</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getTransitionPoint()
 * @model
 * @generated
 */
public interface TransitionPoint extends TrPoint
{
  /**
   * Returns the value of the '<em><b>Handler</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>If this flag is {@code true} then for a transition triggerd from this point
   * no exit and entry codes of the states left and entered are executed.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Handler</em>' attribute.
   * @see #setHandler(boolean)
   * @see org.eclipse.etrice.core.fsm.fSM.FSMPackage#getTransitionPoint_Handler()
   * @model
   * @generated
   */
  boolean isHandler();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.fsm.fSM.TransitionPoint#isHandler <em>Handler</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Handler</em>' attribute.
   * @see #isHandler()
   * @generated
   */
  void setHandler(boolean value);

} // TransitionPoint
