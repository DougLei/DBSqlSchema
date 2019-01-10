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
	 * 获取配置文件的id值
	 * @return
	 */
	String getId();
	
	/**
	 * 获取配置文件的name值
	 * @return
	 */
	String getName();
	
	/**
	 * 创建sql语句
	 * @param json
	 * @return
	 */
	String buildSqlStatement();
}
