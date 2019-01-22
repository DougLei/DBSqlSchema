package com.sql.statement;

/**
 * 
 * @author DougLei
 */
public interface BasicModel {
	
	/**
	 * 获取对应的sql语句
	 * 如果没有任何语句，则返回null
	 * @return
	 */
	String getSqlStatement();
}
