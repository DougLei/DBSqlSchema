package com.sql.impl.statement.basic.model.function.builtin.guid;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE, DatabaseType.SQLSERVER}, functionName = "_guid")
public class Guid implements BuiltinFunction{
	
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
}
