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
				return (isInversion?"not ":"") + "exists ( " + sql + " )";
			case ORACLE:
				// oracle特殊语法：  q'/中间是字符串内容，即使包括'也是算做字符串的一部分，而不是关键字/'，例如: select q'/I'm Douglei/' from dual
				return "ifexists_function( q'/" + sql + "/' )" + (isInversion?"=0":"=1");
		}
		return null;
	}
}
