package com.sql.statement.complex.object.procedure;

import com.sql.statement.complex.object.DBObjectSqlStatementBuilder;

/**
 * create procedure sql语句builder 接口
 * @author DougLei
 */
public interface ProcedureSqlStatementBuilder extends DBObjectSqlStatementBuilder{
	/**
	 * 是否需要事务
	 * @return
	 */
	boolean isTransaction();
}
