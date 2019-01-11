package com.sql.statement.model.resultset;

import com.sql.statement.model.Basic;

/**
 * resultSet 节点
 * @author DougLei
 */
public interface ResultSet extends Basic{
	
	/**
	 * 添加一个函数
	 * @param functionName
	 * @param parameters
	 */
	void addFunction(String functionName, String... parameters);
}
