package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.statement.complex.object.procedure.model.param.Parameter;

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
	
	protected List<Parameter> getParameterList() {
		List<Parameter> parameterList = super.getParameterList();
		if(parameterList != null && parameterList.size() > 0){
			// TODO 二次处理
			
		}
		return parameterList;
	}
}
