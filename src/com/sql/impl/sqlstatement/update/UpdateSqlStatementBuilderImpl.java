package com.sql.impl.sqlstatement.update;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class UpdateSqlStatementBuilderImpl extends SqlStatementBuilderImpl {
	protected StringBuilder update = new StringBuilder(5000);
	
	protected String buildSql() {
		
		
		return update.toString();
	}
}
