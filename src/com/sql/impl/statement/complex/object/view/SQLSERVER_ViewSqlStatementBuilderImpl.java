package com.sql.impl.statement.complex.object.view;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ViewSqlStatementBuilderImpl extends ViewSqlStatementBuilderImpl{

	public String coverSqlServerSql(String viewName) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("if exists (select name from sysobjects where name = N'").append(viewName).append("')");
		sb.append(newline());
		sb.append("drop view ").append(viewName);
		sb.append(newline());
		sb.append("go");
		sb.append(newline());
		return sb.toString();
	}

	public String coverOracleSql() {
		return "";
	}
}
