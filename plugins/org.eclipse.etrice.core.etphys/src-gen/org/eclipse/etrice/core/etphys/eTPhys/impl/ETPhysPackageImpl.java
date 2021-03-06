/**
 */
package org.eclipse.etrice.core.etphys.eTPhys.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.etrice.core.common.base.BasePackage;

import org.eclipse.etrice.core.etphys.eTPhys.ETPhysFactory;
import org.eclipse.etrice.core.etphys.eTPhys.ETPhysPackage;
import org.eclipse.etrice.core.etphys.eTPhys.ExecMode;
import org.eclipse.etrice.core.etphys.eTPhys.NodeClass;
import org.eclipse.etrice.core.etphys.eTPhys.NodeRef;
import org.eclipse.etrice.core.etphys.eTPhys.PhysicalModel;
import org.eclipse.etrice.core.etphys.eTPhys.PhysicalSystem;
import org.eclipse.etrice.core.etphys.eTPhys.PhysicalThread;
import org.eclipse.etrice.core.etphys.eTPhys.RuntimeClass;
import org.eclipse.etrice.core.etphys.eTPhys.ThreadModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ETPhysPackageImpl extends EPackageImpl implements ETPhysPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass physicalModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass physicalSystemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass physicalThreadEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass runtimeClassEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum execModeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum threadModelEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.etrice.core.etphys.eTPhys.ETPhysPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ETPhysPackageImpl()
  {
    super(eNS_URI, ETPhysFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link ETPhysPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ETPhysPackage init()
  {
    if (isInited) return (ETPhysPackage)EPackage.Registry.INSTANCE.getEPackage(ETPhysPackage.eNS_URI);

    // Obtain or create and register package
    ETPhysPackageImpl theETPhysPackage = (ETPhysPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ETPhysPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ETPhysPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    BasePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theETPhysPackage.createPackageContents();

    // Initialize created meta-data
    theETPhysPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theETPhysPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ETPhysPackage.eNS_URI, theETPhysPackage);
    return theETPhysPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPhysicalModel()
  {
    return physicalModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalModel_Name()
  {
    return (EAttribute)physicalModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalModel_Imports()
  {
    return (EReference)physicalModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalModel_Systems()
  {
    return (EReference)physicalModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalModel_NodeClasses()
  {
    return (EReference)physicalModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalModel_RuntimeClasses()
  {
    return (EReference)physicalModelEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPhysicalSystem()
  {
    return physicalSystemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalSystem_Name()
  {
    return (EAttribute)physicalSystemEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalSystem_Docu()
  {
    return (EReference)physicalSystemEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPhysicalSystem_NodeRefs()
  {
    return (EReference)physicalSystemEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeRef()
  {
    return nodeRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeRef_Name()
  {
    return (EAttribute)nodeRefEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeRef_Type()
  {
    return (EReference)nodeRefEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeRef_Docu()
  {
    return (EReference)nodeRefEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeClass()
  {
    return nodeClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeClass_Name()
  {
    return (EAttribute)nodeClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeClass_Docu()
  {
    return (EReference)nodeClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeClass_Runtime()
  {
    return (EReference)nodeClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeClass_Priomin()
  {
    return (EAttribute)nodeClassEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeClass_Priomax()
  {
    return (EAttribute)nodeClassEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeClass_Threads()
  {
    return (EReference)nodeClassEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPhysicalThread()
  {
    return physicalThreadEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Default()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Name()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Execmode()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Time()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Prio()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Stacksize()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Msgblocksize()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPhysicalThread_Msgpoolsize()
  {
    return (EAttribute)physicalThreadEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRuntimeClass()
  {
    return runtimeClassEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRuntimeClass_Name()
  {
    return (EAttribute)runtimeClassEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRuntimeClass_Docu()
  {
    return (EReference)runtimeClassEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRuntimeClass_ThreadModel()
  {
    return (EAttribute)runtimeClassEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getExecMode()
  {
    return execModeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getThreadModel()
  {
    return threadModelEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETPhysFactory getETPhysFactory()
  {
    return (ETPhysFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    physicalModelEClass = createEClass(PHYSICAL_MODEL);
    createEAttribute(physicalModelEClass, PHYSICAL_MODEL__NAME);
    createEReference(physicalModelEClass, PHYSICAL_MODEL__IMPORTS);
    createEReference(physicalModelEClass, PHYSICAL_MODEL__SYSTEMS);
    createEReference(physicalModelEClass, PHYSICAL_MODEL__NODE_CLASSES);
    createEReference(physicalModelEClass, PHYSICAL_MODEL__RUNTIME_CLASSES);

    physicalSystemEClass = createEClass(PHYSICAL_SYSTEM);
    createEAttribute(physicalSystemEClass, PHYSICAL_SYSTEM__NAME);
    createEReference(physicalSystemEClass, PHYSICAL_SYSTEM__DOCU);
    createEReference(physicalSystemEClass, PHYSICAL_SYSTEM__NODE_REFS);

    nodeRefEClass = createEClass(NODE_REF);
    createEAttribute(nodeRefEClass, NODE_REF__NAME);
    createEReference(nodeRefEClass, NODE_REF__TYPE);
    createEReference(nodeRefEClass, NODE_REF__DOCU);

    nodeClassEClass = createEClass(NODE_CLASS);
    createEAttribute(nodeClassEClass, NODE_CLASS__NAME);
    createEReference(nodeClassEClass, NODE_CLASS__DOCU);
    createEReference(nodeClassEClass, NODE_CLASS__RUNTIME);
    createEAttribute(nodeClassEClass, NODE_CLASS__PRIOMIN);
    createEAttribute(nodeClassEClass, NODE_CLASS__PRIOMAX);
    createEReference(nodeClassEClass, NODE_CLASS__THREADS);

    physicalThreadEClass = createEClass(PHYSICAL_THREAD);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__DEFAULT);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__NAME);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__EXECMODE);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__TIME);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__PRIO);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__STACKSIZE);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__MSGBLOCKSIZE);
    createEAttribute(physicalThreadEClass, PHYSICAL_THREAD__MSGPOOLSIZE);

    runtimeClassEClass = createEClass(RUNTIME_CLASS);
    createEAttribute(runtimeClassEClass, RUNTIME_CLASS__NAME);
    createEReference(runtimeClassEClass, RUNTIME_CLASS__DOCU);
    createEAttribute(runtimeClassEClass, RUNTIME_CLASS__THREAD_MODEL);

    // Create enums
    execModeEEnum = createEEnum(EXEC_MODE);
    threadModelEEnum = createEEnum(THREAD_MODEL);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    BasePackage theBasePackage = (BasePackage)EPackage.Registry.INSTANCE.getEPackage(BasePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(physicalModelEClass, PhysicalModel.class, "PhysicalModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPhysicalModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, PhysicalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalModel_Imports(), theBasePackage.getImport(), null, "imports", null, 0, -1, PhysicalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalModel_Systems(), this.getPhysicalSystem(), null, "systems", null, 0, -1, PhysicalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalModel_NodeClasses(), this.getNodeClass(), null, "nodeClasses", null, 0, -1, PhysicalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalModel_RuntimeClasses(), this.getRuntimeClass(), null, "runtimeClasses", null, 0, -1, PhysicalModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(physicalSystemEClass, PhysicalSystem.class, "PhysicalSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPhysicalSystem_Name(), ecorePackage.getEString(), "name", null, 0, 1, PhysicalSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalSystem_Docu(), theBasePackage.getDocumentation(), null, "docu", null, 0, 1, PhysicalSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPhysicalSystem_NodeRefs(), this.getNodeRef(), null, "nodeRefs", null, 0, -1, PhysicalSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeRefEClass, NodeRef.class, "NodeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNodeRef_Name(), ecorePackage.getEString(), "name", null, 0, 1, NodeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeRef_Type(), this.getNodeClass(), null, "type", null, 0, 1, NodeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeRef_Docu(), theBasePackage.getDocumentation(), null, "docu", null, 0, 1, NodeRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeClassEClass, NodeClass.class, "NodeClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNodeClass_Name(), ecorePackage.getEString(), "name", null, 0, 1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeClass_Docu(), theBasePackage.getDocumentation(), null, "docu", null, 0, 1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeClass_Runtime(), this.getRuntimeClass(), null, "runtime", null, 0, 1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNodeClass_Priomin(), ecorePackage.getELong(), "priomin", null, 0, 1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getNodeClass_Priomax(), ecorePackage.getELong(), "priomax", null, 0, 1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeClass_Threads(), this.getPhysicalThread(), null, "threads", null, 0, -1, NodeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(physicalThreadEClass, PhysicalThread.class, "PhysicalThread", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPhysicalThread_Default(), ecorePackage.getEBoolean(), "default", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Name(), ecorePackage.getEString(), "name", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Execmode(), this.getExecMode(), "execmode", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Time(), ecorePackage.getELong(), "time", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Prio(), ecorePackage.getELong(), "prio", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Stacksize(), ecorePackage.getEInt(), "stacksize", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Msgblocksize(), ecorePackage.getEInt(), "msgblocksize", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPhysicalThread_Msgpoolsize(), ecorePackage.getEInt(), "msgpoolsize", null, 0, 1, PhysicalThread.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(runtimeClassEClass, RuntimeClass.class, "RuntimeClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRuntimeClass_Name(), ecorePackage.getEString(), "name", null, 0, 1, RuntimeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRuntimeClass_Docu(), theBasePackage.getDocumentation(), null, "docu", null, 0, 1, RuntimeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRuntimeClass_ThreadModel(), this.getThreadModel(), "threadModel", null, 0, 1, RuntimeClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(execModeEEnum, ExecMode.class, "ExecMode");
    addEEnumLiteral(execModeEEnum, ExecMode.POLLED);
    addEEnumLiteral(execModeEEnum, ExecMode.BLOCKED);
    addEEnumLiteral(execModeEEnum, ExecMode.MIXED);

    initEEnum(threadModelEEnum, ThreadModel.class, "ThreadModel");
    addEEnumLiteral(threadModelEEnum, ThreadModel.SINGLE_THREADED);
    addEEnumLiteral(threadModelEEnum, ThreadModel.MULTI_THREADED);

    // Create resource
    createResource(eNS_URI);
  }

} //ETPhysPackageImpl
