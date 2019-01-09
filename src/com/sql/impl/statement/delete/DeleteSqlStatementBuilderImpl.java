package com.sql.impl.statement.delete;

import com.sql.exception.DBSqlSchemaException;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.delete.DeleteSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public abstract class DeleteSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements DeleteSqlStatementBuilder{
	protected StringBuilder delete = new StringBuilder(5000);
	
	protected String buildSql() {
		
		throw new DBSqlSchemaException("还未实现 =====> DeleteSqlStatementBuilderImpl");
//		return delete.toString();
	}
}
