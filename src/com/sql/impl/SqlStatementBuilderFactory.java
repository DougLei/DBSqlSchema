package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;
import com.sql.impl.statement.basic.delete.DeleteSqlStatementBuilderImpl;
import com.sql.impl.statement.basic.insert.InsertSqlStatementBuilderImpl;
import com.sql.impl.statement.basic.select.SelectSqlStatementBuilderImpl;
import com.sql.impl.statement.basic.update.UpdateSqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.ORACLE_ProcedureSqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.SQLSERVER_ProcedureSqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.view.ORACLE_ViewSqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.view.SQLSERVER_ViewSqlStatementBuilderImpl;
import com.sql.impl.statement.complex.select.CombinationSelectSqlStatementBuilderImpl;
import com.sql.util.ReflectUtil;

/**
 * sql语句 builder工厂
 * @author DougLei
 */
class SqlStatementBuilderFactory {
	
	/**
	 * 创建SqlStatementBuilder实例
	 * @param sqlStatementType
	 * @return
	 */
	final static SqlStatementBuilder createSqlStatementBuilderInstance(SqlStatementType sqlStatementType){
		Map<String, Class<? extends SqlStatementBuilder>> buildMap = sqlStatementBuilderMap.get(SqlStatementBuilderContext.getDatabaseType().getDatabaseType());
		if(buildMap == null){
			throw new NullPointerException("目前不支持对["+SqlStatementBuilderContext.getDatabaseType()+"]类型数据库sql语句进行build操作");
		}
		Class<? extends SqlStatementBuilder> builderClass = buildMap.get(sqlStatementType.getKeyword());
		if(builderClass == null){
			throw new NullPointerException("目前不支持对["+sqlStatementType.getKeyword()+"]类型的sql语句进行build操作");
		}
		SqlStatementBuilder builder = ReflectUtil.newInstance(builderClass);
		return builder;
	}
	
	private static final Map<String, Map<String, Class<? extends SqlStatementBuilder>>> sqlStatementBuilderMap = new HashMap<String, Map<String, Class<? extends SqlStatementBuilder>>>(DatabaseType.values().length);
	static{
		int length = SqlStatementType.values().length;
		Map<String, Class<? extends SqlStatementBuilder>> sqlserver = new HashMap<String, Class<? extends SqlStatementBuilder>>(length);
		Map<String, Class<? extends SqlStatementBuilder>> oracle = new HashMap<String, Class<? extends SqlStatementBuilder>>(length);
		sqlStatementBuilderMap.put(DatabaseType.SQLSERVER.getDatabaseType(), sqlserver);
		sqlStatementBuilderMap.put(DatabaseType.ORACLE.getDatabaseType(), oracle);
		
		Map<String, Class<? extends SqlStatementBuilder>> commonBuilder = new HashMap<String, Class<? extends SqlStatementBuilder>>(5);
		commonBuilder.put(SqlStatementType.SELECT.getKeyword(), SelectSqlStatementBuilderImpl.class);
		commonBuilder.put(SqlStatementType.INSERT.getKeyword(), InsertSqlStatementBuilderImpl.class);
		commonBuilder.put(SqlStatementType.DELETE.getKeyword(), DeleteSqlStatementBuilderImpl.class);
		commonBuilder.put(SqlStatementType.UPDATE.getKeyword(), UpdateSqlStatementBuilderImpl.class);
		commonBuilder.put(SqlStatementType.COMBINATION_SELECT.getKeyword(), CombinationSelectSqlStatementBuilderImpl.class);
		
		// ---------------------------------------------------------------------------------------------------
		sqlserver.putAll(commonBuilder);
		sqlserver.put(SqlStatementType.VIEW.getKeyword(), SQLSERVER_ViewSqlStatementBuilderImpl.class);
		sqlserver.put(SqlStatementType.PROCEDURE.getKeyword(), SQLSERVER_ProcedureSqlStatementBuilderImpl.class);
		
		// ---------------------------------------------------------------------------------------------------
		oracle.putAll(commonBuilder);
		oracle.put(SqlStatementType.VIEW.getKeyword(), ORACLE_ViewSqlStatementBuilderImpl.class);
		oracle.put(SqlStatementType.PROCEDURE.getKeyword(), ORACLE_ProcedureSqlStatementBuilderImpl.class);
	}
}
