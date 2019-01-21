package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.Parameter;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {

	public String coverSqlServerSql(String procedureName) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("if exists (select name from sysobjects where xtype = 'P' and name = N'").append(procedureName).append("')");
		sb.append(newline());
		sb.append("drop procedure ").append(procedureName);
		sb.append(newline());
		sb.append("go");
		sb.append(newline());
		return sb.toString();
	}

	public String coverOracleSql() {
		return "";
	}
	
	public List<Parameter> getParameterList() {
		List<Parameter> parameterList = super.getParameterList();
		if(parameterList != null && parameterList.size() > 0){
			// TODO 二次处理
			
		}
		return parameterList;
	}
}
