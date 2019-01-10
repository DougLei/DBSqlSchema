package com.sql.statement.model;

/**
 * 
 * @author DougLei
 */
public interface Basic {
	
	/**
	 * 设置主表的别名
	 * @param mainTableAlias
	 */
	Basic setMainTableAlias(String mainTableAlias);
	
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
