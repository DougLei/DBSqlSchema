package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.SqlStatementType;
import com.sql.impl.statement.insert.InsertSqlStatementBuilderImpl;
import com.sql.impl.statement.select.SelectSqlStatementBuilderImpl;
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
	final static SqlStatementBuilder createSqlStatementBuilderInstance(SqlStatementType sqlStatementType){
		Class<? extends SqlStatementBuilder> builderClass = sqlStatementBuilderMap.get(sqlStatementType.getKeyword());
		if(builderClass == null){
			throw new NullPointerException("目前不支持对["+sqlStatementType.getKeyword()+"]类型的sql语句进行build操作");
		}
		SqlStatementBuilder builder = ReflectUtil.newInstance(builderClass);
		return builder;
	}
	
	private static final Map<String, Class<? extends SqlStatementBuilder>> sqlStatementBuilderMap = new HashMap<String, Class<? extends SqlStatementBuilder>>(SqlStatementType.values().length);
	static{
		sqlStatementBuilderMap.put(SqlStatementType.SELECT.getKeyword(), SelectSqlStatementBuilderImpl.class);
		sqlStatementBuilderMap.put(SqlStatementType.INSERT.getKeyword(), InsertSqlStatementBuilderImpl.class);
	}
}
