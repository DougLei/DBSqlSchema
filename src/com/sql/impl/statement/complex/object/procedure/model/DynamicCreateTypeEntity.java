package com.sql.impl.statement.complex.object.procedure.model;

/**
 * 
 * @author DougLei
 */
public class DynamicCreateTypeEntity {
	private String dynamicCreateTypeName;
	private String dynamicCreateTypeSqlStatement;
	private String dynamicDropTypeSqlStatement;
	
	public DynamicCreateTypeEntity(String dynamicCreateTypeName,
			String dynamicCreateTypeSqlStatement,
			String dynamicDropTypeSqlStatement) {
		this.dynamicCreateTypeName = dynamicCreateTypeName;
		this.dynamicCreateTypeSqlStatement = dynamicCreateTypeSqlStatement;
		this.dynamicDropTypeSqlStatement = dynamicDropTypeSqlStatement;
	}
	
	public String getDynamicCreateTypeName() {
		return dynamicCreateTypeName;
	}
	public void setDynamicCreateTypeName(String dynamicCreateTypeName) {
		this.dynamicCreateTypeName = dynamicCreateTypeName;
	}
	public String getDynamicCreateTypeSqlStatement() {
		return dynamicCreateTypeSqlStatement;
	}
	public void setDynamicCreateTypeSqlStatement(
			String dynamicCreateTypeSqlStatement) {
		this.dynamicCreateTypeSqlStatement = dynamicCreateTypeSqlStatement;
	}
	public String getDynamicDropTypeSqlStatement() {
		return dynamicDropTypeSqlStatement;
	}
	public void setDynamicDropTypeSqlStatement(String dynamicDropTypeSqlStatement) {
		this.dynamicDropTypeSqlStatement = dynamicDropTypeSqlStatement;
	}
}
