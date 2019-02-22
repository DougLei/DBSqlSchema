package com.sql.impl.statement.complex.object.procedure;


/**
 * 
 * @author DougLei
 */
public class ORACLE_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl {

	public String coverSqlServerSql(String procedureName) {
		return "";
	}

	public String coverOracleSql() {
		return "or replace ";
	}
	
	// oracle的存储过程是默认开启事务的，不需要begin transaction;操作
	protected String beginTransaction() {
		return "";
	}
}
