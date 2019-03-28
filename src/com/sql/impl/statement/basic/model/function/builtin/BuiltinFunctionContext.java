package com.sql.impl.statement.basic.model.function.builtin;

import java.util.HashMap;
import java.util.Map;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 内置函数的上下文
 * @author DougLei
 */
public class BuiltinFunctionContext {
	private static final String scanBasePackage = "com.sql.impl.statement.basic.model.function.builtin";
	private static final Map<String, Map<String, Class<? extends BuiltinFunction>>> builtinFunctionCache = new HashMap<String, Map<String, Class<? extends BuiltinFunction>>>(2);
	static{
		// TODO 
		
		
		
	}
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 是否是内置函数
	 * @param name
	 * @return
	 */
	private static Class<? extends BuiltinFunction> isBuiltinFunction(String name) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		Map<String, Class<? extends BuiltinFunction>> functions = builtinFunctionCache.get(dbType.getDatabaseType());
		if(functions != null && functions.size() > 0){
			return functions.get(name);
		}
		return null;
	}
	
	/**
	 * 获取内置函数的实例
	 * @param name
	 * @return
	 */
	public static BuiltinFunction getBuiltinFunctionNewInstance(String name){
		Class<? extends BuiltinFunction> bfClass = isBuiltinFunction(name);
		if(bfClass != null){
			try {
				return bfClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
