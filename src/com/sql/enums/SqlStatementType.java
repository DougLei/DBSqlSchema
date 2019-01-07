package com.sql.enums;

/**
 * 
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
