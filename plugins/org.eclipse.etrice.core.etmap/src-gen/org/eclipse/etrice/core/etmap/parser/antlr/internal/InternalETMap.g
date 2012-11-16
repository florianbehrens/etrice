/*
* generated by Xtext
*/
grammar InternalETMap;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.etrice.core.etmap.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.etrice.core.etmap.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.etrice.core.etmap.services.ETMapGrammarAccess;

}

@parser::members {

 	private ETMapGrammarAccess grammarAccess;
 	
    public InternalETMapParser(TokenStream input, ETMapGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "MappingModel";	
   	}
   	
   	@Override
   	protected ETMapGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleMappingModel
entryRuleMappingModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMappingModelRule()); }
	 iv_ruleMappingModel=ruleMappingModel 
	 { $current=$iv_ruleMappingModel.current; } 
	 EOF 
;

// Rule MappingModel
ruleMappingModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='MappingModel' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getMappingModelAccess().getMappingModelKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMappingModelAccess().getNameFQNParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleFQN		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMappingModelRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"FQN");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='{' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMappingModelAccess().getLeftCurlyBracketKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMappingModelAccess().getImportsImportParserRuleCall_3_0()); 
	    }
		lv_imports_3_0=ruleImport		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMappingModelRule());
	        }
       		add(
       			$current, 
       			"imports",
        		lv_imports_3_0, 
        		"Import");
	        afterParserOrEnumRuleCall();
	    }

)
)*(
(
		{ 
	        newCompositeNode(grammarAccess.getMappingModelAccess().getMappingsMappingParserRuleCall_4_0()); 
	    }
		lv_mappings_4_0=ruleMapping		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMappingModelRule());
	        }
       		add(
       			$current, 
       			"mappings",
        		lv_mappings_4_0, 
        		"Mapping");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_5='}' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getMappingModelAccess().getRightCurlyBracketKeyword_5());
    }
)
;





// Entry rule entryRuleMapping
entryRuleMapping returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMappingRule()); }
	 iv_ruleMapping=ruleMapping 
	 { $current=$iv_ruleMapping.current; } 
	 EOF 
;

// Rule Mapping
ruleMapping returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Mapping' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getMappingAccess().getMappingKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMappingRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getMappingAccess().getLogicalSysLogicalSystemCrossReference_1_0()); 
	    }
		ruleFQN		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='->' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMappingAccess().getHyphenMinusGreaterThanSignKeyword_2());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMappingRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getMappingAccess().getPhysicalSysPhysicalSystemCrossReference_3_0()); 
	    }
		ruleFQN		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_4='{' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getMappingAccess().getLeftCurlyBracketKeyword_4());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getMappingAccess().getSubsysMappingsSubSystemMappingParserRuleCall_5_0()); 
	    }
		lv_subsysMappings_5_0=ruleSubSystemMapping		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMappingRule());
	        }
       		add(
       			$current, 
       			"subsysMappings",
        		lv_subsysMappings_5_0, 
        		"SubSystemMapping");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_6='}' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getMappingAccess().getRightCurlyBracketKeyword_6());
    }
)
;





// Entry rule entryRuleSubSystemMapping
entryRuleSubSystemMapping returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getSubSystemMappingRule()); }
	 iv_ruleSubSystemMapping=ruleSubSystemMapping 
	 { $current=$iv_ruleSubSystemMapping.current; } 
	 EOF 
;

// Rule SubSystemMapping
ruleSubSystemMapping returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='SubSystemMapping' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getSubSystemMappingAccess().getSubSystemMappingKeyword_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSubSystemMappingRule());
	        }
        }
	otherlv_1=RULE_ID
	{
		newLeafNode(otherlv_1, grammarAccess.getSubSystemMappingAccess().getLogicalSubSysSubSystemRefCrossReference_1_0()); 
	}

)
)	otherlv_2='->' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getSubSystemMappingAccess().getHyphenMinusGreaterThanSignKeyword_2());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getSubSystemMappingRule());
	        }
        }
	otherlv_3=RULE_ID
	{
		newLeafNode(otherlv_3, grammarAccess.getSubSystemMappingAccess().getNodeNodeRefCrossReference_3_0()); 
	}

)
)(	otherlv_4='{' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getSubSystemMappingAccess().getLeftCurlyBracketKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getSubSystemMappingAccess().getActorInstanceMappingsActorInstanceMappingParserRuleCall_4_1_0()); 
	    }
		lv_actorInstanceMappings_5_0=ruleActorInstanceMapping		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getSubSystemMappingRule());
	        }
       		add(
       			$current, 
       			"actorInstanceMappings",
        		lv_actorInstanceMappings_5_0, 
        		"ActorInstanceMapping");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_6='}' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getSubSystemMappingAccess().getRightCurlyBracketKeyword_4_2());
    }
)?)
;





