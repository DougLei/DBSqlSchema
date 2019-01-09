package com.sql.impl.sqlstatement.insert;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class InsertSqlStatementBuilderImpl extends SqlStatementBuilderImpl {
	protected StringBuilder insert = new StringBuilder(5000);
	
	protected String buildSql() {
		
		
		return insert.toString();
	}
}
