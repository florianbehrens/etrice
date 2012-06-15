package org.eclipse.etrice.core.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.etrice.core.config.ActorClassConfig;
import org.eclipse.etrice.core.config.ActorInstanceConfig;
import org.eclipse.etrice.core.config.AttrClassConfig;
import org.eclipse.etrice.core.config.AttrInstanceConfig;
import org.eclipse.etrice.core.config.BooleanLiteral;
import org.eclipse.etrice.core.config.ConfigModel;
import org.eclipse.etrice.core.config.ConfigPackage;
import org.eclipse.etrice.core.config.Import;
import org.eclipse.etrice.core.config.IntLiteral;
import org.eclipse.etrice.core.config.RealLiteral;
import org.eclipse.etrice.core.config.RefPath;
import org.eclipse.etrice.core.config.StringLiteral;
import org.eclipse.etrice.core.services.ConfigGrammarAccess;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("restriction")
public class AbstractConfigSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected ConfigGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
	}
	
	@Override
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == ConfigPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case ConfigPackage.ACTOR_CLASS_CONFIG:
				if(context == grammarAccess.getActorClassConfigRule() ||
				   context == grammarAccess.getConfigElementRule()) {
					sequence_ActorClassConfig(context, (ActorClassConfig) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.ACTOR_INSTANCE_CONFIG:
				if(context == grammarAccess.getActorInstanceConfigRule() ||
				   context == grammarAccess.getConfigElementRule()) {
					sequence_ActorInstanceConfig(context, (ActorInstanceConfig) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.ATTR_CLASS_CONFIG:
				if(context == grammarAccess.getAttrClassConfigRule() ||
				   context == grammarAccess.getAttrConfigRule()) {
					sequence_AttrClassConfig(context, (AttrClassConfig) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.ATTR_INSTANCE_CONFIG:
				if(context == grammarAccess.getAttrConfigRule() ||
				   context == grammarAccess.getAttrInstanceConfigRule()) {
					sequence_AttrInstanceConfig(context, (AttrInstanceConfig) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.BOOLEAN_LITERAL:
				if(context == grammarAccess.getBooleanLiteralRule() ||
				   context == grammarAccess.getLiteralRule()) {
					sequence_BooleanLiteral(context, (BooleanLiteral) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.CONFIG_MODEL:
				if(context == grammarAccess.getConfigModelRule()) {
					sequence_ConfigModel(context, (ConfigModel) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.IMPORT:
				if(context == grammarAccess.getImportRule()) {
					sequence_Import(context, (Import) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.INT_LITERAL:
				if(context == grammarAccess.getIntLiteralRule() ||
				   context == grammarAccess.getLiteralRule() ||
				   context == grammarAccess.getNumberLiteralRule()) {
					sequence_IntLiteral(context, (IntLiteral) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.REAL_LITERAL:
				if(context == grammarAccess.getLiteralRule() ||
				   context == grammarAccess.getNumberLiteralRule() ||
				   context == grammarAccess.getRealLiteralRule()) {
					sequence_RealLiteral(context, (RealLiteral) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.REF_PATH:
				if(context == grammarAccess.getRefPathRule()) {
					sequence_RefPath(context, (RefPath) semanticObject); 
					return; 
				}
				else break;
			case ConfigPackage.STRING_LITERAL:
				if(context == grammarAccess.getLiteralRule() ||
				   context == grammarAccess.getStringLiteralRule()) {
					sequence_StringLiteral(context, (StringLiteral) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (actor=[ActorClass|FQN] attributes+=AttrClassConfig*)
	 */
	protected void sequence_ActorClassConfig(EObject context, ActorClassConfig semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (root=[SubSystemClass|FQN] path=RefPath attributes+=AttrInstanceConfig*)
	 */
	protected void sequence_ActorInstanceConfig(EObject context, ActorInstanceConfig semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (attribute=[Attribute|ID] value=Literal? (min=NumberLiteral? max=NumberLiteral?)?)
	 */
	protected void sequence_AttrClassConfig(EObject context, AttrClassConfig semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (attribute=[Attribute|ID] value=Literal?)
	 */
	protected void sequence_AttrInstanceConfig(EObject context, AttrInstanceConfig semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (isTrue?='true'?)
	 */
	protected void sequence_BooleanLiteral(EObject context, BooleanLiteral semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (imports+=Import* configElements+=ConfigElement*)
	 */
	protected void sequence_ConfigModel(EObject context, ConfigModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (importedNamespace=ImportedFQN? importURI=STRING)
	 */
	protected void sequence_Import(EObject context, Import semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=Integer
	 */
	protected void sequence_IntLiteral(EObject context, IntLiteral semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.INT_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.INT_LITERAL__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIntLiteralAccess().getValueIntegerParserRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     value=Real
	 */
	protected void sequence_RealLiteral(EObject context, RealLiteral semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.REAL_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.REAL_LITERAL__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRealLiteralAccess().getValueRealParserRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (refs+=ID refs+=ID*)
	 */
	protected void sequence_RefPath(EObject context, RefPath semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_StringLiteral(EObject context, StringLiteral semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.STRING_LITERAL__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.STRING_LITERAL__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringLiteralAccess().getValueSTRINGTerminalRuleCall_1_0(), semanticObject.getValue());
		feeder.finish();
	}
}
