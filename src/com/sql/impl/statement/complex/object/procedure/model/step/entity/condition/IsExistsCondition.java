package com.sql.impl.statement.complex.object.procedure.model.step.entity.condition;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;

/**
 * is exists 条件
 * @author DougLei
 */
public class IsExistsCondition {
	private boolean isInversion;// 是否取反
	private String sqlId;
	private JSONObject sqlJson;
	
	public IsExistsCondition(JSONObject isExistsConditionJson) {
		isInversion = isExistsConditionJson.getBooleanValue("isInversion");
		sqlId = isExistsConditionJson.getString("sqlId");
		sqlJson = isExistsConditionJson.getJSONObject("sqlJson");
	}

	public String getSqlStatement() {
		String sql = SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson);
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				sql = "exists ( " + sql + " )";
				break;
			case ORACLE:
				sql = "ifexists ( '" + sql + "' )";
				break;
		}
		if(isInversion){
			sql = "not " + sql;
		}
		return sql;
	}
}
