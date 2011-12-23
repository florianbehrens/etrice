package org.eclipse.etrice.generator.java.gen;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.etrice.core.room.DataClass;
import org.eclipse.etrice.core.room.FreeType;
import org.eclipse.etrice.core.room.Message;
import org.eclipse.etrice.core.room.PrimitiveType;
import org.eclipse.etrice.core.room.RoomClass;
import org.eclipse.etrice.core.room.Type;
import org.eclipse.etrice.core.room.TypedID;
import org.eclipse.etrice.generator.etricegen.ExpandedActorClass;
import org.eclipse.etrice.generator.etricegen.TransitionChain;
import org.eclipse.etrice.generator.generic.ILanguageExtension;
import org.eclipse.etrice.generator.generic.LanguageGenerator;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
@Singleton
public class JavaExtensions implements ILanguageExtension {
  @Inject
  private LanguageGenerator languageGen;
  
  public String toType(final PrimitiveType prim) {
    String _switchResult = null;
    String _string = prim.toString();
    final String __valOfSwitchOver = _string;
    boolean matched = false;
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint8")) {
        matched=true;
        _switchResult = "undefined_type";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint16")) {
        matched=true;
        _switchResult = "undefined_type";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint32")) {
        matched=true;
        _switchResult = "undefined_type";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int8")) {
        matched=true;
        _switchResult = "byte";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int16")) {
        matched=true;
        _switchResult = "short";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int32")) {
        matched=true;
        _switchResult = "int";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"float32")) {
        matched=true;
        _switchResult = "float";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"float64")) {
        matched=true;
        _switchResult = "double";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"string")) {
        matched=true;
        _switchResult = "String";
      }
    }
    if (!matched) {
      String _string_1 = prim.toString();
      _switchResult = _string_1;
    }
    return _switchResult;
  }
  
  public String typeName(final Type type) {
    String _xifexpression = null;
    DataClass _type = type.getType();
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_type, null);
    if (_operator_notEquals) {
      DataClass _type_1 = type.getType();
      String _name = _type_1.getName();
      return _name;
    } else {
      PrimitiveType _prim = type.getPrim();
      String _type_2 = this.toType(_prim);
      _xifexpression = _type_2;
    }
    return _xifexpression;
  }
  
  public String freeTypeName(final FreeType type) {
    String _xifexpression = null;
    String _type = type.getType();
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_type, null);
    if (_operator_notEquals) {
      String _type_1 = type.getType();
      return _type_1;
    } else {
      PrimitiveType _prim = type.getPrim();
      String _type_2 = this.toType(_prim);
      _xifexpression = _type_2;
    }
    return _xifexpression;
  }
  
  public String defaultValue(final PrimitiveType prim) {
    String _switchResult = null;
    String _string = prim.toString();
    final String __valOfSwitchOver = _string;
    boolean matched = false;
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint8")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint16")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"uint32")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int8")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int16")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"int32")) {
        matched=true;
        _switchResult = "0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"float32")) {
        matched=true;
        _switchResult = "0.0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"float64")) {
        matched=true;
        _switchResult = "0.0";
      }
    }
    if (!matched) {
      if (ObjectExtensions.operator_equals(__valOfSwitchOver,"string")) {
        matched=true;
        _switchResult = "\"\"";
      }
    }
    if (!matched) {
      String _string_1 = prim.toString();
      _switchResult = _string_1;
    }
    return _switchResult;
  }
  
  public String defaultValue(final TypedID a) {
    Type _type = a.getType();
    DataClass _type_1 = _type.getType();
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_type_1, null);
    if (_operator_notEquals) {
      Type _type_2 = a.getType();
      DataClass _type_3 = _type_2.getType();
      String _name = _type_3.getName();
      String _operator_plus = StringExtensions.operator_plus("new ", _name);
      String _operator_plus_1 = StringExtensions.operator_plus(_operator_plus, "()");
      return _operator_plus_1;
    } else {
      Type _type_4 = a.getType();
      PrimitiveType _prim = _type_4.getPrim();
      String _defaultValue = this.defaultValue(_prim);
      return _defaultValue;
    }
  }
  
  public String getTypedDataDefinition(final Message m) {
    String _typedData = this.languageGen.getTypedData(m);
    return _typedData;
  }
  
  public String getJavaFileName(final RoomClass rc) {
    String _name = rc.getName();
    String _operator_plus = StringExtensions.operator_plus(_name, ".java");
    return _operator_plus;
  }
  
  public String getExecuteChainCode(final ExpandedActorClass ac, final TransitionChain tc) {
    String _executeChain = this.languageGen.getExecuteChain(ac, tc);
    return _executeChain;
  }
}