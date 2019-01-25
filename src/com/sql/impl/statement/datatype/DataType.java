package com.sql.impl.statement.datatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.datatype.table.ORACLE_TABLE;
import com.sql.impl.statement.datatype.table.SQLSERVER_TABLE;

/**
 * 
 * @author DougLei
 */
public enum DataType {
	VARCHAR("varchar", "varchar2"),
	
	TABLE("table", SQLSERVER_TABLE.newInstance(), "object", ORACLE_TABLE.newInstance());
	
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
	private DataType(String sqlserverDataType, CustomDataType sqlserverCustomDataType, String oracleDataType, CustomDataType oracleCustomDataType) {
		this(sqlserverDataType, oracleDataType);
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
		throw new IllegalArgumentException("程序目前不支持["+str+"]数据类型");
	}

	/**
	 * 是否是自定义类型
	 * @param dataType
	 * @return
	 */
	public boolean isCustomType(){ 
		return this == TABLE;
	}
	
	public String getDataType(){
		if(isCustomType()){
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
		if(customJson == null || customJson.size() == 0){
			throw new NullPointerException("获取自定义的sql语句时，传入的配置信息为空");
		}
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
