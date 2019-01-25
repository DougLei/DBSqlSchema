package com.sql.impl.statement.datatype.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.CustomDataType;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_TABLE implements CustomDataType{
	private SQLSERVER_TABLE(){}
	private static final SQLSERVER_TABLE tableDataType = new SQLSERVER_TABLE();
	public static final SQLSERVER_TABLE newInstance(){
		return tableDataType;
	}
	
	
	
	public String getCustomSqlStatement(JSONObject customJson) {
		
		
		return null;
	}
}
