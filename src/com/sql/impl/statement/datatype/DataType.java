package com.sql.impl.statement.datatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;

/**
 * 
 * @author DougLei
 */
public enum DataType {
	VARCHAR("varchar", "varchar2"),
	
	TABLE(),
	CUSTOM();// 用户自定义类型
	
	private String sqlserverDataType;
	private String oracleDataType;
	
	private CustomDataType sqlserverCustomDataType;
	private CustomDataType oracleCustomDataType;
	
	private DataType() {
	}
	private DataType(String sqlserverDataType, String oracleDataType) {
		this.sqlserverDataType = sqlserverDataType;
		this.oracleDataType = oracleDataType;
	}
	private DataType(CustomDataType sqlserverCustomDataType, CustomDataType oracleCustomDataType) {
		this.sqlserverCustomDataType = sqlserverCustomDataType;
		this.oracleCustomDataType = oracleCustomDataType;
	}
	
	public static DataType toValue(String str){
		str = str.trim().toUpperCase();
		for(DataType dt : DataType.values()){
			if(dt.name().equals(str)){
				return dt;
			}
		}
		return CUSTOM;
	}

	/**
	 * 是否是自定义类型
	 * @param dataType
	 * @return
	 */
	public boolean isCustomType(){ 
		return this == TABLE || this == CUSTOM;
	}
	
	public String getDataType(){
		if(this == CUSTOM){
			return null;
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverDataType;
			case ORACLE:
				return oracleDataType;
		}
		throw new IllegalArgumentException("DataType.getDataType出现异常");
	}
	
	public String getCustomSqlStatement(JSONObject customJson) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverCustomDataType.getCustomSqlStatement(customJson);
			case ORACLE:
				return oracleCustomDataType.getCustomSqlStatement(customJson);
		}
		throw new IllegalArgumentException("DataType.getCustomSqlStatement出现异常");
	}
}
