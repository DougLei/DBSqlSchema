package com.sql.impl.statement.basic.model.function.builtin.length;

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
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE, DatabaseType.SQLSERVER}, functionName = "_length")
public class Length implements BuiltinFunction{
	private Parameter parameter;
	
	public BuiltinFunction init(JSONObject confJson) {
		parameter = new Parameter(confJson.getJSONObject("parameter"));
		return this;
	}

	public String getSqlStatement() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return "len("+parameter.getSqlStatement()+")";
			case ORACLE:
				return "length("+parameter.getSqlStatement()+")";
		}
		return null;
	}
}
