package com.sql.impl.statement.select.model;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public abstract class BasicImpl implements Basic {
	protected String sqlStatement;
	
	public String getSqlStatement() {
		if(sqlStatement == null){
			processSqlStatement();
		}
		return sqlStatement;
	}
	
	/**
	 * 处理sql语句
	 */
	protected abstract void processSqlStatement();
}
