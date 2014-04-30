/*
* generated by Xtext
*/
package org.eclipse.etrice.core.common.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.eclipse.etrice.core.common.services.BaseGrammarAccess;

public class BaseParser extends AbstractContentAssistParser {
	
	@Inject
	private BaseGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.eclipse.etrice.core.common.ui.contentassist.antlr.internal.InternalBaseParser createParser() {
		org.eclipse.etrice.core.common.ui.contentassist.antlr.internal.InternalBaseParser result = new org.eclipse.etrice.core.common.ui.contentassist.antlr.internal.InternalBaseParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getAnnotationTypeAccess().getAlternatives_6(), "rule__AnnotationType__Alternatives_6");
					put(grammarAccess.getAnnotationAttributeAccess().getAlternatives(), "rule__AnnotationAttribute__Alternatives");
					put(grammarAccess.getSimpleAnnotationAttributeAccess().getAlternatives_0(), "rule__SimpleAnnotationAttribute__Alternatives_0");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getAlternatives_0(), "rule__EnumAnnotationAttribute__Alternatives_0");
					put(grammarAccess.getTIMEAccess().getAlternatives(), "rule__TIME__Alternatives");
					put(grammarAccess.getLiteralAccess().getAlternatives(), "rule__Literal__Alternatives");
					put(grammarAccess.getBooleanLiteralAccess().getAlternatives_1(), "rule__BooleanLiteral__Alternatives_1");
					put(grammarAccess.getNumberLiteralAccess().getAlternatives(), "rule__NumberLiteral__Alternatives");
					put(grammarAccess.getIntegerAccess().getAlternatives(), "rule__Integer__Alternatives");
					put(grammarAccess.getSignedIntegerAccess().getAlternatives_0(), "rule__SignedInteger__Alternatives_0");
					put(grammarAccess.getRealAccess().getAlternatives(), "rule__Real__Alternatives");
					put(grammarAccess.getDecimalAccess().getAlternatives_0(), "rule__Decimal__Alternatives_0");
					put(grammarAccess.getDotDecimalAccess().getAlternatives_0(), "rule__DotDecimal__Alternatives_0");
					put(grammarAccess.getDecimalDotAccess().getAlternatives_0(), "rule__DecimalDot__Alternatives_0");
					put(grammarAccess.getDecimalExpAccess().getAlternatives_0(), "rule__DecimalExp__Alternatives_0");
					put(grammarAccess.getLiteralTypeAccess().getAlternatives(), "rule__LiteralType__Alternatives");
					put(grammarAccess.getAnnotationAccess().getGroup(), "rule__Annotation__Group__0");
					put(grammarAccess.getAnnotationAccess().getGroup_2(), "rule__Annotation__Group_2__0");
					put(grammarAccess.getAnnotationAccess().getGroup_2_2(), "rule__Annotation__Group_2_2__0");
					put(grammarAccess.getKeyValueAccess().getGroup(), "rule__KeyValue__Group__0");
					put(grammarAccess.getAnnotationTypeAccess().getGroup(), "rule__AnnotationType__Group__0");
					put(grammarAccess.getAnnotationTypeAccess().getGroup_6_1(), "rule__AnnotationType__Group_6_1__0");
					put(grammarAccess.getAnnotationTypeAccess().getGroup_6_1_2(), "rule__AnnotationType__Group_6_1_2__0");
					put(grammarAccess.getSimpleAnnotationAttributeAccess().getGroup(), "rule__SimpleAnnotationAttribute__Group__0");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getGroup(), "rule__EnumAnnotationAttribute__Group__0");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getGroup_6(), "rule__EnumAnnotationAttribute__Group_6__0");
					put(grammarAccess.getDocumentationAccess().getGroup(), "rule__Documentation__Group__0");
					put(grammarAccess.getTIMEAccess().getGroup_0(), "rule__TIME__Group_0__0");
					put(grammarAccess.getTIMEAccess().getGroup_1(), "rule__TIME__Group_1__0");
					put(grammarAccess.getTIMEAccess().getGroup_2(), "rule__TIME__Group_2__0");
					put(grammarAccess.getTIMEAccess().getGroup_3(), "rule__TIME__Group_3__0");
					put(grammarAccess.getLiteralArrayAccess().getGroup(), "rule__LiteralArray__Group__0");
					put(grammarAccess.getLiteralArrayAccess().getGroup_1(), "rule__LiteralArray__Group_1__0");
					put(grammarAccess.getBooleanLiteralAccess().getGroup(), "rule__BooleanLiteral__Group__0");
					put(grammarAccess.getRealLiteralAccess().getGroup(), "rule__RealLiteral__Group__0");
					put(grammarAccess.getIntLiteralAccess().getGroup(), "rule__IntLiteral__Group__0");
					put(grammarAccess.getStringLiteralAccess().getGroup(), "rule__StringLiteral__Group__0");
					put(grammarAccess.getSignedIntegerAccess().getGroup(), "rule__SignedInteger__Group__0");
					put(grammarAccess.getDecimalAccess().getGroup(), "rule__Decimal__Group__0");
					put(grammarAccess.getDotDecimalAccess().getGroup(), "rule__DotDecimal__Group__0");
					put(grammarAccess.getDecimalDotAccess().getGroup(), "rule__DecimalDot__Group__0");
					put(grammarAccess.getDecimalExpAccess().getGroup(), "rule__DecimalExp__Group__0");
					put(grammarAccess.getFQNAccess().getGroup(), "rule__FQN__Group__0");
					put(grammarAccess.getFQNAccess().getGroup_1(), "rule__FQN__Group_1__0");
					put(grammarAccess.getAnnotationAccess().getTypeAssignment_1(), "rule__Annotation__TypeAssignment_1");
					put(grammarAccess.getAnnotationAccess().getAttributesAssignment_2_1(), "rule__Annotation__AttributesAssignment_2_1");
					put(grammarAccess.getAnnotationAccess().getAttributesAssignment_2_2_1(), "rule__Annotation__AttributesAssignment_2_2_1");
					put(grammarAccess.getKeyValueAccess().getKeyAssignment_0(), "rule__KeyValue__KeyAssignment_0");
					put(grammarAccess.getKeyValueAccess().getValueAssignment_2(), "rule__KeyValue__ValueAssignment_2");
					put(grammarAccess.getAnnotationTypeAccess().getNameAssignment_1(), "rule__AnnotationType__NameAssignment_1");
					put(grammarAccess.getAnnotationTypeAccess().getDocuAssignment_2(), "rule__AnnotationType__DocuAssignment_2");
					put(grammarAccess.getAnnotationTypeAccess().getTargetsAssignment_6_0(), "rule__AnnotationType__TargetsAssignment_6_0");
					put(grammarAccess.getAnnotationTypeAccess().getTargetsAssignment_6_1_1(), "rule__AnnotationType__TargetsAssignment_6_1_1");
					put(grammarAccess.getAnnotationTypeAccess().getTargetsAssignment_6_1_2_1(), "rule__AnnotationType__TargetsAssignment_6_1_2_1");
					put(grammarAccess.getAnnotationTypeAccess().getAttributesAssignment_7(), "rule__AnnotationType__AttributesAssignment_7");
					put(grammarAccess.getSimpleAnnotationAttributeAccess().getOptionalAssignment_0_0(), "rule__SimpleAnnotationAttribute__OptionalAssignment_0_0");
					put(grammarAccess.getSimpleAnnotationAttributeAccess().getNameAssignment_2(), "rule__SimpleAnnotationAttribute__NameAssignment_2");
					put(grammarAccess.getSimpleAnnotationAttributeAccess().getTypeAssignment_4(), "rule__SimpleAnnotationAttribute__TypeAssignment_4");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getOptionalAssignment_0_0(), "rule__EnumAnnotationAttribute__OptionalAssignment_0_0");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getNameAssignment_2(), "rule__EnumAnnotationAttribute__NameAssignment_2");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getValuesAssignment_5(), "rule__EnumAnnotationAttribute__ValuesAssignment_5");
					put(grammarAccess.getEnumAnnotationAttributeAccess().getValuesAssignment_6_1(), "rule__EnumAnnotationAttribute__ValuesAssignment_6_1");
					put(grammarAccess.getDocumentationAccess().getLinesAssignment_2(), "rule__Documentation__LinesAssignment_2");
					put(grammarAccess.getLiteralArrayAccess().getLiteralsAssignment_0(), "rule__LiteralArray__LiteralsAssignment_0");
					put(grammarAccess.getLiteralArrayAccess().getLiteralsAssignment_1_1(), "rule__LiteralArray__LiteralsAssignment_1_1");
					put(grammarAccess.getBooleanLiteralAccess().getIsTrueAssignment_1_1(), "rule__BooleanLiteral__IsTrueAssignment_1_1");
					put(grammarAccess.getRealLiteralAccess().getValueAssignment_1(), "rule__RealLiteral__ValueAssignment_1");
					put(grammarAccess.getIntLiteralAccess().getValueAssignment_1(), "rule__IntLiteral__ValueAssignment_1");
					put(grammarAccess.getStringLiteralAccess().getValueAssignment_1(), "rule__StringLiteral__ValueAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.eclipse.etrice.core.common.ui.contentassist.antlr.internal.InternalBaseParser typedParser = (org.eclipse.etrice.core.common.ui.contentassist.antlr.internal.InternalBaseParser) parser;
			typedParser.entryRuleAnnotation();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public BaseGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(BaseGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
