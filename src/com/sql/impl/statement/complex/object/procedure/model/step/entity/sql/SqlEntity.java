package com.sql.impl.statement.complex.object.procedure.model.step.entity.sql;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SqlEntity {
	private String sqlId;
	private JSONObject sqlJson;
	
	public SqlEntity(String sqlId, JSONObject sqlJson) {
		this.sqlId = sqlId;
		this.sqlJson = sqlJson;
	}

	public String getSqlStatement(){
		StringBuilder sb = new StringBuilder(500);
		if(StrUtils.notEmpty(sqlId)){
			sb.append(SqlStatementBuilderContext.buildSqlStatement(sqlId));
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(sqlJson);
			sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
		}
		return sb.toString();
	}
}
