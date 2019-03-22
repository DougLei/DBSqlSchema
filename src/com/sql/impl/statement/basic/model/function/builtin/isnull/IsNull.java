package com.sql.impl.statement.basic.model.function.builtin.isnull;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class IsNull implements BuiltinFunction{
	private Parameter parameter;
	private Parameter defaultParameter;
	
	public BuiltinFunction init(JSONObject confJson) {
		parameter = new Parameter(confJson.getJSONObject("parameter"));
		defaultParameter = new Parameter(confJson.getJSONObject("defaultParameter"));
		return this;
	}

	public String getSqlStatement() {
		String sql = "("+parameter.getSqlStatement()+", " + defaultParameter.getSqlStatement() + ")";
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return "isnull" + sql ;
			case ORACLE:
				return "nvl" + sql;
		}
		return null;
	}
	
	
	public String getFunctionName() {
		return "_isnull";
	}
}
