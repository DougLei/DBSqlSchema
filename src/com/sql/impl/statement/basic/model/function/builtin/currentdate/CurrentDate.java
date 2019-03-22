package com.sql.impl.statement.basic.model.function.builtin.currentdate;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class CurrentDate implements BuiltinFunction{
	
	public BuiltinFunction init(JSONObject confJson) {
		return this;
	}

	public String getSqlStatement() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return "getdate()";
			case ORACLE:
				return "systimestamp";
		}
		return null;
	}
	
	
	public String getFunctionName() {
		return "_current_date";
	}
}
