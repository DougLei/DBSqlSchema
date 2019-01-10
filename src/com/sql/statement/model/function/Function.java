package com.sql.statement.model.function;


/**
 * 数据库函数
 * @author DougLei
 */
public interface Function{
	
	/**
	 * 获取对应的sql语句
	 * @param mainTableAlias 表别名
	 * @param columnName 列名 
	 * @param columnAlias 列别名
	 * @return
	 */
	String getSqlStatement(String mainTableAlias, String columnName, String columnAlias);
}
