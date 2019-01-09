package com.sql.impl.sqlstatement.procedure;

import com.sql.impl.SqlStatementBuilderImpl;

/**
 * 
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilder extends SqlStatementBuilderImpl {
	protected StringBuilder procedure = new StringBuilder(10000);
	
	protected String buildSql() {
		
		
		return procedure.toString();
	}
}
