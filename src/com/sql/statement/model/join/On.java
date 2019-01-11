package com.sql.statement.model.join;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public interface On extends Basic{

	/**
	 * 获取与下一个条件的逻辑操作符
	 * @return
	 */
	String getNextLogicOperator();
}
