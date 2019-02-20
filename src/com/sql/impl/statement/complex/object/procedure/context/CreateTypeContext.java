package com.sql.impl.statement.complex.object.procedure.context;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;

/**
 * 在存储过程中使用的自定义类型，需要先创建出来，这里存储的是创建语句，最后要在创建存储过程语句之前先执行这些创建类型的语句
 * @author DougLei
 */
public class CreateTypeContext {
	/**
	 * 在存储过程中可能会出现创建某些自定义类型，所以这里统一记录创建类型的sql语句，最后统一放到存储过程的最前面，实现先创建自定义类型，再创建存储过程
	 */
	private static final ThreadLocal<StringBuilder> createTypeSqlStatementLocal = new ThreadLocal<StringBuilder>();
	
	public static boolean includeCreateType(){
		StringBuilder sb = createTypeSqlStatementLocal.get();
		if(sb != null && sb.length() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 记录创建自定义类型的sql语句
	 * @param sql
	 */
	public static void recordCreateTypeSqlStatement(String sql){
		StringBuilder sb = createTypeSqlStatementLocal.get();
		if(sb == null){
			sb = new StringBuilder(1000);
			createTypeSqlStatementLocal.set(sb);
		}
		sb.append(sql).append("\n");
		sb.append(linkNextSqlStatementToken());
	}
	
	private static String linkNextSqlStatementToken() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return "\ngo\n";
			case ORACLE:
				return "\n;\n";
		}
		return null;
	}

	public static String getCreateTypeSqlStatement(){
		String sql = createTypeSqlStatementLocal.get().toString();
		clear();
		return sql;
	}
	
	public static void clear(){
		StringBuilder sb = createTypeSqlStatementLocal.get();
		if(sb != null && sb.length() > 0){
			sb.setLength(0);
		}
	}
}
