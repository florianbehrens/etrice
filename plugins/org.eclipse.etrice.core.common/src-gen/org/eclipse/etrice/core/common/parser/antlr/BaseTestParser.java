/*
* generated by Xtext
*/
package org.eclipse.etrice.core.common.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.etrice.core.common.services.BaseTestGrammarAccess;

public class BaseTestParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private BaseTestGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.etrice.core.common.parser.antlr.internal.InternalBaseTestParser createParser(XtextTokenStream stream) {
		return new org.eclipse.etrice.core.common.parser.antlr.internal.InternalBaseTestParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "BaseModel";
	}
	
	public BaseTestGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(BaseTestGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
