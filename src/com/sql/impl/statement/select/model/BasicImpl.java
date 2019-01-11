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
			if((sqlStatement = processSqlStatement()) == null){
				sqlStatement = "";
			}
		}
		return sqlStatement;
	}
	
	/**
	 * 处理sql语句
	 * @return
	 */
	protected abstract String processSqlStatement();
}
