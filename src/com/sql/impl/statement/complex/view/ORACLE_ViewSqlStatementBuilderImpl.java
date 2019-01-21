package com.sql.impl.statement.complex.view;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ViewSqlStatementBuilderImpl extends ViewSqlStatementBuilderImpl{

	public String coverSqlServerSql(String viewName) {
		return "";
	}

	public String coverOracleSql() {
		return "or replace ";
	}
}
