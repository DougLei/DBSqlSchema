package com.sql.statement.model.join;

import java.util.Arrays;

/**
 * join的类型
 * @author DougLei
 */
public enum JoinType {
	INNER("inner join"),
	LEFT("left join"),
	RIGHT("right join"),
	FULL("full join");
	
	private String sqlStatement;
	private JoinType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	public static JoinType toValue(String str){
		try {
			return JoinType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(JoinType.values())+"]");
		}
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	public String toString() {
		return "{"+sqlStatement+"}";
	}
}