package com.sql.statement.insert;

import java.util.List;

import com.sql.statement.model.values.Values;

/**
 * insert sql语句builder 接口
 * @author DougLei
 */
public interface InsertSqlStatementBuilder {
	
	/**
	 * 获取表名
	 * @return
	 */
	String getTableName();
	
	/**
	 * 获取要插入的列名集合
	 * @return
	 */
	List<String> getColumnNames();
	
	/**
	 * 获取values后的内容对象
	 * @return
	 */
	Values getValues();
}
