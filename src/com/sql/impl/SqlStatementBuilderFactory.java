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
		Map<String, Class<? extends SqlStatementBuilder>> ssb = sqlStatementBuilderMap.get(databaseType.getDatabaseType());
		Class<? extends SqlStatementBuilder> builderClass = ssb.get(sqlStatementType.getKeyword());
		if(builderClass == null){
			throw new NullPointerException("目前不支持["+databaseType.getProductName()+"]数据库，["+sqlStatementType.getKeyword()+"]类型的sql语句进行build操作");
		}
		SqlStatementBuilder builder = ReflectUtil.newInstance(builderClass);
		return builder;
	}
	
	private static final Map<String, Map<String, Class<? extends SqlStatementBuilder>>> sqlStatementBuilderMap = new HashMap<String, Map<String,Class<? extends SqlStatementBuilder>>>(DatabaseType.values().length);
	static{
		int sqlStatementTypeCount = SqlStatementType.values().length;
		
		// SQLSERVER
		Map<String, Class<? extends SqlStatementBuilder>> sqlserverSqlStatementBuilderMap = new HashMap<String, Class<? extends SqlStatementBuilder>>(sqlStatementTypeCount);
		// TODO 添加sqlserver 的各个builder类
		
		// ORACLE
		Map<String, Class<? extends SqlStatementBuilder>> oracleSqlStatementBuilderMap = new HashMap<String, Class<? extends SqlStatementBuilder>>(sqlStatementTypeCount);
		// TODO 添加oracle 的各个builder类
		
		
		// 将各个builder类添加到缓存集合中
		sqlStatementBuilderMap.put(DatabaseType.SQLSERVER.getDatabaseType(), sqlserverSqlStatementBuilderMap);
		sqlStatementBuilderMap.put(DatabaseType.ORACLE.getDatabaseType(), oracleSqlStatementBuilderMap);
	}
}
