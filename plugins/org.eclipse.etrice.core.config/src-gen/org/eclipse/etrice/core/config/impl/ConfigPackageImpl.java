/**
 */
package org.eclipse.etrice.core.config.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.etrice.core.common.base.BasePackage;

import org.eclipse.etrice.core.config.ActorClassConfig;
import org.eclipse.etrice.core.config.ActorInstanceConfig;
import org.eclipse.etrice.core.config.AttrClassConfig;
import org.eclipse.etrice.core.config.AttrConfig;
import org.eclipse.etrice.core.config.AttrInstanceConfig;
import org.eclipse.etrice.core.config.ConfigElement;
import org.eclipse.etrice.core.config.ConfigFactory;
import org.eclipse.etrice.core.config.ConfigModel;
import org.eclipse.etrice.core.config.ConfigPackage;
import org.eclipse.etrice.core.config.ConfigValue;
import org.eclipse.etrice.core.config.ConfigValueArray;
import org.eclipse.etrice.core.config.DynamicConfig;
import org.eclipse.etrice.core.config.EnumConfigValue;
import org.eclipse.etrice.core.config.LiteralConfigValue;
import org.eclipse.etrice.core.config.PortClassConfig;
import org.eclipse.etrice.core.config.PortInstanceConfig;
import org.eclipse.etrice.core.config.ProtocolClassConfig;
import org.eclipse.etrice.core.config.RefPath;
import org.eclipse.etrice.core.config.RefSegment;
import org.eclipse.etrice.core.config.SubSystemConfig;

