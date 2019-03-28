package com.sql.impl.statement.basic.model.function.builtin.isnull;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE, DatabaseType.SQLSERVER}, functionName = "_isnull")
public class IsNull implements BuiltinFunction{
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
}
