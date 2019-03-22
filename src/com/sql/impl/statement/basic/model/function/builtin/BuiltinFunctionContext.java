package com.sql.impl.statement.basic.model.function.builtin;

import java.util.HashMap;
import java.util.Map;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.basic.model.function.builtin.append.db.ORACLE_Append;
import com.sql.impl.statement.basic.model.function.builtin.append.db.SQLSERVER_Append;
import com.sql.impl.statement.basic.model.function.builtin.currentdate.CurrentDate;
import com.sql.impl.statement.basic.model.function.builtin.forxmlpath.db.ORACLE_ForXmlPath;
import com.sql.impl.statement.basic.model.function.builtin.forxmlpath.db.SQLSERVER_ForXmlPath;
import com.sql.impl.statement.basic.model.function.builtin.guid.Guid;
import com.sql.impl.statement.basic.model.function.builtin.length.Length;
import com.sql.impl.statement.basic.model.function.builtin.rownumberover.RowNumberOver;
import com.sql.impl.statement.basic.model.function.builtin.substring.db.ORACLE_Substring;
import com.sql.impl.statement.basic.model.function.builtin.substring.db.SQLSERVER_Substring;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 内置函数的上下文
 * @author DougLei
 */
public class BuiltinFunctionContext {
	private static final Map<String, Map<String, Class<? extends BuiltinFunction>>> builtinFunctionCache = new HashMap<String, Map<String, Class<? extends BuiltinFunction>>>(2);
	static{
		int size = 20;
		Map<String, Class<? extends BuiltinFunction>> sqlserver = new HashMap<String, Class<? extends BuiltinFunction>>(size);
		builtinFunctionCache.put(DatabaseType.SQLSERVER.getDatabaseType(), sqlserver);
		Map<String, Class<? extends BuiltinFunction>> oracle = new HashMap<String, Class<? extends BuiltinFunction>>(size);
		builtinFunctionCache.put(DatabaseType.ORACLE.getDatabaseType(), oracle);
		
		// ---------------------------------------------------------------------------------------------------------
		sqlserver.put("_append", SQLSERVER_Append.class);
		oracle.put("_append", ORACLE_Append.class);
		
		sqlserver.put("_for_xml_path", SQLSERVER_ForXmlPath.class);
		oracle.put("_for_xml_path", ORACLE_ForXmlPath.class);
		
		sqlserver.put("_row_number_over", RowNumberOver.class);
		oracle.put("_row_number_over", RowNumberOver.class);
		
		sqlserver.put("_guid", Guid.class);
		oracle.put("_guid", Guid.class);
		
		sqlserver.put("_current_date", CurrentDate.class);
		oracle.put("_current_date", CurrentDate.class);
		
		sqlserver.put("_convert_data_type", CurrentDate.class);
		oracle.put("_convert_data_type", CurrentDate.class);
		
		sqlserver.put("_length", Length.class);
		oracle.put("_length", Length.class);
		
		sqlserver.put("_substring", SQLSERVER_Substring.class);
		oracle.put("_substring", ORACLE_Substring.class);
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
