package com.sql.impl.sqlstatement.delete;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class DeleteSqlStatementBuilderImpl extends SqlStatementBuilderImpl {
	protected StringBuilder delete = new StringBuilder(5000);
	
	protected String buildSql() {
		
		
		return delete.toString();
	}
}
