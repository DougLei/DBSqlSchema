package com.sql.enums;

/**
 * sql语句类型
 * @author DougLei
 */
public enum SqlStatementType {
	
	SELECT("select"),
	INSERT("insert"),
	UPDATE("update"),
	DELETE("delete"),
	VIEW("view"),
	PROCEDURE("procedure");
	
	private String keyword;
	private SqlStatementType(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return keyword;
	}
}
