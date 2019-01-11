package com.sql.statement.model.where;

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

	public String getSqlStatement() {
		return sqlStatement;
	}
	public String toString(){
		return sqlStatement;
	}
}
