package com.sql.statement.complex.object.procedure;

import java.util.List;
import com.sql.statement.complex.object.DBObjectSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.Parameter;

/**
 * create procedure sql语句builder 接口
 * @author DougLei
 */
public interface ProcedureSqlStatementBuilder extends DBObjectSqlStatementBuilder{
	
	/**
	 * 获取存储过程参数集合
	 * @return
	 */
	List<Parameter> getParameterList();
}
