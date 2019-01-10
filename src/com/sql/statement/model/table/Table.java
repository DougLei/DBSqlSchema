package com.sql.statement.model.table;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public interface Table extends Basic{

	/**
	 * 获取表的类型
	 * @return
	 */
	TableType getTableType();

	/**
	 * 获取别名
	 * @return
	 */
	String getAlias();
}
