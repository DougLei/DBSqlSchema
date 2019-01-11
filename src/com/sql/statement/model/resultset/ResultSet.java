package com.sql.statement.model.resultset;

import com.sql.statement.model.Basic;

/**
 * resultSet 节点
 * @author DougLei
 */
public interface ResultSet extends Basic{
	
	/**
	 * 设置函数
	 * @param functionName
	 * @param parameters
	 */
	void setFunction(String functionName, String[] parameters);
}