import org.eclipse.etrice.core.room.RoomPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigPackageImpl extends EPackageImpl implements ConfigPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass subSystemConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dynamicConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actorClassConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actorInstanceConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass protocolClassConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass portClassConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass portInstanceConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass attrConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass attrClassConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass attrInstanceConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configValueArrayEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass configValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass literalConfigValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumConfigValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass refPathEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass refSegmentEClass = null;

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
   * @see org.eclipse.etrice.core.config.ConfigPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ConfigPackageImpl()
  {
    super(eNS_URI, ConfigFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ConfigPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ConfigPackage init()
  {
    if (isInited) return (ConfigPackage)EPackage.Registry.INSTANCE.getEPackage(ConfigPackage.eNS_URI);

    // Obtain or create and register package
    ConfigPackageImpl theConfigPackage = (ConfigPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConfigPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConfigPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    RoomPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theConfigPackage.createPackageContents();

    // Initialize created meta-data
    theConfigPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theConfigPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ConfigPackage.eNS_URI, theConfigPackage);
    return theConfigPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigModel()
  {
    return configModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConfigModel_Name()
  {
    return (EAttribute)configModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConfigModel_Imports()
  {
    return (EReference)configModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConfigModel_ConfigElements()
  {
    return (EReference)configModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigElement()
  {
    return configElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSubSystemConfig()
  {
    return subSystemConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSubSystemConfig_Root()
  {
    return (EReference)subSystemConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSubSystemConfig_SubSystem()
  {
    return (EReference)subSystemConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSubSystemConfig_DynConfig()
  {
    return (EReference)subSystemConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDynamicConfig()
  {
    return dynamicConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDynamicConfig_FilePath()
  {
    return (EAttribute)dynamicConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDynamicConfig_UserCode1()
  {
    return (EAttribute)dynamicConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDynamicConfig_UserCode2()
  {
    return (EAttribute)dynamicConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDynamicConfig_Polling()
  {
    return (EAttribute)dynamicConfigEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getActorClassConfig()
  {
    return actorClassConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorClassConfig_Actor()
  {
    return (EReference)actorClassConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorClassConfig_Attributes()
  {
    return (EReference)actorClassConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getActorInstanceConfig()
  {
    return actorInstanceConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorInstanceConfig_Root()
  {
    return (EReference)actorInstanceConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorInstanceConfig_SubSystem()
  {
    return (EReference)actorInstanceConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorInstanceConfig_Path()
  {
    return (EReference)actorInstanceConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorInstanceConfig_Attributes()
  {
    return (EReference)actorInstanceConfigEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActorInstanceConfig_Ports()
  {
    return (EReference)actorInstanceConfigEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProtocolClassConfig()
  {
    return protocolClassConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProtocolClassConfig_Protocol()
  {
    return (EReference)protocolClassConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProtocolClassConfig_Regular()
  {
    return (EReference)protocolClassConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProtocolClassConfig_Conjugated()
  {
    return (EReference)protocolClassConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPortClassConfig()
  {
    return portClassConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPortClassConfig_Attributes()
  {
    return (EReference)portClassConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPortInstanceConfig()
  {
    return portInstanceConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPortInstanceConfig_Item()
  {
    return (EReference)portInstanceConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPortInstanceConfig_Attributes()
  {
    return (EReference)portInstanceConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttrConfig()
  {
    return attrConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrConfig_Attribute()
  {
    return (EReference)attrConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrConfig_Value()
  {
    return (EReference)attrConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttrClassConfig()
  {
    return attrClassConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrClassConfig_Min()
  {
    return (EReference)attrClassConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrClassConfig_Max()
  {
    return (EReference)attrClassConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrClassConfig_Attributes()
  {
    return (EReference)attrClassConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAttrInstanceConfig()
  {
    return attrInstanceConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAttrInstanceConfig_DynConfig()
  {
    return (EAttribute)attrInstanceConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAttrInstanceConfig_ReadOnly()
  {
    return (EAttribute)attrInstanceConfigEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAttrInstanceConfig_Attributes()
  {
    return (EReference)attrInstanceConfigEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigValueArray()
  {
    return configValueArrayEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConfigValueArray_Values()
  {
    return (EReference)configValueArrayEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConfigValue()
  {
    return configValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLiteralConfigValue()
  {
    return literalConfigValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLiteralConfigValue_Value()
  {
    return (EReference)literalConfigValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumConfigValue()
  {
    return enumConfigValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumConfigValue_Type()
  {
    return (EReference)enumConfigValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumConfigValue_Value()
  {
    return (EReference)enumConfigValueEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRefPath()
  {
    return refPathEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRefPath_Refs()
  {
    return (EReference)refPathEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRefSegment()
  {
    return refSegmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRefSegment_Ref()
  {
    return (EAttribute)refSegmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRefSegment_Idx()
  {
    return (EAttribute)refSegmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigFactory getConfigFactory()
  {
    return (ConfigFactory)getEFactoryInstance();
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
    configModelEClass = createEClass(CONFIG_MODEL);
    createEAttribute(configModelEClass, CONFIG_MODEL__NAME);
    createEReference(configModelEClass, CONFIG_MODEL__IMPORTS);
    createEReference(configModelEClass, CONFIG_MODEL__CONFIG_ELEMENTS);

    configElementEClass = createEClass(CONFIG_ELEMENT);

    subSystemConfigEClass = createEClass(SUB_SYSTEM_CONFIG);
    createEReference(subSystemConfigEClass, SUB_SYSTEM_CONFIG__ROOT);
    createEReference(subSystemConfigEClass, SUB_SYSTEM_CONFIG__SUB_SYSTEM);
    createEReference(subSystemConfigEClass, SUB_SYSTEM_CONFIG__DYN_CONFIG);

    dynamicConfigEClass = createEClass(DYNAMIC_CONFIG);
    createEAttribute(dynamicConfigEClass, DYNAMIC_CONFIG__FILE_PATH);
    createEAttribute(dynamicConfigEClass, DYNAMIC_CONFIG__USER_CODE1);
    createEAttribute(dynamicConfigEClass, DYNAMIC_CONFIG__USER_CODE2);
    createEAttribute(dynamicConfigEClass, DYNAMIC_CONFIG__POLLING);

    actorClassConfigEClass = createEClass(ACTOR_CLASS_CONFIG);
    createEReference(actorClassConfigEClass, ACTOR_CLASS_CONFIG__ACTOR);
    createEReference(actorClassConfigEClass, ACTOR_CLASS_CONFIG__ATTRIBUTES);

    actorInstanceConfigEClass = createEClass(ACTOR_INSTANCE_CONFIG);
    createEReference(actorInstanceConfigEClass, ACTOR_INSTANCE_CONFIG__ROOT);
    createEReference(actorInstanceConfigEClass, ACTOR_INSTANCE_CONFIG__SUB_SYSTEM);
    createEReference(actorInstanceConfigEClass, ACTOR_INSTANCE_CONFIG__PATH);
    createEReference(actorInstanceConfigEClass, ACTOR_INSTANCE_CONFIG__ATTRIBUTES);
    createEReference(actorInstanceConfigEClass, ACTOR_INSTANCE_CONFIG__PORTS);

    protocolClassConfigEClass = createEClass(PROTOCOL_CLASS_CONFIG);
    createEReference(protocolClassConfigEClass, PROTOCOL_CLASS_CONFIG__PROTOCOL);
    createEReference(protocolClassConfigEClass, PROTOCOL_CLASS_CONFIG__REGULAR);
    createEReference(protocolClassConfigEClass, PROTOCOL_CLASS_CONFIG__CONJUGATED);

    portClassConfigEClass = createEClass(PORT_CLASS_CONFIG);
    createEReference(portClassConfigEClass, PORT_CLASS_CONFIG__ATTRIBUTES);

    portInstanceConfigEClass = createEClass(PORT_INSTANCE_CONFIG);
    createEReference(portInstanceConfigEClass, PORT_INSTANCE_CONFIG__ITEM);
    createEReference(portInstanceConfigEClass, PORT_INSTANCE_CONFIG__ATTRIBUTES);

    attrConfigEClass = createEClass(ATTR_CONFIG);
    createEReference(attrConfigEClass, ATTR_CONFIG__ATTRIBUTE);
    createEReference(attrConfigEClass, ATTR_CONFIG__VALUE);

    attrClassConfigEClass = createEClass(ATTR_CLASS_CONFIG);
    createEReference(attrClassConfigEClass, ATTR_CLASS_CONFIG__MIN);
    createEReference(attrClassConfigEClass, ATTR_CLASS_CONFIG__MAX);
    createEReference(attrClassConfigEClass, ATTR_CLASS_CONFIG__ATTRIBUTES);

    attrInstanceConfigEClass = createEClass(ATTR_INSTANCE_CONFIG);
    createEAttribute(attrInstanceConfigEClass, ATTR_INSTANCE_CONFIG__DYN_CONFIG);
    createEAttribute(attrInstanceConfigEClass, ATTR_INSTANCE_CONFIG__READ_ONLY);
    createEReference(attrInstanceConfigEClass, ATTR_INSTANCE_CONFIG__ATTRIBUTES);

    configValueArrayEClass = createEClass(CONFIG_VALUE_ARRAY);
    createEReference(configValueArrayEClass, CONFIG_VALUE_ARRAY__VALUES);

    configValueEClass = createEClass(CONFIG_VALUE);

    literalConfigValueEClass = createEClass(LITERAL_CONFIG_VALUE);
    createEReference(literalConfigValueEClass, LITERAL_CONFIG_VALUE__VALUE);

    enumConfigValueEClass = createEClass(ENUM_CONFIG_VALUE);
    createEReference(enumConfigValueEClass, ENUM_CONFIG_VALUE__TYPE);
    createEReference(enumConfigValueEClass, ENUM_CONFIG_VALUE__VALUE);

    refPathEClass = createEClass(REF_PATH);
    createEReference(refPathEClass, REF_PATH__REFS);

    refSegmentEClass = createEClass(REF_SEGMENT);
    createEAttribute(refSegmentEClass, REF_SEGMENT__REF);
    createEAttribute(refSegmentEClass, REF_SEGMENT__IDX);
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
    RoomPackage theRoomPackage = (RoomPackage)EPackage.Registry.INSTANCE.getEPackage(RoomPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    subSystemConfigEClass.getESuperTypes().add(this.getConfigElement());
    actorClassConfigEClass.getESuperTypes().add(this.getConfigElement());
    actorInstanceConfigEClass.getESuperTypes().add(this.getConfigElement());
    protocolClassConfigEClass.getESuperTypes().add(this.getConfigElement());
    attrClassConfigEClass.getESuperTypes().add(this.getAttrConfig());
    attrInstanceConfigEClass.getESuperTypes().add(this.getAttrConfig());
    literalConfigValueEClass.getESuperTypes().add(this.getConfigValue());
    enumConfigValueEClass.getESuperTypes().add(this.getConfigValue());

    // Initialize classes and features; add operations and parameters
    initEClass(configModelEClass, ConfigModel.class, "ConfigModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConfigModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConfigModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConfigModel_Imports(), theBasePackage.getImport(), null, "imports", null, 0, -1, ConfigModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConfigModel_ConfigElements(), this.getConfigElement(), null, "configElements", null, 0, -1, ConfigModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    addEOperation(configModelEClass, this.getActorClassConfig(), "getActorClassConfigs", 0, -1, IS_UNIQUE, IS_ORDERED);

    addEOperation(configModelEClass, this.getActorInstanceConfig(), "getActorInstanceConfigs", 0, -1, IS_UNIQUE, IS_ORDERED);

    addEOperation(configModelEClass, this.getProtocolClassConfig(), "getProtocolClassConfigs", 0, -1, IS_UNIQUE, IS_ORDERED);

    addEOperation(configModelEClass, this.getSubSystemConfig(), "getSubSystemConfigs", 0, -1, IS_UNIQUE, IS_ORDERED);

    initEClass(configElementEClass, ConfigElement.class, "ConfigElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(subSystemConfigEClass, SubSystemConfig.class, "SubSystemConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSubSystemConfig_Root(), theRoomPackage.getLogicalSystem(), null, "root", null, 0, 1, SubSystemConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSubSystemConfig_SubSystem(), theRoomPackage.getSubSystemRef(), null, "subSystem", null, 0, 1, SubSystemConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSubSystemConfig_DynConfig(), this.getDynamicConfig(), null, "dynConfig", null, 0, 1, SubSystemConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dynamicConfigEClass, DynamicConfig.class, "DynamicConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDynamicConfig_FilePath(), ecorePackage.getEString(), "filePath", null, 0, 1, DynamicConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDynamicConfig_UserCode1(), ecorePackage.getEString(), "userCode1", null, 0, 1, DynamicConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDynamicConfig_UserCode2(), ecorePackage.getEString(), "userCode2", null, 0, 1, DynamicConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDynamicConfig_Polling(), ecorePackage.getELong(), "polling", null, 0, 1, DynamicConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actorClassConfigEClass, ActorClassConfig.class, "ActorClassConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getActorClassConfig_Actor(), theRoomPackage.getActorClass(), null, "actor", null, 0, 1, ActorClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActorClassConfig_Attributes(), this.getAttrClassConfig(), null, "attributes", null, 0, -1, ActorClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actorInstanceConfigEClass, ActorInstanceConfig.class, "ActorInstanceConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getActorInstanceConfig_Root(), theRoomPackage.getLogicalSystem(), null, "root", null, 0, 1, ActorInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActorInstanceConfig_SubSystem(), theRoomPackage.getSubSystemRef(), null, "subSystem", null, 0, 1, ActorInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActorInstanceConfig_Path(), this.getRefPath(), null, "path", null, 0, 1, ActorInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActorInstanceConfig_Attributes(), this.getAttrInstanceConfig(), null, "attributes", null, 0, -1, ActorInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActorInstanceConfig_Ports(), this.getPortInstanceConfig(), null, "ports", null, 0, -1, ActorInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(protocolClassConfigEClass, ProtocolClassConfig.class, "ProtocolClassConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getProtocolClassConfig_Protocol(), theRoomPackage.getProtocolClass(), null, "protocol", null, 0, 1, ProtocolClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProtocolClassConfig_Regular(), this.getPortClassConfig(), null, "regular", null, 0, 1, ProtocolClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProtocolClassConfig_Conjugated(), this.getPortClassConfig(), null, "conjugated", null, 0, 1, ProtocolClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(portClassConfigEClass, PortClassConfig.class, "PortClassConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPortClassConfig_Attributes(), this.getAttrClassConfig(), null, "attributes", null, 0, -1, PortClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(portInstanceConfigEClass, PortInstanceConfig.class, "PortInstanceConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPortInstanceConfig_Item(), theRoomPackage.getInterfaceItem(), null, "item", null, 0, 1, PortInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPortInstanceConfig_Attributes(), this.getAttrInstanceConfig(), null, "attributes", null, 0, -1, PortInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(attrConfigEClass, AttrConfig.class, "AttrConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAttrConfig_Attribute(), theRoomPackage.getAttribute(), null, "attribute", null, 0, 1, AttrConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttrConfig_Value(), this.getConfigValueArray(), null, "value", null, 0, 1, AttrConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(attrClassConfigEClass, AttrClassConfig.class, "AttrClassConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAttrClassConfig_Min(), theBasePackage.getNumberLiteral(), null, "min", null, 0, 1, AttrClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttrClassConfig_Max(), theBasePackage.getNumberLiteral(), null, "max", null, 0, 1, AttrClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttrClassConfig_Attributes(), this.getAttrClassConfig(), null, "attributes", null, 0, -1, AttrClassConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(attrInstanceConfigEClass, AttrInstanceConfig.class, "AttrInstanceConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAttrInstanceConfig_DynConfig(), ecorePackage.getEBoolean(), "dynConfig", null, 0, 1, AttrInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAttrInstanceConfig_ReadOnly(), ecorePackage.getEBoolean(), "readOnly", null, 0, 1, AttrInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAttrInstanceConfig_Attributes(), this.getAttrInstanceConfig(), null, "attributes", null, 0, -1, AttrInstanceConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(configValueArrayEClass, ConfigValueArray.class, "ConfigValueArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConfigValueArray_Values(), this.getConfigValue(), null, "values", null, 0, -1, ConfigValueArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(configValueEClass, ConfigValue.class, "ConfigValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(literalConfigValueEClass, LiteralConfigValue.class, "LiteralConfigValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLiteralConfigValue_Value(), theBasePackage.getLiteral(), null, "value", null, 0, 1, LiteralConfigValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumConfigValueEClass, EnumConfigValue.class, "EnumConfigValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumConfigValue_Type(), theRoomPackage.getEnumerationType(), null, "type", null, 0, 1, EnumConfigValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumConfigValue_Value(), theRoomPackage.getEnumLiteral(), null, "value", null, 0, 1, EnumConfigValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(refPathEClass, RefPath.class, "RefPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRefPath_Refs(), this.getRefSegment(), null, "refs", null, 0, -1, RefPath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(refSegmentEClass, RefSegment.class, "RefSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRefSegment_Ref(), ecorePackage.getEString(), "ref", null, 0, 1, RefSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRefSegment_Idx(), ecorePackage.getEInt(), "idx", "-1", 0, 1, RefSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    addEOperation(refSegmentEClass, ecorePackage.getEString(), "toString", 0, 1, IS_UNIQUE, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ConfigPackageImpl
