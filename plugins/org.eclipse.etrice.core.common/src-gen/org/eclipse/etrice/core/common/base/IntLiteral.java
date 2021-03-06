/**
 */
package org.eclipse.etrice.core.common.base;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.common.base.IntLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.etrice.core.common.base.BasePackage#getIntLiteral()
 * @model
 * @generated
 */
public interface IntLiteral extends NumberLiteral
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(long)
   * @see org.eclipse.etrice.core.common.base.BasePackage#getIntLiteral_Value()
   * @model
   * @generated
   */
  long getValue();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.common.base.IntLiteral#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(long value);

} // IntLiteral
