package com.sql.impl.statement.update;

import com.sql.exception.DBSqlSchemaException;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.update.UpdateSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public abstract class UpdateSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements UpdateSqlStatementBuilder{
	protected StringBuilder update = new StringBuilder(5000);
	
	protected String buildSql() {
		
		throw new DBSqlSchemaException("还未实现 =====> UpdateSqlStatementBuilderImpl");
//		return update.toString();
	}
}
