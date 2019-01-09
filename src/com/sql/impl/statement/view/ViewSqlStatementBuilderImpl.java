package com.sql.impl.statement.view;

import com.sql.exception.DBSqlSchemaException;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.view.ViewSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public abstract class ViewSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ViewSqlStatementBuilder{
	protected StringBuilder view = new StringBuilder(10000);
	
	protected String buildSql() {
		
		throw new DBSqlSchemaException("还未实现 =====> ViewSqlStatementBuilderImpl");
//		return view.toString();
	}
}
