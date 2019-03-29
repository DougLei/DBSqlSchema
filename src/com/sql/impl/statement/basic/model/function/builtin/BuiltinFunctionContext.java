package com.sql.impl.statement.basic.model.function.builtin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.douglei.instances.scan.classes.ClassScanner;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;
import com.sql.util.ReflectUtil;

/**
 * 内置函数的上下文
 * @author DougLei
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BuiltinFunctionContext {
	private static final String scanBasePackage = "com.sql.impl.statement.basic.model.function.builtin";
	private static final Map<String, Map<String, Class<? extends BuiltinFunction>>> builtinFunctionCache = new HashMap<String, Map<String, Class<? extends BuiltinFunction>>>(2);
	static{
		builtinFunctionCache.put(DatabaseType.SQLSERVER.getDatabaseType(), new HashMap<String, Class<? extends BuiltinFunction>>());
		builtinFunctionCache.put(DatabaseType.ORACLE.getDatabaseType(), new HashMap<String, Class<? extends BuiltinFunction>>());
		
		// 扫描所有内置函数
		List<String> classNames = ClassScanner.newInstance().scanClasses(scanBasePackage);
		if(classNames != null){
			Class clz = null;
			BuiltinFunctionAnnotation an = null;
			DatabaseType[] supportDtabaseType = null;
			for (String cn : classNames) {
				clz = ReflectUtil.getClass(cn);
				an = (BuiltinFunctionAnnotation) clz.getAnnotation(BuiltinFunctionAnnotation.class);
				if(an != null){
					supportDtabaseType = an.supportDtabaseType();
					for (DatabaseType databaseType : supportDtabaseType) {
						builtinFunctionCache.get(databaseType.getDatabaseType()).put(an.functionName(), clz);
					}
				}
			}
			classNames.clear();
		}
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
