package com.sql.statement.complex.select;

import java.util.Arrays;

/**
 * union类型
 * @author DougLei
 */
public enum UnionType {
	UNION("union"),
	UNION_ALL("union all");
	
	private String sqlStatement;
	private UnionType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	public static UnionType toValue(String str){
		try {
			return UnionType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(UnionType.values())+"]");
		}
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public String toString(){
		return "{" + sqlStatement + "}";
	}
}
