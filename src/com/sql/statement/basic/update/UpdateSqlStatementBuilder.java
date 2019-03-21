package com.sql.statement.basic.update;

import java.util.List;
import com.sql.statement.basic.model.set.Set;

/**
 * update sql语句builder 接口
 * @author DougLei
 */
public interface UpdateSqlStatementBuilder {

	/**
	 * 获取表名
	 * @return
	 */
	String getTable();
	
	/**
	 * 获取set 语句对象集合
	 * @return
	 */
	List<Set> getSetList();
}
