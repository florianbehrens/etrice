package org.eclipse.etrice.generator.cpp.gen;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.etrice.core.room.Attribute;
import org.eclipse.etrice.core.room.ComplexType;
import org.eclipse.etrice.core.room.DataType;
import org.eclipse.etrice.core.room.RefableType;
import org.eclipse.etrice.generator.base.IDataConfiguration;
import org.eclipse.etrice.generator.generic.ILanguageExtension;
import org.eclipse.etrice.generator.generic.ProcedureHelpers;
import org.eclipse.etrice.generator.generic.RoomExtensions;
import org.eclipse.etrice.generator.generic.TypeHelpers;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@Singleton
@SuppressWarnings("all")
public class Initialization {
  @Inject
  private TypeHelpers _typeHelpers;
  
  @Inject
  private RoomExtensions _roomExtensions;
  
  @Inject
  private ILanguageExtension languageExt;
  
  @Inject
  private IDataConfiguration dataConfigExt;
  
  @Inject
  private ProcedureHelpers procedureHelpers;
  
  public CharSequence attributeInitialization(final List<Attribute> attribs, final boolean useClassDefaultsOnly) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// initialize attributes");
    _builder.newLine();
    {
      for(final Attribute a : attribs) {
        RefableType _refType = a.getRefType();
        DataType aType = _refType.getType();
        _builder.newLineIfNotEmpty();
        String value = a.getDefaultValueLiteral();
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals = (!Objects.equal(value, null));
          if (_notEquals) {
            {
              boolean _or = false;
              int _size = a.getSize();
              boolean _equals = (_size == 0);
              if (_equals) {
                _or = true;
              } else {
                boolean _isCharacterType = this._typeHelpers.isCharacterType(aType);
                _or = (_equals || _isCharacterType);
              }
              if (_or) {
              } else {
                boolean _startsWith = value.startsWith("{");
                if (_startsWith) {
                  String _name = a.getName();
                  String _replace = value.replace("{", "");
                  String _replace_1 = _replace.replace("}", "");
                  String[] _split = _replace_1.split(",");
                  CharSequence _initializeArrayWithValues = this.initializeArrayWithValues(_name, _split);
                  _builder.append(_initializeArrayWithValues, "");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("for (int i=0;i<");
                  int _size_1 = a.getSize();
                  _builder.append(_size_1, "");
                  _builder.append(";i++){");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  String _name_1 = a.getName();
                  _builder.append(_name_1, "	");
                  _builder.append("[i] = ");
                  _builder.append(value, "	");
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                  _builder.append("}");
                  _builder.newLine();
                }
              }
            }
          } else {
            boolean _or_1 = false;
            boolean _or_2 = false;
            if ((aType instanceof ComplexType)) {
              _or_2 = true;
            } else {
              int _size_2 = a.getSize();
              boolean _greaterThan = (_size_2 > 1);
              _or_2 = ((aType instanceof ComplexType) || _greaterThan);
            }
            if (_or_2) {
              _or_1 = true;
            } else {
              boolean _not = (!useClassDefaultsOnly);
              _or_1 = (_or_2 || _not);
            }
            if (_or_1) {
              {
                int _size_3 = a.getSize();
                boolean _equals_1 = (_size_3 == 0);
                if (_equals_1) {
                } else {
                  {
                    boolean _not_1 = (!useClassDefaultsOnly);
                    if (_not_1) {
                      _builder.append("for (int i=0;i<");
                      int _size_4 = a.getSize();
                      _builder.append(_size_4, "");
                      _builder.append(";i++){");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      String _name_2 = a.getName();
                      _builder.append(_name_2, "	");
                      _builder.append("[i] = ");
                      {
                        RefableType _refType_1 = a.getRefType();
                        boolean _isRef = _refType_1.isRef();
                        if (_isRef) {
                          String _nullPointer = this.languageExt.nullPointer();
                          _builder.append(_nullPointer, "	");
                        } else {
                          String _defaultValue = this.languageExt.defaultValue(aType);
                          _builder.append(_defaultValue, "	");
                        }
                      }
                      _builder.append(";");
                      _builder.newLineIfNotEmpty();
                      _builder.append("}");
                      _builder.newLine();
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence initializeArrayWithValues(final String varName, final String[] values) {
    StringConcatenation _builder = new StringConcatenation();
    final Function1<String,String> _function = new Function1<String,String>() {
        public String apply(final String v) {
          String _plus = (varName + "[");
          int _indexOf = ((List<String>)Conversions.doWrapArray(values)).indexOf(v);
          String _plus_1 = (_plus + Integer.valueOf(_indexOf));
          String _plus_2 = (_plus_1 + "] = ");
          String _plus_3 = (_plus_2 + v);
          String _plus_4 = (_plus_3 + ";");
          return _plus_4;
        }
      };
    List<String> _map = ListExtensions.<String, String>map(((List<String>)Conversions.doWrapArray(values)), _function);
    String _join = IterableExtensions.join(_map, "\r\n");
    _builder.append(_join, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence attributeInitialization(final Attribute a, final boolean useClassDefaultsOnly) {
    CharSequence _xblockexpression = null;
    {
      RefableType _refType = a.getRefType();
      DataType aType = _refType.getType();
      String value = a.getDefaultValueLiteral();
      CharSequence _xifexpression = null;
      boolean _notEquals = (!Objects.equal(value, null));
      if (_notEquals) {
        CharSequence _xifexpression_1 = null;
        boolean _or = false;
        int _size = a.getSize();
        boolean _equals = (_size == 0);
        if (_equals) {
          _or = true;
        } else {
          boolean _isCharacterType = this._typeHelpers.isCharacterType(aType);
          _or = (_equals || _isCharacterType);
        }
        if (_or) {
          CharSequence _xifexpression_2 = null;
          RefableType _refType_1 = a.getRefType();
          boolean _isRef = _refType_1.isRef();
          if (_isRef) {
            StringConcatenation _builder = new StringConcatenation();
            String _name = a.getName();
            _builder.append(_name, "");
            _builder.append("(new ");
            String _name_1 = aType.getName();
            _builder.append(_name_1, "");
            _builder.append("(");
            _builder.append(value, "");
            _builder.append("))");
            _xifexpression_2 = _builder;
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            String _name_2 = a.getName();
            _builder_1.append(_name_2, "");
            _builder_1.append("(");
            _builder_1.append(value, "");
            _builder_1.append(")");
            _xifexpression_2 = _builder_1;
          }
          _xifexpression_1 = _xifexpression_2;
        } else {
          CharSequence _xifexpression_3 = null;
          boolean _startsWith = value.startsWith("{");
          if (_startsWith) {
            StringConcatenation _builder_2 = new StringConcatenation();
            String _name_3 = a.getName();
            _builder_2.append(_name_3, "");
            _builder_2.append("()");
            _xifexpression_3 = _builder_2;
          } else {
            StringConcatenation _builder_3 = new StringConcatenation();
            String _name_4 = a.getName();
            _builder_3.append(_name_4, "");
            _builder_3.append("()");
            _xifexpression_3 = _builder_3;
          }
          _xifexpression_1 = _xifexpression_3;
        }
        _xifexpression = _xifexpression_1;
      } else {
        CharSequence _xifexpression_4 = null;
        boolean _or_1 = false;
        boolean _or_2 = false;
        if ((aType instanceof ComplexType)) {
          _or_2 = true;
        } else {
          int _size_1 = a.getSize();
          boolean _greaterThan = (_size_1 > 1);
          _or_2 = ((aType instanceof ComplexType) || _greaterThan);
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          boolean _not = (!useClassDefaultsOnly);
          _or_1 = (_or_2 || _not);
        }
        if (_or_1) {
          CharSequence _xifexpression_5 = null;
          int _size_2 = a.getSize();
          boolean _equals_1 = (_size_2 == 0);
          if (_equals_1) {
            CharSequence _xifexpression_6 = null;
            RefableType _refType_2 = a.getRefType();
            boolean _isRef_1 = _refType_2.isRef();
            if (_isRef_1) {
              StringConcatenation _builder_4 = new StringConcatenation();
              String _name_5 = a.getName();
              _builder_4.append(_name_5, "");
              _builder_4.append("(");
              String _nullPointer = this.languageExt.nullPointer();
              _builder_4.append(_nullPointer, "");
              _builder_4.append(")");
              _xifexpression_6 = _builder_4;
            } else {
              StringConcatenation _builder_5 = new StringConcatenation();
              String _name_6 = a.getName();
              _builder_5.append(_name_6, "");
              _builder_5.append("(");
              String _defaultValue = this.languageExt.defaultValue(aType);
              _builder_5.append(_defaultValue, "");
              _builder_5.append(")");
              _xifexpression_6 = _builder_5;
            }
            _xifexpression_5 = _xifexpression_6;
          } else {
            StringConcatenation _builder_6 = new StringConcatenation();
            String _name_7 = a.getName();
            _builder_6.append(_name_7, "");
            _builder_6.append("()");
            _xifexpression_5 = _builder_6;
          }
          _xifexpression_4 = _xifexpression_5;
        }
        _xifexpression = _xifexpression_4;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
}