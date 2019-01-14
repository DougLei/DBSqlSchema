package com.sql.statement.basic.model.values;

import java.util.Arrays;

/**
 * insert into values 后的类型
 * @author DougLei
 */
public enum ValuesType {
	VALUE("value"), // 值
	SUB_QUERY("sub_query"); // 子查询
	
	private String type;
	private ValuesType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public static ValuesType toValue(String str){
		try {
			return ValuesType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(ValuesType.values())+"]");
		}
	}
	
	public String toString(){
		return "{" + type + "}";
	}
}
