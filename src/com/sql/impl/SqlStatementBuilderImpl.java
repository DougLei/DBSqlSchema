package com.sql.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.SqlStatementType;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {
	protected final Map<String, SqlStatementBuilder> builderMap = new HashMap<String, SqlStatementBuilder>(32);
	
	protected SqlStatementInfoBuilder infoBuilder;
	protected JSONObject json;
	protected SqlStatementType sqlStatementType;
	
	protected String id;
	protected String description;
	protected JSONObject content;
	
	public void setSqlStatementInfoBuilder(SqlStatementInfoBuilder infoBuilder) {
		infoBuilder.validationInfo();
		this.infoBuilder = infoBuilder;
		this.json = infoBuilder.getJson();
		this.sqlStatementType = infoBuilder.getSqlStatementType();
		
		this.id = json.getString("id");
		this.description = json.getString("description");
		this.content = json.getJSONObject("content");
	}
	
	public SqlStatementInfoBuilder getSqlStatementInfoBuilder() {
		return infoBuilder;
	}
	public JSONObject getJson() {
		return json;
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public SqlStatementType getSqlStatementType() {
		return sqlStatementType;
	}
	
	protected static final char newLine(){
		return '\n';
	}
}
