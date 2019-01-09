package com.sql.impl.sqlstatement.select;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class SelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl {
	protected StringBuilder select = new StringBuilder(5000);
	
	protected String buildSql() {
		
		
		return select.toString();
	}
}
