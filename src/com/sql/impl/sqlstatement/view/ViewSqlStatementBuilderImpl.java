package com.sql.impl.sqlstatement.view;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class ViewSqlStatementBuilderImpl extends SqlStatementBuilderImpl {
	protected StringBuilder view = new StringBuilder(10000);
	
	protected String buildSql() {
		
		
		return view.toString();
	}
}
