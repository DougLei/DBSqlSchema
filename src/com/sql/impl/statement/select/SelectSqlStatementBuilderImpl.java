package com.sql.impl.statement.select;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.select.SelectSqlStatementBuilder;

public class SelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements SelectSqlStatementBuilder {
	protected StringBuilder selectSqlStatement = new StringBuilder(5000);
	
	public String buildSqlStatement() {
		selectSqlStatement.append("select ").append(newline());
		
		
		
		
		
		return selectSqlStatement.toString();
	}
}
