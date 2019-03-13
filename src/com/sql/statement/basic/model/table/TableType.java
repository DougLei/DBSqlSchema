package com.sql.statement.basic.model.table;

import java.util.Arrays;

/**
 * 表类型
 * @author DougLei
 */
public enum TableType {
	TABLE, // 表
	PARAMETER, // 参数
	FUNCTION, // 函数
	SUB_QUERY; // 子查询
	
	public static TableType toValue(String str){
		try {
			return TableType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(TableType.values())+"]");
		}
	}
	
	public String toString(){
		return name();
	}
}
