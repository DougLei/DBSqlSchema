package com.sql.enums;

import java.util.Arrays;

/**
 * 数据库类型枚举对象
 * @author DougLei
 */
public enum DatabaseType {
	
	SQLSERVER("sqlserver", "Microsoft SQL Server"),
	ORACLE("oracle", "Oracle");
	
	private String databaseType;
	private String databaseTypeUpperCase;
	private String productName;
	
	private DatabaseType(String databaseType, String productName) {
		this.databaseType = databaseType;
		this.databaseTypeUpperCase = databaseType.toUpperCase();
		this.productName = productName;
	}
	
	public static DatabaseType toValue(String str){
		try {
			return DatabaseType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(DatabaseType.values())+"]");
		}
	}
	
	public String getDatabaseType() {
		return databaseType;
	}
	public String getDatabaseTypeUpperCase() {
		return databaseTypeUpperCase;
	}
	public String getProductName() {
		return productName;
	}

	public String toString(){
		return name();
	}
}
