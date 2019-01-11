package com.sql.statement.model;

/**
 * 
 * @author DougLei
 */
public interface Basic {
	
	/**
	 * 获取对应的sql语句
	 * 如果没有任何语句，则返回null
	 * @return
	 */
	String getSqlStatement();
}
