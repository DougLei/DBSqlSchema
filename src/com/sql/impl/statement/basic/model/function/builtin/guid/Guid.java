package com.sql.impl.statement.basic.model.function.builtin.guid;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class Guid implements BuiltinFunction{
	
	public BuiltinFunction init(JSONObject confJson) {
		return this;
	}

	public String getSqlStatement() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return "newid()";
			case ORACLE:
				return "sys_guid()";
		}
		return null;
	}
	
	
	public String getFunctionName() {
		return "_guid";
	}
}
