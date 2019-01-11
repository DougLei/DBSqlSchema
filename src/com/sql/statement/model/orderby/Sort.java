package com.sql.statement.model.orderby;

import java.util.Arrays;

import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public enum Sort {
	ASC("asc"),
	DESC("desc");
	
	private String sqlStatement;
	private Sort(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public static Sort toValue(String str){
		try {
			if(StrUtils.isEmpty(str)){
				return ASC;
			}
			return Sort.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(Sort.values())+"]");
		}
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	
	public String toString(){
		return "{"+sqlStatement+"}";
	}
}
