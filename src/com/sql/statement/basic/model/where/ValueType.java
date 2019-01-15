package com.sql.statement.basic.model.where;

import java.util.Arrays;

/**
 * 
 * @author DougLei
 */
public enum ValueType {
	VALUE("value"), // 值
	SUB_QUERY("sub_query"); // 子查询
	
	private String type;
	private ValueType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public static ValueType toValue(String str){
		try {
			return ValueType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(ValueType.values())+"]");
		}
	}
	
	public String toString(){
		return "{" + type + "}";
	}
}
