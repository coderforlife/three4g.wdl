package net.blimster.gwt.threejs.wdl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.HashSet;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.ArrayType;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Constructor;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.ConstructorSection;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Datatype;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Method;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.MethodSection;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.ObjectWrapper;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Parameter;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Property;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.PropertySection;
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Type;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ThreeJsWrapperDescriptionLanguageGenerator implements IGenerator {
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<ObjectWrapper> _filter = Iterables.<ObjectWrapper>filter(_iterable, ObjectWrapper.class);
    for (final ObjectWrapper objectWrapper : _filter) {
      String _pck = objectWrapper.getPck();
      String _string = _pck.toString();
      String _replace = _string.replace(".", "/");
      String _plus = (_replace + "/");
      String _name = objectWrapper.getName();
      String _plus_1 = (_plus + _name);
      String _plus_2 = (_plus_1 + ".java");
      CharSequence _class = this.toClass(objectWrapper);
      fsa.generateFile(_plus_2, _class);
    }
  }
  
  public CharSequence toClass(final ObjectWrapper objectWrapper) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This file is part of three4g.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* three4g is free software: you can redistribute it and/or modify");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* it under the terms of the GNU Lesse General Public License as ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* published by the Free Software Foundation, either version 3 of ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* the License, or (at your option) any later version.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* three4g is distributed in the hope that it will be useful,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* but WITHOUT ANY WARRANTY; without even the implied warranty of");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* GNU Lesser General Public License for more details.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* You should have received a copy of the GNU Lesser General Public ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* License along with three4g. If not, see <http://www.gnu.org/licenses/>.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* (c) 2012 by Oliver Damm, Am Wasserberg 8, 22869 Schenefeld");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* mail: oliver [dot] damm [at] gmx [dot] de");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* web: http://www.blimster.net ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package ");
    String _pck = objectWrapper.getPck();
    _builder.append(_pck, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      ObjectWrapper _supertype = objectWrapper.getSupertype();
      boolean _equals = Objects.equal(_supertype, null);
      if (_equals) {
        _builder.append("import com.google.gwt.core.client.JavaScriptObject;");
      }
    }
    _builder.newLineIfNotEmpty();
    CharSequence _imports = this.toImports(objectWrapper);
    _builder.append(_imports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This file is generated, do not edit.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    {
      boolean _isAbstract = objectWrapper.isAbstract();
      if (_isAbstract) {
        _builder.append("abstract ");
      } else {
        _builder.append("final ");
      }
    }
    _builder.append("class ");
    String _name = objectWrapper.getName();
    _builder.append(_name, "");
    _builder.append(" extends ");
    {
      ObjectWrapper _supertype_1 = objectWrapper.getSupertype();
      boolean _notEquals = (!Objects.equal(_supertype_1, null));
      if (_notEquals) {
        ObjectWrapper _supertype_2 = objectWrapper.getSupertype();
        String _name_1 = _supertype_2.getName();
        _builder.append(_name_1, "");
      } else {
        _builder.append("JavaScriptObject");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected ");
    String _name_2 = objectWrapper.getName();
    _builder.append(_name_2, "	");
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      ConstructorSection _constructorSection = objectWrapper.getConstructorSection();
      boolean _notEquals_1 = (!Objects.equal(_constructorSection, null));
      if (_notEquals_1) {
        {
          ConstructorSection _constructorSection_1 = objectWrapper.getConstructorSection();
          String _builderConstructor = _constructorSection_1.getBuilderConstructor();
          boolean _notEquals_2 = (!Objects.equal(_builderConstructor, null));
          if (_notEquals_2) {
            _builder.append("public static ");
            String _name_3 = objectWrapper.getName();
            _builder.append(_name_3, "	");
            _builder.append("Builder with()");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return ");
            String _name_4 = objectWrapper.getName();
            _builder.append(_name_4, "		");
            _builder.append("Builder.create();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        {
          ConstructorSection _constructorSection_2 = objectWrapper.getConstructorSection();
          String _defaultConstructor = _constructorSection_2.getDefaultConstructor();
          boolean _notEquals_3 = (!Objects.equal(_defaultConstructor, null));
          if (_notEquals_3) {
            _builder.append("public static native ");
            String _name_5 = objectWrapper.getName();
            _builder.append(_name_5, "	");
            _builder.append(" create()");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("/*-{");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return new $wnd.THREE.");
            String _name_6 = objectWrapper.getName();
            _builder.append(_name_6, "		");
            _builder.append("();");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}-*/;");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        {
          ConstructorSection _constructorSection_3 = objectWrapper.getConstructorSection();
          EList<Constructor> _constructors = _constructorSection_3.getConstructors();
          for(final Constructor constructor : _constructors) {
            _builder.append("public static native ");
            String _name_7 = objectWrapper.getName();
            _builder.append(_name_7, "	");
            _builder.append(" create");
            {
              String _name_8 = constructor.getName();
              boolean _notEquals_4 = (!Objects.equal(_name_8, null));
              if (_notEquals_4) {
                String _name_9 = constructor.getName();
                String _firstUpper = StringExtensions.toFirstUpper(_name_9);
                _builder.append(_firstUpper, "	");
              }
            }
            _builder.append("(");
            {
              EList<Parameter> _parameters = constructor.getParameters();
              boolean _hasElements = false;
              for(final Parameter parameter : _parameters) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "	");
                }
                {
                  boolean _isJson = constructor.isJson();
                  boolean _equals_1 = (_isJson == true);
                  if (_equals_1) {
                    CharSequence _jsonParam = this.toJsonParam(parameter);
                    _builder.append(_jsonParam, "	");
                  } else {
                    CharSequence _param = this.toParam(parameter);
                    _builder.append(_param, "	");
                  }
                }
              }
            }
            _builder.append(")");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("/*-{");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return new $wnd.THREE.");
            String _name_10 = objectWrapper.getName();
            _builder.append(_name_10, "		");
            _builder.append("(");
            {
              boolean _isJson_1 = constructor.isJson();
              boolean _equals_2 = (_isJson_1 == true);
              if (_equals_2) {
                _builder.append("{ ");
              }
            }
            {
              EList<Parameter> _parameters_1 = constructor.getParameters();
              boolean _hasElements_1 = false;
              for(final Parameter parameter_1 : _parameters_1) {
                if (!_hasElements_1) {
                  _hasElements_1 = true;
                } else {
                  _builder.appendImmediate(", ", "		");
                }
                {
                  boolean _isJson_2 = constructor.isJson();
                  boolean _equals_3 = (_isJson_2 == true);
                  if (_equals_3) {
                    CharSequence _jsonArg = this.toJsonArg(parameter_1);
                    _builder.append(_jsonArg, "		");
                  } else {
                    CharSequence _arg = this.toArg(parameter_1);
                    _builder.append(_arg, "		");
                  }
                }
              }
            }
            {
              boolean _isJson_3 = constructor.isJson();
              boolean _equals_4 = (_isJson_3 == true);
              if (_equals_4) {
                _builder.append(" }");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}-*/;");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      PropertySection _propertySection = objectWrapper.getPropertySection();
      boolean _notEquals_5 = (!Objects.equal(_propertySection, null));
      if (_notEquals_5) {
        {
          PropertySection _propertySection_1 = objectWrapper.getPropertySection();
          EList<Property> _properties = _propertySection_1.getProperties();
          for(final Property property : _properties) {
            CharSequence _setterGetter = this.toSetterGetter(property);
            _builder.append(_setterGetter, "	");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      MethodSection _methodSection = objectWrapper.getMethodSection();
      boolean _notEquals_6 = (!Objects.equal(_methodSection, null));
      if (_notEquals_6) {
        {
          MethodSection _methodSection_1 = objectWrapper.getMethodSection();
          EList<Method> _methods = _methodSection_1.getMethods();
          for(final Method method : _methods) {
            CharSequence _method = this.toMethod(method);
            _builder.append(_method, "	");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence toSetterGetter(final Property property) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isReadOnly = property.isReadOnly();
      boolean _equals = (_isReadOnly == false);
      if (_equals) {
        _builder.append("public final native void set");
        String _name = property.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "");
        _builder.append("(");
        Type _type = property.getType();
        CharSequence _type_1 = this.toType(_type);
        _builder.append(_type_1, "");
        _builder.append(" ");
        String _name_1 = property.getName();
        _builder.append(_name_1, "");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("/*-{");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("this.");
        String _name_2 = property.getName();
        _builder.append(_name_2, "	");
        _builder.append(" = ");
        String _name_3 = property.getName();
        _builder.append(_name_3, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("}-*/;");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public final native ");
    Type _type_2 = property.getType();
    CharSequence _type_3 = this.toType(_type_2);
    _builder.append(_type_3, "");
    _builder.append(" get");
    String _name_4 = property.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_1, "");
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    _builder.append("/*-{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return this.");
    String _name_5 = property.getName();
    _builder.append(_name_5, "	");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}-*/;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence toMethod(final Method method) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public final native ");
    {
      Type _returnType = method.getReturnType();
      boolean _notEquals = (!Objects.equal(_returnType, null));
      if (_notEquals) {
        Type _returnType_1 = method.getReturnType();
        CharSequence _type = this.toType(_returnType_1);
        _builder.append(_type, "");
      } else {
        _builder.append("void");
      }
    }
    _builder.append(" ");
    String _name = method.getName();
    _builder.append(_name, "");
    _builder.append("(");
    {
      EList<Parameter> _parameters = method.getParameters();
      boolean _hasElements = false;
      for(final Parameter parameter : _parameters) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "");
        }
        CharSequence _param = this.toParam(parameter);
        _builder.append(_param, "");
      }
    }
    _builder.append(")");
    _builder.newLineIfNotEmpty();
    _builder.append("/*-{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    {
      Type _returnType_2 = method.getReturnType();
      boolean _notEquals_1 = (!Objects.equal(_returnType_2, null));
      if (_notEquals_1) {
        _builder.append("return ");
      }
    }
    _builder.append("this.");
    String _name_1 = method.getName();
    _builder.append(_name_1, "	");
    _builder.append("(");
    {
      EList<Parameter> _parameters_1 = method.getParameters();
      boolean _hasElements_1 = false;
      for(final Parameter parameter_1 : _parameters_1) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(", ", "	");
        }
        CharSequence _arg = this.toArg(parameter_1);
        _builder.append(_arg, "	");
      }
    }
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}-*/;");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence toParam(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    Type _type = parameter.getType();
    CharSequence _type_1 = this.toType(_type);
    _builder.append(_type_1, "");
    _builder.append(" ");
    String _name = parameter.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public CharSequence toJsonParam(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    Type _type = parameter.getType();
    CharSequence _type_1 = this.toType(_type);
    _builder.append(_type_1, "");
    _builder.append(" _");
    String _name = parameter.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public CharSequence toArg(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = parameter.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public CharSequence toJsonArg(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = parameter.getName();
    _builder.append(_name, "");
    _builder.append(" : _");
    String _name_1 = parameter.getName();
    _builder.append(_name_1, "");
    return _builder;
  }
  
  public CharSequence toType(final Type type) {
    CharSequence _xifexpression = null;
    ObjectWrapper _wrapper = type.getWrapper();
    boolean _equals = Objects.equal(_wrapper, null);
    if (_equals) {
      CharSequence _xblockexpression = null;
      {
        int dimensions = 1;
        boolean _and = false;
        ArrayType _arrayType = type.getArrayType();
        boolean _notEquals = (!Objects.equal(_arrayType, null));
        if (!_notEquals) {
          _and = false;
        } else {
          ArrayType _arrayType_1 = type.getArrayType();
          int _dimensions = _arrayType_1.getDimensions();
          boolean _greaterThan = (_dimensions > 1);
          _and = (_notEquals && _greaterThan);
        }
        if (_and) {
          ArrayType _arrayType_2 = type.getArrayType();
          int _dimensions_1 = _arrayType_2.getDimensions();
          dimensions = _dimensions_1;
        }
        StringConcatenation _builder = new StringConcatenation();
        Datatype _datatype = type.getDatatype();
        ArrayType _arrayType_3 = type.getArrayType();
        boolean _notEquals_1 = (!Objects.equal(_arrayType_3, null));
        String _asJavaType = this.asJavaType(_datatype, _notEquals_1, dimensions);
        _builder.append(_asJavaType, "");
        _xblockexpression = (_builder);
      }
      _xifexpression = _xblockexpression;
    } else {
      CharSequence _xblockexpression_1 = null;
      {
        String prefix = "";
        String postfix = "";
        ArrayType _arrayType = type.getArrayType();
        boolean _notEquals = (!Objects.equal(_arrayType, null));
        if (_notEquals) {
          int dimensions = 1;
          ArrayType _arrayType_1 = type.getArrayType();
          int _dimensions = _arrayType_1.getDimensions();
          boolean _greaterThan = (_dimensions > 1);
          if (_greaterThan) {
            ArrayType _arrayType_2 = type.getArrayType();
            int _dimensions_1 = _arrayType_2.getDimensions();
            dimensions = _dimensions_1;
          }
          int i = 0;
          boolean _lessThan = (i < dimensions);
          boolean _while = _lessThan;
          while (_while) {
            {
              String _plus = (prefix + "JsArray<");
              prefix = _plus;
              String _plus_1 = (postfix + ">");
              postfix = _plus_1;
              int _plus_2 = (i + 1);
              i = _plus_2;
            }
            boolean _lessThan_1 = (i < dimensions);
            _while = _lessThan_1;
          }
        }
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(prefix, "");
        ObjectWrapper _wrapper_1 = type.getWrapper();
        String _name = _wrapper_1.getName();
        _builder.append(_name, "");
        {
          ArrayType _arrayType_3 = type.getArrayType();
          boolean _notEquals_1 = (!Objects.equal(_arrayType_3, null));
          if (_notEquals_1) {
            _builder.append(postfix, "");
          }
        }
        _xblockexpression_1 = (_builder);
      }
      _xifexpression = _xblockexpression_1;
    }
    return _xifexpression;
  }
  
  public String asJavaType(final Datatype datatype, final boolean arrayType, final int dimensions) {
    String _xifexpression = null;
    boolean _equals = (arrayType == true);
    if (_equals) {
      String _xblockexpression = null;
      {
        String prefix = "";
        String postfix = "";
        int i = 1;
        boolean _lessThan = (i < dimensions);
        boolean _while = _lessThan;
        while (_while) {
          {
            String _plus = (prefix + "JsArray<");
            prefix = _plus;
            String _plus_1 = (postfix + ">");
            postfix = _plus_1;
            int _plus_2 = (i + 1);
            i = _plus_2;
          }
          boolean _lessThan_1 = (i < dimensions);
          _while = _lessThan_1;
        }
        String _switchResult = null;
        boolean _matched = false;
        if (!_matched) {
          String _name = datatype.name();
          boolean _equals_1 = _name.equals("INT");
          if (_equals_1) {
            _matched=true;
            String _plus = (prefix + "JsArrayInteger");
            String _plus_1 = (_plus + postfix);
            _switchResult = _plus_1;
          }
        }
        if (!_matched) {
          String _name_1 = datatype.name();
          boolean _equals_2 = _name_1.equals("FLOAT");
          if (_equals_2) {
            _matched=true;
            String _plus_2 = (prefix + "JsArrayNumber");
            String _plus_3 = (_plus_2 + postfix);
            _switchResult = _plus_3;
          }
        }
        if (!_matched) {
          String _name_2 = datatype.name();
          boolean _equals_3 = _name_2.equals("STRING");
          if (_equals_3) {
            _matched=true;
            String _plus_4 = (prefix + "JsArrayString");
            String _plus_5 = (_plus_4 + postfix);
            _switchResult = _plus_5;
          }
        }
        if (!_matched) {
          String _name_3 = datatype.name();
          boolean _equals_4 = _name_3.equals("BOOL");
          if (_equals_4) {
            _matched=true;
            String _plus_6 = (prefix + "JsArrayBoolean");
            String _plus_7 = (_plus_6 + postfix);
            _switchResult = _plus_7;
          }
        }
        _xblockexpression = (_switchResult);
      }
      _xifexpression = _xblockexpression;
    } else {
      String _switchResult = null;
      boolean _matched = false;
      if (!_matched) {
        String _name = datatype.name();
        boolean _equals_1 = _name.equals("INT");
        if (_equals_1) {
          _matched=true;
          _switchResult = "int";
        }
      }
      if (!_matched) {
        String _name_1 = datatype.name();
        boolean _equals_2 = _name_1.equals("FLOAT");
        if (_equals_2) {
          _matched=true;
          _switchResult = "double";
        }
      }
      if (!_matched) {
        String _name_2 = datatype.name();
        boolean _equals_3 = _name_2.equals("STRING");
        if (_equals_3) {
          _matched=true;
          _switchResult = "String";
        }
      }
      if (!_matched) {
        String _name_3 = datatype.name();
        boolean _equals_4 = _name_3.equals("BOOL");
        if (_equals_4) {
          _matched=true;
          _switchResult = "boolean";
        }
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  public CharSequence toImports(final ObjectWrapper wrapper) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      HashSet<String> imports = _hashSet;
      ObjectWrapper _supertype = wrapper.getSupertype();
      boolean _notEquals = (!Objects.equal(_supertype, null));
      if (_notEquals) {
        ObjectWrapper _supertype_1 = wrapper.getSupertype();
        String _pck = _supertype_1.getPck();
        String _plus = ("import " + _pck);
        String _plus_1 = (_plus + ".");
        ObjectWrapper _supertype_2 = wrapper.getSupertype();
        String _name = _supertype_2.getName();
        String _plus_2 = (_plus_1 + _name);
        String _plus_3 = (_plus_2 + ";\n");
        imports.add(_plus_3);
      }
      ConstructorSection _constructorSection = wrapper.getConstructorSection();
      boolean _notEquals_1 = (!Objects.equal(_constructorSection, null));
      if (_notEquals_1) {
        ConstructorSection _constructorSection_1 = wrapper.getConstructorSection();
        EList<Constructor> _constructors = _constructorSection_1.getConstructors();
        for (final Constructor constructor : _constructors) {
          EList<Parameter> _parameters = constructor.getParameters();
          for (final Parameter param : _parameters) {
            Type _type = param.getType();
            this.toImport(_type, imports);
          }
        }
      }
      PropertySection _propertySection = wrapper.getPropertySection();
      boolean _notEquals_2 = (!Objects.equal(_propertySection, null));
      if (_notEquals_2) {
        PropertySection _propertySection_1 = wrapper.getPropertySection();
        EList<Property> _properties = _propertySection_1.getProperties();
        for (final Property property : _properties) {
          Type _type_1 = property.getType();
          this.toImport(_type_1, imports);
        }
      }
      MethodSection _methodSection = wrapper.getMethodSection();
      boolean _notEquals_3 = (!Objects.equal(_methodSection, null));
      if (_notEquals_3) {
        MethodSection _methodSection_1 = wrapper.getMethodSection();
        EList<Method> _methods = _methodSection_1.getMethods();
        for (final Method method : _methods) {
          {
            EList<Parameter> _parameters_1 = method.getParameters();
            for (final Parameter param_1 : _parameters_1) {
              Type _type_2 = param_1.getType();
              this.toImport(_type_2, imports);
            }
            boolean _and = false;
            Type _returnType = method.getReturnType();
            boolean _notEquals_4 = (!Objects.equal(_returnType, null));
            if (!_notEquals_4) {
              _and = false;
            } else {
              Type _returnType_1 = method.getReturnType();
              ObjectWrapper _wrapper = _returnType_1.getWrapper();
              boolean _notEquals_5 = (!Objects.equal(_wrapper, null));
              _and = (_notEquals_4 && _notEquals_5);
            }
            if (_and) {
              Type _returnType_2 = method.getReturnType();
              this.toImport(_returnType_2, imports);
            }
          }
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        for(final String imp : imports) {
          _builder.append(imp, "");
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public Boolean toImport(final Type type, final HashSet<String> imports) {
    Boolean _xblockexpression = null;
    {
      ObjectWrapper _wrapper = type.getWrapper();
      boolean _notEquals = (!Objects.equal(_wrapper, null));
      if (_notEquals) {
        ObjectWrapper _wrapper_1 = type.getWrapper();
        String _pck = _wrapper_1.getPck();
        String _plus = ("import " + _pck);
        String _plus_1 = (_plus + ".");
        ObjectWrapper _wrapper_2 = type.getWrapper();
        String _name = _wrapper_2.getName();
        String _plus_2 = (_plus_1 + _name);
        String _plus_3 = (_plus_2 + ";\n");
        imports.add(_plus_3);
        ArrayType _arrayType = type.getArrayType();
        boolean _notEquals_1 = (!Objects.equal(_arrayType, null));
        if (_notEquals_1) {
          imports.add("import com.google.gwt.core.client.JsArray;");
        }
      }
      Boolean _xifexpression = null;
      Datatype _datatype = type.getDatatype();
      boolean _notEquals_2 = (!Objects.equal(_datatype, null));
      if (_notEquals_2) {
        Boolean _xifexpression_1 = null;
        ArrayType _arrayType_1 = type.getArrayType();
        boolean _notEquals_3 = (!Objects.equal(_arrayType_1, null));
        if (_notEquals_3) {
          Boolean _switchResult = null;
          Datatype _datatype_1 = type.getDatatype();
          final Datatype _switchValue = _datatype_1;
          boolean _matched = false;
          if (!_matched) {
            Datatype _datatype_2 = type.getDatatype();
            String _name_1 = _datatype_2.name();
            boolean _equals = _name_1.equals("INT");
            if (_equals) {
              _matched=true;
              boolean _add = imports.add("import com.google.gwt.core.client.JsArrayInteger;\n");
              _switchResult = _add;
            }
          }
          if (!_matched) {
            Datatype _datatype_3 = type.getDatatype();
            String _name_2 = _datatype_3.name();
            boolean _equals_1 = _name_2.equals("FLOAT");
            if (_equals_1) {
              _matched=true;
              boolean _add_1 = imports.add("import com.google.gwt.core.client.JsArrayNumber;\n");
              _switchResult = _add_1;
            }
          }
          if (!_matched) {
            Datatype _datatype_4 = type.getDatatype();
            String _name_3 = _datatype_4.name();
            boolean _equals_2 = _name_3.equals("STRING");
            if (_equals_2) {
              _matched=true;
              boolean _add_2 = imports.add("import com.google.gwt.core.client.JsArrayString;\n");
              _switchResult = _add_2;
            }
          }
          if (!_matched) {
            Datatype _datatype_5 = type.getDatatype();
            String _name_4 = _datatype_5.name();
            boolean _equals_3 = _name_4.equals("BOOL");
            if (_equals_3) {
              _matched=true;
              boolean _add_3 = imports.add("import com.google.gwt.core.client.JsArrayBoolean;\n");
              _switchResult = _add_3;
            }
          }
          _xifexpression_1 = _switchResult;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
}
