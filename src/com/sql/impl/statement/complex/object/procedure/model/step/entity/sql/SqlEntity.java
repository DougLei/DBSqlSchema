package com.sql.impl.statement.complex.object.procedure.model.step.entity.sql;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;

/**
 * 
 * @author DougLei
 */
public class SqlEntity extends AbstractEntity{
	private String sqlId;
	private JSONObject sqlJson;
	
	public SqlEntity(String sqlId, JSONObject sqlJson) {
		this.sqlId = sqlId;
		this.sqlJson = sqlJson;
	}

	public String getSqlStatement(){
		StringBuilder sb = new StringBuilder(SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson));
		return sb.toString();
	}
}
