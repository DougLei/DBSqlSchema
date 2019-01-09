package com.sql.impl.statement.insert;

import com.sql.exception.DBSqlSchemaException;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.insert.InsertSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public abstract class InsertSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements InsertSqlStatementBuilder{
	protected StringBuilder insert = new StringBuilder(5000);
	
	protected String buildSql() {
		
		throw new DBSqlSchemaException("还未实现 =====> InsertSqlStatementBuilderImpl");
//		return insert.toString();
	}
}
