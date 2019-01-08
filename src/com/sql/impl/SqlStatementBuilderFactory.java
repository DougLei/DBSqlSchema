package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;
import com.sql.util.ReflectUtil;

/**
 * sql语句 builder工厂
 * @author DougLei
 */
class SqlStatementBuilderFactory {
	
	/**
	 * 创建SqlStatementBuilder实例
	 * @param databaseType
	 * @param sqlStatementType
	 * @return
	 */
	final static SqlStatementBuilder createSqlStatementBuilderInstance(DatabaseType databaseType, SqlStatementType sqlStatementType){
		Map<String, Class<SqlStatementBuilder>> ssb = sqlStatementBuilderMap.get(databaseType.getDatabaseType());
		Class<SqlStatementBuilder> builderClass = ssb.get(sqlStatementType.getKeyword());
		if(builderClass == null){
			throw new NullPointerException("目前不支持["+databaseType.getProductName()+"]数据库，["+sqlStatementType.getKeyword()+"]类型的sql语句进行build操作");
		}
		SqlStatementBuilder builder = ReflectUtil.newInstance(builderClass);
		return builder;
	}
	
	private static final int databaseTypeCount = DatabaseType.values().length;
	private static final int sqlStatementTypeCount = SqlStatementType.values().length;
	private static final Map<String, Map<String, Class<SqlStatementBuilder>>> sqlStatementBuilderMap = new HashMap<String, Map<String,Class<SqlStatementBuilder>>>(databaseTypeCount);
	static{
		// SQLSERVER
		Map<String, Class<SqlStatementBuilder>> sqlserverSqlStatementBuilderMap = new HashMap<String, Class<SqlStatementBuilder>>(sqlStatementTypeCount);
		sqlStatementBuilderMap.put(DatabaseType.SQLSERVER.getDatabaseType(), sqlserverSqlStatementBuilderMap);
		
		// ORACLE
		Map<String, Class<SqlStatementBuilder>> oracleSqlStatementBuilderMap = new HashMap<String, Class<SqlStatementBuilder>>(sqlStatementTypeCount);
		sqlStatementBuilderMap.put(DatabaseType.ORACLE.getDatabaseType(), oracleSqlStatementBuilderMap);
	}
}
