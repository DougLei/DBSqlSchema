package com.sql.impl.statement.basic.model.groupby;

import com.sql.statement.basic.model.function.Function;

/**
 * 
 * @author DougLei
 */
public class GroupByColumnEntity {
	private String columnName;
	private Function function;
	
	public GroupByColumnEntity(String columnName, Function function) {
		this.columnName = columnName;
		this.function = function;
	}
	
	public String getSqlStatement(){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = columnName;
		}
		return sqlStatement;
	}
}
