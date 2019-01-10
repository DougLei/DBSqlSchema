package com.sql;



/**
 * sql语句 builder
 * @author DougLei
 */
public interface SqlStatementBuilder {
	
	/**
	 * 设置json配置内容
	 * @param json
	 * @return
	 */
	void setJson(String json);
	
	/**
	 * 获取json配置内容
	 * @return
	 */
	String getJson();
	
	/**
	 * 创建sql语句
	 * @param json
	 * @return
	 */
	String buildSqlStatement();
}
