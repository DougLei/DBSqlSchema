package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.SqlStatementType;

/**
 * sql语句builder的上下文
 * @author DougLei
 */
public class SqlStatementBuilderContext {
	private static final ThreadLocal<Map<String, SqlStatementBuilder>> builderCache = new ThreadLocal<Map<String,SqlStatementBuilder>>();
	
	public static void set(SqlStatementBuilder builder){
		Map<String, SqlStatementBuilder> builderMap = builderCache.get();
		if(builderMap == null){
			builderMap = new HashMap<String, SqlStatementBuilder>();
		}
		
		builderMap.put(builder.getId(), builder);
		builderCache.set(builderMap);
	}
	
	public static SqlStatementBuilder getBuilder(String builderId){
		Map<String, SqlStatementBuilder> builderMap = builderCache.get();
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
		return getBuilder(builderId).buildSqlStatement();
	}
	
	public static SqlStatementType getBuilderSqlStatementType(String builderId){
		return getBuilder(builderId).getSqlStatementType();
	}
	
	public static void clear(){
		if(builderCache.get() != null && builderCache.get().size() > 0){
			builderCache.get().clear();
		}
	}
}
