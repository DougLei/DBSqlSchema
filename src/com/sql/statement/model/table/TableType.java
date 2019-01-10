package com.sql.statement.model.table;

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

	public String toString(){
		return "{" + type + "}";
	}
}
