package com.sql.impl.statement.basic.model.function.builtin.currentdate;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE, DatabaseType.SQLSERVER}, functionName = "_current_date")
public class CurrentDate implements BuiltinFunction{
	
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
}
