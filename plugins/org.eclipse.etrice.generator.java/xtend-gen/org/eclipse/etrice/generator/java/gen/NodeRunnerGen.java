/**
 * Copyright (c) 2011 protos software gmbh (http://www.protos.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * CONTRIBUTORS:
 * 		Henrik Rentz-Reichert (initial contribution)
 */
package org.eclipse.etrice.generator.java.gen;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.etrice.core.etmap.util.ETMapUtil;
import org.eclipse.etrice.core.etphys.eTPhys.NodeRef;
import org.eclipse.etrice.core.genmodel.etricegen.Root;
import org.eclipse.etrice.core.genmodel.etricegen.StructureInstance;
import org.eclipse.etrice.core.genmodel.etricegen.SubSystemInstance;
import org.eclipse.etrice.core.genmodel.etricegen.SystemInstance;
import org.eclipse.etrice.core.room.SubSystemClass;
import org.eclipse.etrice.generator.fsm.base.FileSystemHelpers;
import org.eclipse.etrice.generator.fsm.base.IGeneratorFileIo;
import org.eclipse.etrice.generator.generic.RoomExtensions;
import org.eclipse.etrice.generator.java.gen.JavaExtensions;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

@Singleton
@SuppressWarnings("all")
public class NodeRunnerGen {
  @Inject
  @Extension
  private RoomExtensions roomExt;
  
  @Inject
  @Extension
  private JavaExtensions _javaExtensions;
  
  @Inject
  @Extension
  private FileSystemHelpers _fileSystemHelpers;
  
  @Inject
  private IGeneratorFileIo fileIO;
  
  public void doGenerate(final Root root) {
    Collection<NodeRef> _nodeRefs = ETMapUtil.getNodeRefs();
    for (final NodeRef nr : _nodeRefs) {
      List<String> _subSystemInstancePaths = ETMapUtil.getSubSystemInstancePaths(nr);
      for (final String instpath : _subSystemInstancePaths) {
        {
          StructureInstance _instance = root.getInstance(instpath);
          final SubSystemInstance ssi = ((SubSystemInstance) _instance);
          boolean _and = false;
          boolean _notEquals = (!Objects.equal(ssi, null));
          if (!_notEquals) {
            _and = false;
          } else {
            SubSystemClass _subSystemClass = ssi.getSubSystemClass();
            boolean _isValidGenerationLocation = this._fileSystemHelpers.isValidGenerationLocation(_subSystemClass);
            _and = _isValidGenerationLocation;
          }
          if (_and) {
            String _javaClassName = this._javaExtensions.getJavaClassName(nr, ssi);
            final String file = (_javaClassName + "Runner.java");
            SubSystemClass _subSystemClass_1 = ssi.getSubSystemClass();
            String _generationTargetPath = this.roomExt.getGenerationTargetPath(_subSystemClass_1);
            SubSystemClass _subSystemClass_2 = ssi.getSubSystemClass();
            String _path = this.roomExt.getPath(_subSystemClass_2);
            final String filepath = (_generationTargetPath + _path);
            SubSystemClass _subSystemClass_3 = ssi.getSubSystemClass();
            String _generationInfoPath = this.roomExt.getGenerationInfoPath(_subSystemClass_3);
            SubSystemClass _subSystemClass_4 = ssi.getSubSystemClass();
            String _path_1 = this.roomExt.getPath(_subSystemClass_4);
            final String infopath = (_generationInfoPath + _path_1);
            CharSequence _generate = this.generate(root, ssi);
            this.fileIO.generateFile("generating SubSystemRunner implementation", filepath, infopath, file, _generate);
          }
        }
      }
    }
  }
  
  public CharSequence generate(final Root root, final SubSystemInstance ssc) {
    CharSequence _xblockexpression = null;
    {
      final SubSystemClass cc = ssc.getSubSystemClass();
      final NodeRef nr = ETMapUtil.getNodeRef(ssc);
      final String clsname = this._javaExtensions.getJavaClassName(nr, ssc);
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
      _builder.append("* this class contains the main function running component ");
      String _name = ssc.getName();
      _builder.append(_name, " ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("* it instantiates ");
      String _name_1 = ssc.getName();
      _builder.append(_name_1, " ");
      _builder.append(" and starts and ends the lifecycle");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.newLine();
      _builder.append("package ");
      String _package = this.roomExt.getPackage(cc);
      _builder.append(_package, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import org.eclipse.etrice.runtime.java.modelbase.RTSystem;");
      _builder.newLine();
      _builder.append("import org.eclipse.etrice.runtime.java.modelbase.SubSystemRunnerBase;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class ");
      _builder.append((clsname + "Runner"), "");
      _builder.append(" extends SubSystemRunnerBase {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* main function");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("* creates the main component and starts and stops the lifecycle");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public static void main(String[] args) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("// instantiate the main component");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("RTSystem sys = ");
      {
        EObject _eContainer = ssc.eContainer();
        if ((_eContainer instanceof SystemInstance)) {
          _builder.append("new RTSystem(\"");
          EObject _eContainer_1 = ssc.eContainer();
          String _name_2 = ((SystemInstance) _eContainer_1).getName();
          _builder.append(_name_2, "\t\t");
          _builder.append("\")");
        } else {
          _builder.append("null");
        }
      }
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append(clsname, "\t\t");
      _builder.append(" main_component = new ");
      _builder.append(clsname, "\t\t");
      _builder.append("(sys, \"");
      String _name_3 = ssc.getName();
      _builder.append(_name_3, "\t\t");
      _builder.append("\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("run(main_component, args);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("};");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
