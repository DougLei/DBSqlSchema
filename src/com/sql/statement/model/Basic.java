package com.sql.statement.model;

/**
 * 
 * @author DougLei
 */
public interface Basic {
	
	/**
	 * 添加一个函数
	 * @param functionName
	 * @param parameters
	 */
	void addFunction(String functionName, String... parameters);
	
	/**
	 * 获取对应的sql语句
	 * @return
	 */
	String getSqlStatement();
}
