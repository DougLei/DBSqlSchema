package com.sql.statement.model.join;

import com.sql.statement.model.Basic;
import com.sql.statement.model.table.TableType;

/**
 * 
 * @author DougLei
 */
public interface Join extends Basic{
	
	/**
	 * 获取join表的类型
	 * @return
	 */
	TableType getTableType();

	/**
	 * 获取join别名
	 * @return
	 */
	String getAlias();
}
