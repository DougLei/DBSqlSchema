package com.sql.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.SqlStatementType;
import com.sql.impl.statement.model.function.FunctionImpl;
import com.sql.statement.model.function.Function;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {
	protected boolean isBuild;
	protected String sqlStatement;
	
	protected SqlStatementInfoBuilder infoBuilder;
	protected JSONObject json;
	protected SqlStatementType sqlStatementType;
	
	protected String id;
	protected String description;
	protected JSONObject content;
	
	public void clear(){
		if(content !=null && content.size() > 0){
			content.clear();
		}
		if(json !=null && json.size() > 0){
			json.clear();
		}
	}
	
	public void setSqlStatementInfoBuilder(SqlStatementInfoBuilder infoBuilder) {
		infoBuilder.validationInfo();
		this.infoBuilder = infoBuilder;
		this.json = infoBuilder.getJson();
		this.sqlStatementType = infoBuilder.getSqlStatementType();
		this.id = json.getString("id");
		this.description = json.getString("description");
		this.content = json.getJSONObject("content");
		
		SqlStatementBuilderContext.setConfJsonRefContent(json.getJSONArray("refContents"));
		SqlStatementBuilderContext.setSqlStatementBuilder(this);
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
	
	public String buildSqlStatement() {
		if(!isBuild && sqlStatement == null){
			sqlStatement = buildSql();
			isBuild = true;
		}
		return sqlStatement;
	}

	/**
	 * 实际build sql语句的方法
	 * @return
	 */
	protected abstract String buildSql();

	protected static final char newline(){
		return '\n';
	}
	
	/**
	 * 获取function属性对象
	 * @param function
	 * @return
	 */
	protected Function getFunction(JSONObject function){
		if(function != null){
			Object name = function.get("name");
			JSONArray parameters = function.getJSONArray("parameters");
			if(name != null && parameters != null && parameters.size() > 0){
				String[] ps = new String[parameters.size()];
				for(int i=0;i<parameters.size();i++){
					ps[i] = parameters.getString(i);
				}
				return FunctionImpl.newInstance(name.toString(), ps);
			}
		}
		return null;
	}
}
