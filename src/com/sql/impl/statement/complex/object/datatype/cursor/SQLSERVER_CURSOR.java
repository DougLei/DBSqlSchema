package com.sql.impl.statement.complex.object.datatype.cursor;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.util.StrUtils;

public class SQLSERVER_CURSOR extends AbstractCustomDataType{
	private SQLSERVER_CURSOR(){}
	private static final SQLSERVER_CURSOR cursorDataType = new SQLSERVER_CURSOR();
	public static final SQLSERVER_CURSOR newInstance(){
		return cursorDataType;
	}
	
	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		StringBuilder sb = new StringBuilder(500);
		sb.append(name).append(" cursor for").append(newline());
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
