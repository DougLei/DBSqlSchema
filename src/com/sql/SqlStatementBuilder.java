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
	 * 创建最终的sql语句
	 * @param json
	 * @return
	 */
	String buildSqlStatement();
}
