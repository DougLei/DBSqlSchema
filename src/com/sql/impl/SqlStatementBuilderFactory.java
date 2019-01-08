package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.sql.SqlStatementBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;
import com.sql.impl.sqlstatement.delete.OracleDeleteSqlStatementBuilder;
import com.sql.impl.sqlstatement.delete.SqlServerDeleteSqlStatementBuilder;
import com.sql.impl.sqlstatement.insert.OracleInsertSqlStatementBuilder;
import com.sql.impl.sqlstatement.insert.SqlServerInsertSqlStatementBuilder;
import com.sql.impl.sqlstatement.procedure.OracleProcedureSqlStatementBuilder;
import com.sql.impl.sqlstatement.procedure.SqlServerProcedureSqlStatementBuilder;
import com.sql.impl.sqlstatement.select.OracleSelectSqlStatementBuilder;
import com.sql.impl.sqlstatement.select.SqlServerSelectSqlStatementBuilder;
import com.sql.impl.sqlstatement.update.OracleUpdateSqlStatementBuilder;
import com.sql.impl.sqlstatement.update.SqlServerUpdateSqlStatementBuilder;
import com.sql.impl.sqlstatement.view.OracleViewSqlStatementBuilder;
import com.sql.impl.sqlstatement.view.SqlServerViewSqlStatementBuilder;
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
	
	private static final int databaseTypeCount = DatabaseType.values().length;
	private static final int sqlStatementTypeCount = SqlStatementType.values().length;
	private static final Map<String, Map<String, Class<? extends SqlStatementBuilder>>> sqlStatementBuilderMap = new HashMap<String, Map<String,Class<? extends SqlStatementBuilder>>>(databaseTypeCount);
	static{
		// SQLSERVER
		Map<String, Class<? extends SqlStatementBuilder>> sqlserverSqlStatementBuilderMap = new HashMap<String, Class<? extends SqlStatementBuilder>>(sqlStatementTypeCount);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.SELECT.getKeyword(), SqlServerSelectSqlStatementBuilder.class);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.INSERT.getKeyword(), SqlServerInsertSqlStatementBuilder.class);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.UPDATE.getKeyword(), SqlServerUpdateSqlStatementBuilder.class);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.DELETE.getKeyword(), SqlServerDeleteSqlStatementBuilder.class);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.VIEW.getKeyword(), SqlServerViewSqlStatementBuilder.class);
		sqlserverSqlStatementBuilderMap.put(SqlStatementType.PROCEDURE.getKeyword(), SqlServerProcedureSqlStatementBuilder.class);
		sqlStatementBuilderMap.put(DatabaseType.SQLSERVER.getDatabaseType(), sqlserverSqlStatementBuilderMap);
		
		// ORACLE
		Map<String, Class<? extends SqlStatementBuilder>> oracleSqlStatementBuilderMap = new HashMap<String, Class<? extends SqlStatementBuilder>>(sqlStatementTypeCount);
		oracleSqlStatementBuilderMap.put(SqlStatementType.SELECT.getKeyword(), OracleSelectSqlStatementBuilder.class);
		oracleSqlStatementBuilderMap.put(SqlStatementType.INSERT.getKeyword(), OracleInsertSqlStatementBuilder.class);
		oracleSqlStatementBuilderMap.put(SqlStatementType.UPDATE.getKeyword(), OracleUpdateSqlStatementBuilder.class);
		oracleSqlStatementBuilderMap.put(SqlStatementType.DELETE.getKeyword(), OracleDeleteSqlStatementBuilder.class);
		oracleSqlStatementBuilderMap.put(SqlStatementType.VIEW.getKeyword(), OracleViewSqlStatementBuilder.class);
		oracleSqlStatementBuilderMap.put(SqlStatementType.PROCEDURE.getKeyword(), OracleProcedureSqlStatementBuilder.class);
		sqlStatementBuilderMap.put(DatabaseType.ORACLE.getDatabaseType(), oracleSqlStatementBuilderMap);
	}
}
