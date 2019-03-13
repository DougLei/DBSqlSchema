package com.sql.impl.statement.basic.model.function.builtin.forxmlpath;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ForXmlPath implements BuiltinFunction{
	protected String rootNodeName;
	
	private String sqlId;
	private JSONObject sqlJson;
	
	public BuiltinFunction init(JSONObject confJson) {
		sqlId = confJson.getString("sqlId");
		sqlJson = confJson.getJSONObject("sqlJson");
		rootNodeName = confJson.getString("rootNodeName");
		if(StrUtils.isEmpty(rootNodeName)){
			reInitRootNodeName();
		}
		return this;
	}

	public String getSqlStatement() {
		return installSqlStatement(SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson));
	}
	
	/**
	 * 重新初始化rootNodeName属性值
	 */
	protected abstract void reInitRootNodeName();
	/**
	 * 组装sql语句
	 * @param sqlContent 主体的sql语句，子类实现如何用函数包裹起来
	 * @return
	 */
	protected abstract String installSqlStatement(String sqlContent);
	
	public String getFunctionName() {
		return "_forxmlpath";
	}
}
