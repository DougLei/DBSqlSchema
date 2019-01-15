package com.sql.impl.statement.basic.model.values;

import com.sql.statement.basic.model.function.Function;

/**
 * 
 * @author DougLei
 */
public class ValuesEntity {
	protected String value;
	protected Function function;
	
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
