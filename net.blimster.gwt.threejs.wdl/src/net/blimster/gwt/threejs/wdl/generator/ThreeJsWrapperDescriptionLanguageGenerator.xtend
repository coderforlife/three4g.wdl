/*
 * generated by Xtext
 */
package net.blimster.gwt.threejs.wdl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess

import static extension org.eclipse.xtext.xbase.lib.IterableExtensions.*
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.ObjectWrapper
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Method
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Parameter
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Property
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Constructor
import java.util.HashSet
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Datatype
import net.blimster.gwt.threejs.wdl.threeJsWrapperDescriptionLanguage.Type

class ThreeJsWrapperDescriptionLanguageGenerator implements IGenerator {
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) 
	{
		for(objectWrapper:resource.allContents.toIterable.filter(typeof(ObjectWrapper))) 
		{
			fsa.generateFile(objectWrapper.pck.toString.replace(".", "/") + "/" + objectWrapper.name + ".java", objectWrapper.toClass)
    	}
	}
	
	def toClass(ObjectWrapper objectWrapper)
	'''
		/*
		 *
		 * This file is part of three4g.
		 *
		 * three4g is free software: you can redistribute it and/or modify
		 * it under the terms of the GNU Lesse General Public License as 
		 * published by the Free Software Foundation, either version 3 of 
		 * the License, or (at your option) any later version.
		 *
		 * three4g is distributed in the hope that it will be useful,
		 * but WITHOUT ANY WARRANTY; without even the implied warranty of
		 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
		 * GNU Lesser General Public License for more details.
		 *
		 * You should have received a copy of the GNU Lesser General Public 
		 * License along with three4g. If not, see <http://www.gnu.org/licenses/>.
		 *
		 * (c) 2012 by Oliver Damm, Am Wasserberg 8, 22869 Schenefeld
		 *
		 * mail: oliver [dot] damm [at] gmx [dot] de
		 * web: http://www.blimster.net 
		 */
		package �objectWrapper.pck�;

		�IF objectWrapper.supertype == null�import com.google.gwt.core.client.JavaScriptObject;�ENDIF�
		�objectWrapper.toImports�
		
		/**
		 * This file is generated, do not edit.
		 */
		public �IF objectWrapper.isAbstract()�abstract �ELSE�final �ENDIF�class �objectWrapper.name� extends �IF objectWrapper.supertype != null��objectWrapper.supertype.name��ELSE�JavaScriptObject�ENDIF�
		{
			
			protected �objectWrapper.name�()
			{
				super();
			}
			
			�IF objectWrapper.constructorSection != null��IF objectWrapper.constructorSection.builderConstructor != null�public static �objectWrapper.name�Builder with()
			{
				
				return �objectWrapper.name�Builder.create();
				
			}�ENDIF�
			
			�IF objectWrapper.constructorSection.defaultConstructor != null�public static native �objectWrapper.name� create()
			/*-{
				
				return new $wnd.THREE.�objectWrapper.name�();
				
			}-*/;�ENDIF�
			
			
			
			�FOR constructor:objectWrapper.constructorSection.constructors�public static native �objectWrapper.name� create�IF constructor.name != null��constructor.name.toFirstUpper��ENDIF�(�FOR parameter:constructor.parameters SEPARATOR ', '��IF constructor.json == true��parameter.toJsonParam��ELSE��parameter.toParam��ENDIF��ENDFOR�)
			/*-{
				
				return new $wnd.THREE.�objectWrapper.name�(�IF constructor.json == true�{ �ENDIF��FOR parameter:constructor.parameters SEPARATOR ', '��IF constructor.json == true��parameter.toJsonArg��ELSE��parameter.toArg��ENDIF��ENDFOR��IF constructor.json == true� }�ENDIF�);
				
			}-*/;
			
			�ENDFOR��ENDIF�
			
			�IF objectWrapper.propertySection != null��FOR property:objectWrapper.propertySection.properties��property.toSetterGetter��ENDFOR��ENDIF�
			
			�IF objectWrapper.methodSection != null��FOR method:objectWrapper.methodSection.methods��method.toMethod��ENDFOR��ENDIF�
			
		}
	'''
	
	def toSetterGetter(Property property)
	'''
	�IF property.readOnly == false�public final native void set�property.name.toFirstUpper�(�property.type.toType� �property.name�)
	/*-{
		
		this.�property.name� = �property.name�;
		
	}-*/;�ENDIF�

	public final native �property.type.toType� get�property.name.toFirstUpper�()
	/*-{
		
		return this.�property.name�;
		
	}-*/;
	
	'''
	
	def toMethod(Method method)
	'''
	public final native �IF method.returnType != null��method.returnType.toType��ELSE�void�ENDIF� �method.name�(�FOR parameter:method.parameters SEPARATOR ', '��parameter.toParam��ENDFOR�)
	/*-{
		
		�IF method.returnType != null�return �ENDIF�this.�method.name�(�FOR parameter:method.parameters SEPARATOR ', '��parameter.toArg��ENDFOR�);
		
	}-*/;
	
	'''
	
	def toParam(Parameter parameter)
	'''�parameter.type.toType� �parameter.name�'''

	def toJsonParam(Parameter parameter)
	'''�parameter.type.toType� _�parameter.name�'''

	def toArg(Parameter parameter)
	'''�parameter.name�'''

	def toJsonArg(Parameter parameter)
	'''�parameter.name� : _�parameter.name�'''
	
	def toType(Type type)
	{
		if(type.wrapper == null) 
		{
			var dimensions = 1;
			if(type.arrayType != null && type.arrayType.dimensions > 1)
			{
				dimensions = type.arrayType.dimensions;
			}
			'''�type.datatype.asJavaType(type.arrayType != null, dimensions)�'''
		}
		else
		{
			var prefix = "";
			var postfix = "";
			if(type.arrayType != null)
			{
				var dimensions = 1;
				if(type.arrayType.dimensions > 1)
				{
					dimensions = type.arrayType.dimensions;
				}
				
				var i = 0;
				while(i < dimensions)
				{
					prefix = prefix + "JsArray<";
					postfix = postfix + ">";
					i = i + 1;
				}
				
			}
			
			'''�prefix��type.wrapper.name��IF type.arrayType != null��postfix��ENDIF�'''
		}
	}
	
	def asJavaType(Datatype datatype, boolean arrayType, int dimensions)
	{
		if(arrayType == true)
		{
			var prefix = "";
			var postfix = "";
			
			var i = 1;
			while(i < dimensions)
			{
				prefix = prefix + "JsArray<";
				postfix = postfix + ">"
				i = i + 1;
			}
			
			switch(datatype) 
			{
				case datatype.name.equals('INT'): prefix + "JsArrayInteger" + postfix
				case datatype.name.equals('FLOAT'): prefix + "JsArrayNumber" + postfix
				case datatype.name.equals('STRING'): prefix + "JsArrayString" + postfix
				case datatype.name.equals('BOOL'): prefix + "JsArrayBoolean" + postfix
			}
		}
		else 
		{
			switch(datatype) 
			{
				case datatype.name.equals('INT'): "int"
				case datatype.name.equals('FLOAT'): "double"
				case datatype.name.equals('STRING'): "String"
				case datatype.name.equals('BOOL'): "boolean"
			}
		}
	}
	
	def toImports(ObjectWrapper wrapper) 
	{
		var imports = new HashSet<String>();
		if(wrapper.supertype != null) 
		{
			imports.add("import " + wrapper.supertype.pck + "." + wrapper.supertype.name + ";\n");
		}
		
		if(wrapper.constructorSection != null)
		{
			for(Constructor constructor : wrapper.constructorSection.constructors)
			{
				for(Parameter param : constructor.parameters)
				{
					param.type.toImport(imports);
				}	
			}
		}

		if(wrapper.propertySection != null)
		{
			for(Property property : wrapper.propertySection.properties)
			{
				property.type.toImport(imports);
			}
		}

		if(wrapper.methodSection != null)
		{
			for(Method method : wrapper.methodSection.methods)
			{
				for(Parameter param : method.parameters)
				{
					param.type.toImport(imports);
				}
				if(method.returnType != null && method.returnType.wrapper != null)
				{
					method.returnType.toImport(imports);
				}	
			}
		}
		
		'''�FOR imp:imports��imp��ENDFOR�'''
	}
	
	def toImport(Type type, HashSet<String> imports)
	{
		if(type.wrapper != null) 
		{
			imports.add("import " + type.wrapper.pck + "." + type.wrapper.name + ";\n");
			if(type.arrayType != null) 
			{
				imports.add("import com.google.gwt.core.client.JsArray;");
			}
		}
		
		if(type.datatype != null)
		{
			if(type.arrayType != null)
			{
				switch(type.datatype) 
				{
					case type.datatype.name.equals('INT'): imports.add("import com.google.gwt.core.client.JsArrayInteger;\n")
					case type.datatype.name.equals('FLOAT'): imports.add("import com.google.gwt.core.client.JsArrayNumber;\n")
					case type.datatype.name.equals('STRING'): imports.add("import com.google.gwt.core.client.JsArrayString;\n")
					case type.datatype.name.equals('BOOL'): imports.add("import com.google.gwt.core.client.JsArrayBoolean;\n")
					
				}
			}
		}			
	}
	
}
