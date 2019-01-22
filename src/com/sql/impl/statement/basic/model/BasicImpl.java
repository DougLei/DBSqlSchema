package com.sql.impl.statement.basic.model;

import com.sql.statement.basic.model.Basic;

/**
 * 
 * @author DougLei
 */
public abstract class BasicImpl implements Basic {
	private String sqlStatement;
	private boolean isInitialize;
	
	public String getSqlStatement() {
		if(!isInitialize && sqlStatement == null){
			sqlStatement = processSqlStatement();
			isInitialize = true;
		}
		if(sqlStatement != null && sqlStatement.trim().length() == 0){
			sqlStatement = null;
		}
		return sqlStatement;
	}
	
	/**
	 * 处理sql语句
	 * 如果没有就返回null
	 * @return
	 */
	protected abstract String processSqlStatement();
}
