package com.sql.statement.basic.model.where;


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
			return AND;
		}
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	public String toString(){
		return "{"+sqlStatement+"}";
	}
}
