/**
 */
package org.eclipse.etrice.core.etphys.eTPhys.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.etrice.core.etphys.eTPhys.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ETPhysFactoryImpl extends EFactoryImpl implements ETPhysFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ETPhysFactory init()
  {
    try
    {
      ETPhysFactory theETPhysFactory = (ETPhysFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/etrice/core/etphys/ETPhys"); 
      if (theETPhysFactory != null)
      {
        return theETPhysFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ETPhysFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETPhysFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ETPhysPackage.PHYSICAL_MODEL: return createPhysicalModel();
      case ETPhysPackage.PHYSICAL_SYSTEM: return createPhysicalSystem();
      case ETPhysPackage.NODE_REF: return createNodeRef();
      case ETPhysPackage.NODE_CLASS: return createNodeClass();
      case ETPhysPackage.PHYSICAL_THREAD: return createPhysicalThread();
      case ETPhysPackage.RUNTIME_CLASS: return createRuntimeClass();
      case ETPhysPackage.DOCUMENTATION: return createDocumentation();
      case ETPhysPackage.IMPORT: return createImport();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ETPhysPackage.EXEC_MODE:
        return createExecModeFromString(eDataType, initialValue);
      case ETPhysPackage.THREAD_MODEL:
        return createThreadModelFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ETPhysPackage.EXEC_MODE:
        return convertExecModeToString(eDataType, instanceValue);
      case ETPhysPackage.THREAD_MODEL:
        return convertThreadModelToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PhysicalModel createPhysicalModel()
  {
    PhysicalModelImpl physicalModel = new PhysicalModelImpl();
    return physicalModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PhysicalSystem createPhysicalSystem()
  {
    PhysicalSystemImpl physicalSystem = new PhysicalSystemImpl();
    return physicalSystem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeRef createNodeRef()
  {
    NodeRefImpl nodeRef = new NodeRefImpl();
    return nodeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeClass createNodeClass()
  {
    NodeClassImpl nodeClass = new NodeClassImpl();
    return nodeClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PhysicalThread createPhysicalThread()
  {
    PhysicalThreadImpl physicalThread = new PhysicalThreadImpl();
    return physicalThread;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RuntimeClass createRuntimeClass()
  {
    RuntimeClassImpl runtimeClass = new RuntimeClassImpl();
    return runtimeClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Documentation createDocumentation()
  {
    DocumentationImpl documentation = new DocumentationImpl();
    return documentation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecMode createExecModeFromString(EDataType eDataType, String initialValue)
  {
    ExecMode result = ExecMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertExecModeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThreadModel createThreadModelFromString(EDataType eDataType, String initialValue)
  {
    ThreadModel result = ThreadModel.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertThreadModelToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETPhysPackage getETPhysPackage()
  {
    return (ETPhysPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ETPhysPackage getPackage()
  {
    return ETPhysPackage.eINSTANCE;
  }

} //ETPhysFactoryImpl