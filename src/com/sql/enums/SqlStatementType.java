package com.sql.enums;

import java.util.Arrays;

/**
 * sql语句类型
 * @author DougLei
 */
public enum SqlStatementType {
	
	SELECT("select"),
	INSERT("insert"),
	UPDATE("update"),
	DELETE("delete"),
	
	COMBINATION_SELECT("combinationSelect"),
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
	
	public static SqlStatementType toValue(String str){
		try {
			return SqlStatementType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(SqlStatementType.values())+"]");
		}
	}

	public String toString(){
		return "{" + keyword + (desc==null?"":"," + desc) + "}";
	}
}
