package com.sql.impl.statement.complex.object.datatype.cursor;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;

public class ORACLE_CURSOR extends AbstractCustomDataType{
	private ORACLE_CURSOR(){}
	private static final ORACLE_CURSOR cursorDataType = new ORACLE_CURSOR();
	public static final ORACLE_CURSOR newInstance(){
		return cursorDataType;
	}
	
	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		StringBuilder sb = new StringBuilder(500);
		sb.append("cursor ").append(name).append(" is").append(newline());
		sb.append("(").append(SqlStatementBuilderContext.getSqlStatement(customJson.getString("sqlId"), customJson.getJSONObject("sqlJson"))).append(")");
		return sb.toString();
	}

	public boolean isSupportCreateType() {
		return false;
	}

	public boolean isSupportAppendCustom() {
		return true;
	}

	protected DataType getCustomDataType() {
		return DataType.CURSOR;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		return null;
	}
	
	public boolean isSupportDynamicCreateType() {
		return false;
	}

	public String getDynamicCreateTypeSqlStatement(String name, JSONObject customJson) {
		return null;
	}

	public String getDynamicDropTypeSqlStatement(String name, JSONObject customJson) {
		return null;
	}

	public String getDynamicCreateTypeName(String name) {
		return null;
	}
}
