package com.sql.statement.model.where;

import java.util.Arrays;

/**
 * 数据操作类型
 * @author DougLei
 */
public enum DataOperatorType {
	
	BTN(""),
	CTN(""),
	IN(""),
	EQ(""),
	NE(""),
	GE(""),
	GT(""),
	LE(""),
	LT("");
	
	private String sqlStatement;
	private DataOperatorType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	public static DataOperatorType toValue(String str){
		try {
			return DataOperatorType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(DataOperatorType.values())+"]");
		}
	}

	public String getSqlStatement(String... parameters) {
		return sqlStatement;
	}
	public String toString(){
		return "{"+sqlStatement+"}";
	}
}
