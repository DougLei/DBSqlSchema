package com.sql.statement.basic.model.where;


/**
 * 
 * @author DougLei
 */
public interface Value{
	
	/**
	 * 获取值语句数组
	 * <p>因为有between and这种</p>
	 * @return
	 */
	public String[] getSqlStatements();
	
	/**
	 * 是否是null值
	 * @return
	 */
	public boolean isNullValueType();
}
