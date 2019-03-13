package com.sql.impl.statement.basic.model.values;

import com.sql.impl.statement.Tools;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ValuesEntity {
	protected String value;
	protected String paramName;
	protected Function function;
	
	public ValuesEntity(String value, String paramName, Function function) {
		this.value = StrUtils.isEmpty(value)?null:value;
		this.paramName = StrUtils.isEmpty(paramName)?null:paramName;
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
		if(sqlStatement == null){
			sqlStatement = Tools.getName(null, paramName);
		}
		return sqlStatement;
	}
}
