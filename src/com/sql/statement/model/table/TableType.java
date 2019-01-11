package com.sql.statement.model.table;

import java.util.Arrays;

/**
 * 表类型
 * @author DougLei
 */
public enum TableType {
	TABLE("table"), // 表
	SUB_QUERY("subQuery"); // 子查询
	
	private String type;
	private TableType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public static TableType toValue(String str){
		try {
			return TableType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(TableType.values())+"]");
		}
	}
	
	public String toString(){
		return "{" + type + "}";
	}
}