// Entry rule entryRuleActorInstanceMapping
entryRuleActorInstanceMapping returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getActorInstanceMappingRule()); }
	 iv_ruleActorInstanceMapping=ruleActorInstanceMapping 
	 { $current=$iv_ruleActorInstanceMapping.current; } 
	 EOF 
;

// Rule ActorInstanceMapping
ruleActorInstanceMapping returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='ActorInstanceMapping' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getActorInstanceMappingAccess().getActorInstanceMappingKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getActorInstanceMappingAccess().getPathRefPathParserRuleCall_1_0()); 
	    }
		lv_path_1_0=ruleRefPath		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getActorInstanceMappingRule());
	        }
       		set(
       			$current, 
       			"path",
        		lv_path_1_0, 
        		"RefPath");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='->' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getActorInstanceMappingAccess().getHyphenMinusGreaterThanSignKeyword_2());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getActorInstanceMappingRule());
	        }
        }
	otherlv_3=RULE_ID
	{
		newLeafNode(otherlv_3, grammarAccess.getActorInstanceMappingAccess().getThreadThreadCrossReference_3_0()); 
	}

)
)(	otherlv_4='{' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getActorInstanceMappingAccess().getLeftCurlyBracketKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getActorInstanceMappingAccess().getActorInstanceMappingsActorInstanceMappingParserRuleCall_4_1_0()); 
	    }
		lv_actorInstanceMappings_5_0=ruleActorInstanceMapping		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getActorInstanceMappingRule());
	        }
       		add(
       			$current, 
       			"actorInstanceMappings",
        		lv_actorInstanceMappings_5_0, 
        		"ActorInstanceMapping");
	        afterParserOrEnumRuleCall();
	    }

)
)*	otherlv_6='}' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getActorInstanceMappingAccess().getRightCurlyBracketKeyword_4_2());
    }
)?)
;





// Entry rule entryRuleRefPath
entryRuleRefPath returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRefPathRule()); }
	 iv_ruleRefPath=ruleRefPath 
	 { $current=$iv_ruleRefPath.current; } 
	 EOF 
;

// Rule RefPath
ruleRefPath returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		lv_refs_0_0=RULE_ID
		{
			newLeafNode(lv_refs_0_0, grammarAccess.getRefPathAccess().getRefsIDTerminalRuleCall_0_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRefPathRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"refs",
        		lv_refs_0_0, 
        		"ID");
	    }

)
)(	otherlv_1='/' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getRefPathAccess().getSolidusKeyword_1_0());
    }
(
(
		lv_refs_2_0=RULE_ID
		{
			newLeafNode(lv_refs_2_0, grammarAccess.getRefPathAccess().getRefsIDTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRefPathRule());
	        }
       		addWithLastConsumed(
       			$current, 
       			"refs",
        		lv_refs_2_0, 
        		"ID");
	    }

)
))*)
;





// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	 iv_ruleImport=ruleImport 
	 { $current=$iv_ruleImport.current; } 
	 EOF 
;

// Rule Import
ruleImport returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='import' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
    }
(((
(
		{ 
	        newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceImportedFQNParserRuleCall_1_0_0_0()); 
	    }
		lv_importedNamespace_1_0=ruleImportedFQN		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportRule());
	        }
       		set(
       			$current, 
       			"importedNamespace",
        		lv_importedNamespace_1_0, 
        		"ImportedFQN");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2='from' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getFromKeyword_1_0_1());
    }
)
    |	otherlv_3='model' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getImportAccess().getModelKeyword_1_1());
    }
)(
(
		lv_importURI_4_0=RULE_STRING
		{
			newLeafNode(lv_importURI_4_0, grammarAccess.getImportAccess().getImportURISTRINGTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getImportRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"importURI",
        		lv_importURI_4_0, 
        		"STRING");
	    }

)
))
;





// Entry rule entryRuleImportedFQN
entryRuleImportedFQN returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportedFQNRule()); } 
	 iv_ruleImportedFQN=ruleImportedFQN 
	 { $current=$iv_ruleImportedFQN.current.getText(); }  
	 EOF 
;

// Rule ImportedFQN
ruleImportedFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getImportedFQNAccess().getFQNParserRuleCall_0()); 
    }
    this_FQN_0=ruleFQN    {
		$current.merge(this_FQN_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }
(
	kw='.*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getImportedFQNAccess().getFullStopAsteriskKeyword_1()); 
    }
)?)
    ;





// Entry rule entryRuleFQN
entryRuleFQN returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getFQNRule()); } 
	 iv_ruleFQN=ruleFQN 
	 { $current=$iv_ruleFQN.current.getText(); }  
	 EOF 
;

// Rule FQN
ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0()); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getFQNAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


