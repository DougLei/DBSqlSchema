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
	private String desc;
	private SqlStatementType(String keyword) {
		this(keyword, null);
	}
	private SqlStatementType(String keyword, String desc) {
		this.keyword = keyword;
		this.desc = desc;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public String getDesc() {
		return desc;
	}

	public String toString(){
		return "{" + keyword + (desc==null?"":"," + desc) + "}";
	}
}
