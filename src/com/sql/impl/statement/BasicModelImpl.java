package com.sql.impl.statement;

import com.sql.statement.BasicModel;

/**
 * 
 * @author DougLei
 */
public abstract class BasicModelImpl implements BasicModel {
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
