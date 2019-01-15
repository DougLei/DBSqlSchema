package com.sql.statement.basic.delete;

import java.util.List;

import com.sql.statement.basic.model.where.WhereGroup;


/**
 * delete sql语句builder 接口
 * @author DougLei
 */
public interface DeleteSqlStatementBuilder {
	
	/**
	 * 获取表名
	 * @return
	 */
	String getTableName();
	
	/**
	 * 获取whereGroup语句对象集合
	 * @return
	 */
	List<WhereGroup> getWhereGroupList();
}
