package com.sql.statement.basic.model.where;

import com.sql.statement.BasicModel;

/**
 * 
 * @author DougLei
 */
public interface Where extends BasicModel {
	
	/**
	 * 获取与下一个条件的逻辑操作符
	 * @return
	 */
	String getNextLogicOperator();
}
