package com.sql.enums;

import com.sql.impl.SqlStatementBuilderContext;

/**
 * 
 * @author DougLei
 */
public enum DataType {
	VARCHAR("varchar", "varchar2"),
	
	TABLE("table", ""),
	
	USER_DEFINED();// 用户自定义类型
	
	private String sqlDataType;
	private String oracleDataType;
	private DataType() {
	}
	private DataType(String sqlDataType, String oracleDataType) {
		this.sqlDataType = sqlDataType;
		this.oracleDataType = oracleDataType;
	}

	public String getDataType(){
		if(this == USER_DEFINED){
			return null;
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlDataType;
			case ORACLE:
				return oracleDataType;
		}
		throw new IllegalArgumentException("DataType.getDataType出现异常");
	}
	
	/**
	 * 是否是表类型
	 * @param dataType
	 * @return
	 */
	public boolean isTableType(){ 
		return this == TABLE;
	}
	
	public static DataType toValue(String str){
		str = str.trim().toUpperCase();
		for(DataType dt : DataType.values()){
			if(dt.name().equals(str)){
				return dt;
			}
		}
		return USER_DEFINED;
	}
	
	public String toString(){
		return "{name: "+name()+", sqlserver: "+sqlDataType+", oracle: "+oracleDataType+"}";
	}
}
