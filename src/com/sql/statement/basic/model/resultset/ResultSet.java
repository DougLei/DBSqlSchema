package com.sql.statement.basic.model.resultset;

import com.sql.statement.BasicModel;

/**
 * resultSet 节点
 * @author DougLei
 */
public interface ResultSet extends BasicModel{

	/**
	 * 获取查询结果集的列名
	 * @return
	 */
	String getResultSetColumnName();
}
