package com.sql.impl.statement.select;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.select.SelectSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public abstract class SelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements SelectSqlStatementBuilder{
	protected StringBuilder select = new StringBuilder(5000);
	
	protected String buildSql() {
		
		
		
		return select.toString();
	}
}
