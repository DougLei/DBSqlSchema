package com.sql.statement.model.where;

import java.util.Arrays;

/**
 * 逻辑操作类型
 * @author DougLei
 */
public enum LogicOperatorType {
	AND("and"),
	OR("or");
	
	private String sqlStatement;
	private LogicOperatorType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public static LogicOperatorType toValue(String str){
		try {
			return LogicOperatorType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(LogicOperatorType.values())+"]");
		}
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	public String toString(){
		return "{"+sqlStatement+"}";
	}
}
