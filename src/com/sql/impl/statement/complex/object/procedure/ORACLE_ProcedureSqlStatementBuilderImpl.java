package com.sql.impl.statement.complex.object.procedure;

import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {

	public String coverSqlServerSql(String procedureName) {
		return "";
	}

	public String coverOracleSql() {
		return "or replace ";
	}
}
