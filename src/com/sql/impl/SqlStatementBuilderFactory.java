package com.sql.impl;

import com.sql.SqlStatementBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.Encoding;
import com.sql.exception.UnsupportDatabaseType;
import com.sql.impl.oracle.OracleSqlStatementBuilder;
import com.sql.impl.sqlserver.SqlServerSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public class SqlStatementBuilderFactory {
	
	/**
	 * 
	 * @param databaseType
	 * @return
	 */
	public SqlStatementBuilder createSqlStatementBuilderInstance(DatabaseType databaseType){
		return createSqlStatementBuilderInstance(databaseType, Encoding.UTF_8);
	}
	
	/**
	 * 
	 * @param databaseType
	 * @param encoding
	 * @return
	 */
	public SqlStatementBuilder createSqlStatementBuilderInstance(DatabaseType databaseType, Encoding encoding){
		if(databaseType == null){
			throw new NullPointerException("创建SqlStatementBuilder实例时，传入的databaseType不能为空");
		}
		SqlStatementBuilder builder = null;
		if(DatabaseType.SQLSERVER == databaseType){
			builder = new SqlServerSqlStatementBuilder();
		}else if(DatabaseType.ORACLE == databaseType){
			builder = new OracleSqlStatementBuilder();
		}else{
			throw new UnsupportDatabaseType("创建SqlStatementBuilder实例时，程序暂时不支持" + databaseType.getProductName() + "数据库");
		}
		
		builder.setDatabaseType(databaseType);
		builder.setEncoding(encoding);
		return builder;
	}
}
