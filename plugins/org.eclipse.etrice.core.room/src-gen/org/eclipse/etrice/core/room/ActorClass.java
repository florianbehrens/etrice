/**
 */
package org.eclipse.etrice.core.room;

import org.eclipse.emf.common.util.EList;

import org.eclipse.etrice.core.common.base.Annotation;
import org.eclipse.etrice.core.common.base.Documentation;

import org.eclipse.etrice.core.fsm.fSM.AbstractInterfaceItem;
import org.eclipse.etrice.core.fsm.fSM.ModelComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <br>Together with {@link ProtocolClass} and {@link DataClass} one of
 * the main class types of the ROOM language.
 * 
 * <p>
 * The actor class in ROOM has three compartments which aren't represented as separate model objects.
 * To understand to which compartment an attribute or reference belongs to here is a list
 * <ul>
 *   <li>Interface: everything which is visible from the outside from a model point of view
 *   <ul>
 *     <li>{@link #getInterfacePorts}: a list of all interface {@link Port}s</li>
 *     <li>{@link ActorContainerClass#getServiceProvisionPoints()}: a list of all interface {@link SPP}s</li>
 *   </ul>
 *   <li>Structure: all internal structural aspects of an actor class</li>
 *   <ul>
 *     <li>{@link #getUserCode1}-3: user defined code with generator dependent meaning</li>
 *     <li>{@link #getInternalPorts}: a list of all internal end {@link Port}s</li>
 *     <li>{@link #getExternalPorts}: a list of all {@link ExternalPort}s (the interface ports that
 *        are end ports, not relay ports</li>
 *     <li>{@link #getServiceImplementations}: a list of all {@link ServiceImplementation}s</li>
 *     <li>{@link #getServiceAccessPoints}: a list of all {@link SAP}s used by this actor class</li>
 *     <li>{@link #getAttributes}: a list of all actor class {@link Attribute}s</li>
 *     <li>{@link #getActorRefs}: a list of all referenced actor classes (an {@link ActorRef}
 *        has the meaning of a composition)</li>
 *     <li>{@link #getBindings}: a list of all port {@link Binding}s of this actor class</li>
 *     <li>{@link #getConnections}: a list of all {@link LayerConnection}s</li>
 *   </ul>
 *   <li>Behavior: the behavioral aspects of an actor class</li>
 *   <ul>
 *     <li>{@link #getOperations}: a list of {@link Operation}s</li>
 *     <li>{@link #getStateMachine}: the {@link StateGraph state machine} definition</li>
 *   </ul>
 * </ul>
 * </p>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getInterfacePorts <em>Interface Ports</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getStructureDocu <em>Structure Docu</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getInternalPorts <em>Internal Ports</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getExternalPorts <em>External Ports</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getServiceImplementations <em>Service Implementations</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getServiceAccessPoints <em>Service Access Points</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getBehaviorDocu <em>Behavior Docu</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getBehaviorAnnotations <em>Behavior Annotations</em>}</li>
 *   <li>{@link org.eclipse.etrice.core.room.ActorClass#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass()
 * @model
 * @generated
 */
public interface ActorClass extends ActorContainerClass, ModelComponent
{
  /**
   * Returns the value of the '<em><b>Interface Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.Port}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list containing all ports of the actor interface.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Interface Ports</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_InterfacePorts()
   * @model containment="true"
   * @generated
   */
  EList<Port> getInterfacePorts();

  /**
   * Returns the value of the '<em><b>Structure Docu</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is an optional documentation of the actor structure.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Structure Docu</em>' containment reference.
   * @see #setStructureDocu(Documentation)
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_StructureDocu()
   * @model containment="true"
   * @generated
   */
  Documentation getStructureDocu();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.ActorClass#getStructureDocu <em>Structure Docu</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structure Docu</em>' containment reference.
   * @see #getStructureDocu()
   * @generated
   */
  void setStructureDocu(Documentation value);

  /**
   * Returns the value of the '<em><b>Internal Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.Port}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list containing all ports of the actor structure (internal end ports).
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Internal Ports</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_InternalPorts()
   * @model containment="true"
   * @generated
   */
  EList<Port> getInternalPorts();

  /**
   * Returns the value of the '<em><b>External Ports</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.ExternalPort}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list containing all ports of the actor interface that are end ports.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>External Ports</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_ExternalPorts()
   * @model containment="true"
   * @generated
   */
  EList<ExternalPort> getExternalPorts();

  /**
   * Returns the value of the '<em><b>Service Implementations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.ServiceImplementation}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list of all service implementations.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Service Implementations</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_ServiceImplementations()
   * @model containment="true"
   * @generated
   */
  EList<ServiceImplementation> getServiceImplementations();

  /**
   * Returns the value of the '<em><b>Service Access Points</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.SAP}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list of all SAPs.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Service Access Points</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_ServiceAccessPoints()
   * @model containment="true"
   * @generated
   */
  EList<SAP> getServiceAccessPoints();

  /**
   * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.Attribute}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list of all actor class private attributes.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Attributes</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_Attributes()
   * @model containment="true"
   * @generated
   */
  EList<Attribute> getAttributes();

  /**
   * Returns the value of the '<em><b>Behavior Docu</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is an optional documentation of the actor behavior.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Behavior Docu</em>' containment reference.
   * @see #setBehaviorDocu(Documentation)
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_BehaviorDocu()
   * @model containment="true"
   * @generated
   */
  Documentation getBehaviorDocu();

  /**
   * Sets the value of the '{@link org.eclipse.etrice.core.room.ActorClass#getBehaviorDocu <em>Behavior Docu</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Behavior Docu</em>' containment reference.
   * @see #getBehaviorDocu()
   * @generated
   */
  void setBehaviorDocu(Documentation value);

  /**
   * Returns the value of the '<em><b>Behavior Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.common.base.Annotation}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list of annotations to the actor behavior.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Behavior Annotations</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_BehaviorAnnotations()
   * @model containment="true"
   * @generated
   */
  EList<Annotation> getBehaviorAnnotations();

  /**
   * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.etrice.core.room.StandardOperation}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * <br>This is a list of all private operations of this actor class.
   * 
   * <!-- end-model-doc -->
   * @return the value of the '<em>Operations</em>' containment reference list.
   * @see org.eclipse.etrice.core.room.RoomPackage#getActorClass_Operations()
   * @model containment="true"
   * @generated
   */
  EList<StandardOperation> getOperations();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList<Port> ports = new BasicEList<Port>();\r\nfor (ExternalPort ep : getExternalPorts()) {\r\n\tports.add(ep.getInterfacePort());\r\n}\r\nreturn ports;\r\n'"
   * @generated
   */
  EList<Port> getExternalEndPorts();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList<Port> ports = new BasicEList<Port>(getInterfacePorts());\r\nfor (ExternalPort ep : getExternalPorts()) {\r\n\tports.remove(ep.getInterfacePort());\r\n}\r\nreturn ports;\r\n'"
   * @generated
   */
  EList<Port> getRelayPorts();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='EList<SPP> spps = new BasicEList<SPP>();\r\nfor (ServiceImplementation spp : getServiceImplementations()) {\r\n\tspps.add(spp.getSpp());\r\n}\r\nreturn spps;\r\n'"
   * @generated
   */
  EList<SPP> getImplementedSPPs();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (ActorClass)getBase();\r\n'"
   * @generated
   */
  ActorClass getActorBase();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return getName();\r\n'"
   * @generated
   */
  String getComponentName();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return new <%org.eclipse.emf.common.util.BasicEList%><AbstractInterfaceItem>(new <%org.eclipse.etrice.core.room.util.RoomHelpers%>().getInterfaceItems(this));\r\n'"
   * @generated
   */
  EList<AbstractInterfaceItem> getAbstractInterfaceItems();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model kind="operation"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return new <%org.eclipse.emf.common.util.BasicEList%><AbstractInterfaceItem>(new <%org.eclipse.etrice.core.room.util.RoomHelpers%>().getAllInterfaceItems(this));\r\n'"
   * @generated
   */
  EList<AbstractInterfaceItem> getAllAbstractInterfaceItems();

} // ActorClass
