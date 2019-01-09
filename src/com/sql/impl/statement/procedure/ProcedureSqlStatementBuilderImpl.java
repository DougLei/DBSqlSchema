package com.sql.impl.statement.procedure;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.procedure.ProcedureSqlStatementBuilder;

/**
 * procedure sql语句builder 抽象父类
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder procedure = new StringBuilder(10000);
	
	protected String buildSql() {
		
		
		return procedure.toString();
	}
}
