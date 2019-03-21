package com.sql.statement.basic.delete;

/**
 * delete sql语句builder 接口
 * @author DougLei
 */
public interface DeleteSqlStatementBuilder {
	
	/**
	 * 获取表名
	 * @return
	 */
	String getTable();
	
	/**
	 * 获取where 语句
	 * @return
	 */
	String getWhereSqlStatement();
}
