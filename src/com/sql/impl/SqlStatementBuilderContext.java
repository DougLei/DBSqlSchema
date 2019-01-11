package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.DatabaseType;
import com.sql.exception.DBSqlSchemaException;

/**
 * sql语句builder的上下文
 * @author DougLei
 */
public class SqlStatementBuilderContext {
	private static final ThreadLocal<DatabaseType> localDatabaseType = new ThreadLocal<DatabaseType>();
	private static final ThreadLocal<Map<String, SqlStatementBuilder>> localBuilder = new ThreadLocal<Map<String,SqlStatementBuilder>>();
	
	public static void setDatabaseType(DatabaseType databaseType){
		if(databaseType != null){
			if(localDatabaseType.get() == null){
				localDatabaseType.set(databaseType);
			}else if(localDatabaseType.get() != databaseType){
				throw new DBSqlSchemaException("当前处理的是["+localDatabaseType.get()+"]数据库类型，无法同时处理["+databaseType+"]数据库类型");
			}
		}
	}
	
	public static DatabaseType getDatabaseType(){
		return localDatabaseType.get();
	}
	
	public static void setSqlStatementBuilder(SqlStatementBuilder builder){
		Map<String, SqlStatementBuilder> builderMap = localBuilder.get();
		if(builderMap == null){
			builderMap = new HashMap<String, SqlStatementBuilder>();
		}
		
		builderMap.put(builder.getId(), builder);
		localBuilder.set(builderMap);
	}
	
	public static SqlStatementBuilder getSqlStatementBuilder(String builderId){
		Map<String, SqlStatementBuilder> builderMap = localBuilder.get();
		if(builderMap == null){
			throw new NullPointerException("SqlStatementBuilderContext.builderCache is null");
		}
		SqlStatementBuilder builder = builderMap.get(builderId);
		if(builder == null){
			throw new NullPointerException("不存在id为["+builderId+"]的配置信息");
		}
		return builder;
	}
	
	public static String buildSqlStatement(String builderId){
		return getSqlStatementBuilder(builderId).buildSqlStatement();
	}
	
	public static void clear(){
		if(localBuilder.get() != null && localBuilder.get().size() > 0){
			localBuilder.get().clear();
		}
	}
}
