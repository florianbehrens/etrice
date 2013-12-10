/**
 */
package org.eclipse.etrice.core.config;

import org.eclipse.etrice.core.common.base.Literal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Config Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.config.LiteralConfigValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.etrice.core.config.ConfigPackage#getLiteralConfigValue()
 * @model
 * @generated
 */
public interface LiteralConfigValue extends ConfigValue
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(Literal)
   * @see org.eclipse.etrice.core.config.ConfigPackage#getLiteralConfigValue_Value()
   * @model containment="true"
   * @generated
   */
  Literal getValue();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.config.LiteralConfigValue#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(Literal value);

} // LiteralConfigValue