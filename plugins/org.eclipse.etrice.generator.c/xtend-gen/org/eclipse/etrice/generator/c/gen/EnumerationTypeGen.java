/**
 * Copyright (c) 2013 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 */
package org.eclipse.etrice.generator.c.gen;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.etrice.core.genmodel.etricegen.Root;
import org.eclipse.etrice.core.room.EnumLiteral;
import org.eclipse.etrice.core.room.EnumerationType;
import org.eclipse.etrice.core.room.util.RoomHelpers;
import org.eclipse.etrice.generator.base.IGeneratorFileIo;
import org.eclipse.etrice.generator.c.gen.CExtensions;
import org.eclipse.etrice.generator.generic.RoomExtensions;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author Henrik Rentz-Reichert
 */
@SuppressWarnings("all")
public class EnumerationTypeGen {
  @Inject
  private IGeneratorFileIo fileIO;
  
  @Inject
  @Extension
  private CExtensions _cExtensions;
  
  @Inject
  @Extension
  private RoomExtensions _roomExtensions;
  
  public void doGenerate(final Root root) {
    EList<EnumerationType> _usedEnumClasses = root.getUsedEnumClasses();
    for (final EnumerationType et : _usedEnumClasses) {
      {
        String _generationTargetPath = this._roomExtensions.getGenerationTargetPath(et);
        String _path = this._roomExtensions.getPath(et);
        final String path = (_generationTargetPath + _path);
        String _generationInfoPath = this._roomExtensions.getGenerationInfoPath(et);
        String _path_1 = this._roomExtensions.getPath(et);
        final String infopath = (_generationInfoPath + _path_1);
        String file = this._cExtensions.getCHeaderFileName(et);
        CharSequence _generateHeaderFile = this.generateHeaderFile(root, et);
        this.fileIO.generateFile("generating Enumeration header", path, infopath, file, _generateHeaderFile);
      }
    }
  }
  
  public CharSequence generateHeaderFile(final Root root, final EnumerationType et) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author generated by eTrice");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Header File of Enumeration ");
    String _name = et.getName();
    _builder.append(_name, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.newLine();
    CharSequence _generateIncludeGuardBegin = this._cExtensions.generateIncludeGuardBegin(et);
    _builder.append(_generateIncludeGuardBegin, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("#include \"etDatatypes.h\"");
    _builder.newLine();
    _builder.newLine();
    {
      EList<EnumLiteral> _literals = et.getLiterals();
      for(final EnumLiteral lit : _literals) {
        _builder.append("#define ");
        String _name_1 = et.getName();
        _builder.append(_name_1, "");
        _builder.append("_");
        String _name_2 = lit.getName();
        _builder.append(_name_2, "");
        _builder.append(" ");
        String _castedValue = RoomHelpers.getCastedValue(lit);
        _builder.append(_castedValue, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    CharSequence _generateIncludeGuardEnd = this._cExtensions.generateIncludeGuardEnd(et);
    _builder.append(_generateIncludeGuardEnd, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
