package com.sql.impl.statement.complex.object.datatype.cursor;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.util.StrUtils;

public class ORACLE_CURSOR extends AbstractCustomDataType{
	private ORACLE_CURSOR(){}
	private static final ORACLE_CURSOR cursorDataType = new ORACLE_CURSOR();
	public static final ORACLE_CURSOR newInstance(){
		return cursorDataType;
	}
	
	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		StringBuilder sb = new StringBuilder(500);
		sb.append("cursor ").append(name).append(" is").append(newline());
		sb.append("(");
		if(StrUtils.isEmpty(customJson.getString("sqlId"))){
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(customJson.getJSONObject("sqlJson"));
			sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
		}else{
			sb.append(SqlStatementBuilderContext.buildSqlStatement(customJson.getString("sqlId")));
		}
		sb.append(")");
		return sb.toString();
	}

	public boolean isSupportCreateType() {
		return false;
	}

	public boolean isSupportAppendCustomSqlStatement() {
		return true;
	}

	protected DataType getCustomDataType() {
		return DataType.CURSOR;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		return null;
	}
}
