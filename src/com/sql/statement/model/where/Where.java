package com.sql.statement.model.where;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public interface Where extends Basic {
	
	/**
	 * 获取与下一个条件的逻辑操作符
	 * @return
	 */
	String getNextLogicOperator();
}
