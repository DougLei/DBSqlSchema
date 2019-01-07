package com.sql.enums;

/**
 * 数据库类型枚举对象
 * @author DougLei
 */
public enum DatabaseTypeEnum {
	
	SQLSERVER("sqlserver", "Microsoft SQL Server"),
	ORACLE("oracle", "Oracle");
	
	private String databaseType;
	private String productName;
	
	private DatabaseTypeEnum(String databaseType, String productName) {
		this.databaseType = databaseType;
		this.productName = productName;
	}
	
	public String getDatabaseType() {
		return databaseType;
	}
	public String getProductName() {
		return productName;
	}

	public String toString(){
		return "数据库产品名称: ["+productName+"], 数据库类型: ["+databaseType+"]";
	}
}
