/*
 * generated by Xtext
 */
package org.eclipse.etrice.core.common.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class BaseTestAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/etrice/core/common/parser/antlr/internal/InternalBaseTest.tokens");
	}
}
