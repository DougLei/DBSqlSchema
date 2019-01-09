package com.sql.impl;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {

	protected JSONObject json;
	public void setJson(String json) {
		this.json = JSONObject.parseObject(json);
	}
	
	/**验证json配置内容不能为空*/
	private void validJsonNotNull(){
		if(json == null){
			throw new NullPointerException("配置内容(json)不能为空");
		}
	}
	
	public String getId() {
		validJsonNotNull();
		if(json.get("id") == null){
			throw new NullPointerException("配置内容(json)的属性id值不能为空");
		}
		return json.getString("id");
	}

	public String getName() {
		validJsonNotNull();
		if(json.get("name") == null){
			throw new NullPointerException("配置内容(json)的属性name值不能为空");
		}
		return json.getString("name");
	}

	public String buildSqlStatement() {
		validJsonNotNull();
		return buildSql();
	}
	
	/**
	 * 创建sql语句
	 * @return
	 */
	protected abstract String buildSql();
}
