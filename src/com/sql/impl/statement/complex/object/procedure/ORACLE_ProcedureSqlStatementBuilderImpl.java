package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.Parameter;

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
	
	public List<Parameter> getParameterList() {
		List<Parameter> parameterList = super.getParameterList();
		if(parameterList != null && parameterList.size() > 0){
			// TODO 二次处理
			
		}
		return parameterList;
	}
}
