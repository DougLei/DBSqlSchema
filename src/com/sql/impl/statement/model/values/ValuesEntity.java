package com.sql.impl.statement.model.values;

import com.sql.statement.model.function.Function;

/**
 * 
 * @author DougLei
 */
public class ValuesEntity {
	private String value;
	private Function function;
	
	public ValuesEntity(String value, Function function) {
		this.value = value;
		this.function = function;
	}
	
	public String getSqlStatement(){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = value;
		}
		return sqlStatement;
	}
}
