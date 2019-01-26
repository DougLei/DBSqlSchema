package com.sql.impl.statement.complex.object.datatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_BLOB;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_CHAR;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_CLOB;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_DATE;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_INT;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_LONG;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_NCHAR;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_NVARCHAR2;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_SHORT;
import com.sql.impl.statement.complex.object.datatype.base.oracle.ORACLE_VARCHAR2;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_CHAR;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_DATE;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_DATETIME;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_IMAGE;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_INT;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_LONG;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_NCHAR;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_NVARCHAR;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_SHORT;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_TEXT;
import com.sql.impl.statement.complex.object.datatype.base.sqlserver.SQLSERVER_VARCHAR;
import com.sql.impl.statement.complex.object.datatype.cursor.ORACLE_CURSOR;
import com.sql.impl.statement.complex.object.datatype.cursor.SQLSERVER_CURSOR;
import com.sql.impl.statement.complex.object.datatype.table.ORACLE_TABLE;
import com.sql.impl.statement.complex.object.datatype.table.SQLSERVER_TABLE;
import com.sql.impl.statement.complex.object.datatype.tmptable.ORACLE_TMPTABLE;
import com.sql.impl.statement.complex.object.datatype.tmptable.SQLSERVER_TMPTABLE;

/**
 * 
 * @author DougLei
 */
public enum DataType {
	VARCHAR(SQLSERVER_VARCHAR.newInstance(), ORACLE_VARCHAR2.newInstance()),
	NVARCHAR(SQLSERVER_NVARCHAR.newInstance(), ORACLE_NVARCHAR2.newInstance()),
	CHAR(SQLSERVER_CHAR.newInstance(), ORACLE_CHAR.newInstance()),
	NCHAR(SQLSERVER_NCHAR.newInstance(), ORACLE_NCHAR.newInstance()),
	
	DATE(SQLSERVER_DATE.newInstance(), ORACLE_DATE.newInstance()),
	DATETIME(SQLSERVER_DATETIME.newInstance(), ORACLE_DATE.newInstance()),
	
	INTEGER(SQLSERVER_INT.newInstance(), ORACLE_INT.newInstance()),
	SHORT(SQLSERVER_SHORT.newInstance(), ORACLE_SHORT.newInstance()),
	LONG(SQLSERVER_LONG.newInstance(), ORACLE_LONG.newInstance()),
//	DOUBLE(),
	
//	DOUBLE("decimal", "number"),
	
	CLOB(SQLSERVER_TEXT.newInstance(), ORACLE_CLOB.newInstance()),
	BLOB(SQLSERVER_IMAGE.newInstance(), ORACLE_BLOB.newInstance()),
	TEXT(SQLSERVER_TEXT.newInstance(), ORACLE_CLOB.newInstance()),
	IMAGE(SQLSERVER_IMAGE.newInstance(), ORACLE_BLOB.newInstance()),
	
	// -----------------------------------------------------------------------------------
	TABLE(SQLSERVER_TABLE.newInstance(), ORACLE_TABLE.newInstance()),
	TMP_TABLE(SQLSERVER_TMPTABLE.newInstance(), ORACLE_TMPTABLE.newInstance()),
	CURSOR(SQLSERVER_CURSOR.newInstance(), ORACLE_CURSOR.newInstance()),
	;
	
	private BaseDataType sqlserverDataType;
	private BaseDataType oracleDataType;
	
	private CustomDataType sqlserverCustomDataType;
	private CustomDataType oracleCustomDataType;
	
	private boolean isBaseType;
	
	private DataType(BaseDataType sqlserverDataType, BaseDataType oracleDataType) {
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
	public String getBaseDataType(){
		if(!isBaseType()){
			throw new IllegalAccessError("非基础数据库类型，无法获取的dataType值");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverDataType.getSqlStatement();
			case ORACLE:
				return oracleDataType.getSqlStatement();
		}
		throw new IllegalArgumentException("DataType.getDataType出现异常");
	}
	
	/**
	 * 计算基础类型的长度
	 * @param length
	 * @return
	 */
	public int calcLength(int length){
		if(!isBaseType()){
			throw new IllegalAccessError("非基础数据库类型，无法计算的length值");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverDataType.calcLength(length);
			case ORACLE:
				return oracleDataType.calcLength(length);
		}
		throw new IllegalArgumentException("DataType.calcLength出现异常");
	}
	
	/**
	 * 计算基础类型的精度
	 * @param length
	 * @return
	 */
	public int calcPrecision(int precision){
		if(!isBaseType()){
			throw new IllegalAccessError("非基础数据库类型，无法计算的precision值");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverDataType.calcPrecision(precision);
			case ORACLE:
				return oracleDataType.calcPrecision(precision);
		}
		throw new IllegalArgumentException("DataType.calcPrecision出现异常");
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
	 * @param name
	 * @param customJson
	 * @return
	 */
	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		if(isBaseType()){
			throw new IllegalAccessError("基础数据库类型，无法获取创建类型的sql语句");
		}
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return sqlserverCustomDataType.getAppendCustomSqlStatement(name, customJson);
			case ORACLE:
				return oracleCustomDataType.getAppendCustomSqlStatement(name, customJson);
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
	
	/**
	 * 是否支持追加自定义的sql语句
	 * @return
	 */
	public boolean isSupportAppendCustomSqlStatement() {
		if(!isBaseType()){
			DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
			switch(dbType){
				case SQLSERVER:
					return sqlserverCustomDataType.isSupportAppendCustomSqlStatement();
				case ORACLE:
					return oracleCustomDataType.isSupportAppendCustomSqlStatement();
			}
			throw new IllegalArgumentException("DataType.isSupportCreateType出现异常");
		}
		return true;
	}
}
