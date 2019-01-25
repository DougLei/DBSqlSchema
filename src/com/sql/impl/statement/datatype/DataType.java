package com.sql.impl.statement.datatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.datatype.table.ORACLE_TABLE;
import com.sql.impl.statement.datatype.table.SQLSERVER_TABLE;
import com.sql.impl.statement.datatype.tmptable.ORACLE_TMPTABLE;
import com.sql.impl.statement.datatype.tmptable.SQLSERVER_TMPTABLE;

/**
 * 
 * @author DougLei
 */
public enum DataType {
	VARCHAR("varchar", "varchar2"),
	
	TABLE(SQLSERVER_TABLE.newInstance(), ORACLE_TABLE.newInstance()),
	TMP_TABLE(SQLSERVER_TMPTABLE.newInstance(), ORACLE_TMPTABLE.newInstance());
	
	private String sqlserverDataType;
	private String oracleDataType;
	
	private CustomDataType sqlserverCustomDataType;
	private CustomDataType oracleCustomDataType;
	
	private boolean isBaseType;
	
	private DataType(String sqlserverDataType, String oracleDataType) {
		this.isBaseType = true;
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
		throw new IllegalArgumentException("程序目前不支持["+str+"]数据类型");
	}

	/**是否是基础类型*/
	public boolean isBaseType(){ 
		return isBaseType;
	}
	
	/**
	 * 获取基础类型的类型字符串值
	 * @return
	 */
	public String getDataType(){
		if(!isBaseType()){
			throw new IllegalAccessError("非基础数据库类型，无法获取的dataType值");
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
	
	/**
	 * 获取创建类型的sql语句
	 * @param customJson
	 * @return
	 */
	public String getCreateTypeSqlStatement(JSONObject customJson) {
		if(isBaseType()){
			throw new IllegalAccessError("基础数据库类型，无法获取创建类型的sql语句");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverCustomDataType.getCreateTypeSqlStatement(customJson);
			case ORACLE:
				return oracleCustomDataType.getCreateTypeSqlStatement(customJson);
		}
		throw new IllegalArgumentException("DataType.getCreateTypeSqlStatement出现异常");
	}
	
	/**
	 * 获取要追加到整个sql语句中，要呈现的sql语句内容
	 * @param customJson
	 * @return
	 */
	public String getAppendCustomSqlStatement(JSONObject customJson) {
		if(isBaseType()){
			throw new IllegalAccessError("基础数据库类型，无法获取创建类型的sql语句");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverCustomDataType.getAppendCustomSqlStatement(customJson);
			case ORACLE:
				return oracleCustomDataType.getAppendCustomSqlStatement(customJson);
		}
		throw new IllegalArgumentException("DataType.getAppendCustomSqlStatement出现异常");
	}
	
	/**
	 * 是否支持创建类型
	 * @return
	 */
	public boolean isSupportCreateType() {
		if(!isBaseType()){
			DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
			switch(dbType){
				case SQLSERVER:
					return sqlserverCustomDataType.isSupportCreateType();
				case ORACLE:
					return oracleCustomDataType.isSupportCreateType();
			}
			throw new IllegalArgumentException("DataType.isSupportCreateType出现异常");
		}
		return false;
	}
	
}
