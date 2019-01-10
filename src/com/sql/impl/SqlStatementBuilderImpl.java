package com.sql.impl;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {

	protected JSONObject json;
	public void setJson(String json) {
		this.json = JSONObject.parseObject(json);
	}
	
	private boolean isValidation;
	/**验证json配置内容信息*/
	private void validJsonInfo(){
		if(isValidation){
			if(json == null){
				throw new NullPointerException("配置内容(json)不能为空");
			}
			if(json.get("id") == null || StrUtils.isEmpty(json.getString("id"))){
				throw new NullPointerException("配置内容(json)的属性[id]值不能为空");
			}
			if(json.get("name") == null || StrUtils.isEmpty(json.getString("name"))){
				throw new NullPointerException("配置内容(json)的属性[name]值不能为空");
			}
			if(json.get("content") == null || json.getJSONObject("content").size() == 0){
				throw new NullPointerException("配置内容(json)的属性content值不能为空");
			}
			json = json.getJSONObject("content");
			isValidation = true;
		}
	}
	
	public String getId() {
		validJsonInfo();
		return json.getString("id");
	}

	public String getName() {
		validJsonInfo();
		return json.getString("name");
	}
	
	public String buildSqlStatement() {
		validJsonInfo();
		return buildSql();
	}
	
	protected static final char newLine(){
		return nl;
	}
	private static final char nl= '\n';
	
	/**
	 * 创建sql语句
	 * @return
	 */
	protected abstract String buildSql();
}
