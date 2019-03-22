package com.sql.impl.statement.basic.model.function.builtin.substring.db;

import com.sql.impl.statement.basic.model.function.builtin.substring.Substring;

/**
 * 
 * @author DougLei
 */
public class ORACLE_Substring extends Substring{

	public String getSqlStatement() {
		if(subLength == 0){
			return "substr("+parameterSqlStatement+", "+subIndex+")";
		}else{
			return "substr("+parameterSqlStatement+", "+subIndex+", " + subLength+")";
		}
	}
}
