package com.sql.impl.statement.datatype.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.CustomDataType;

/**
 * 
 * @author DougLei
 */
public class ORACLE_TABLE implements CustomDataType{
	private ORACLE_TABLE(){}
	private static final ORACLE_TABLE tableDataType = new ORACLE_TABLE();
	public static final ORACLE_TABLE newInstance(){
		return tableDataType;
	}
	
	
	
	public String getCustomSqlStatement(JSONObject customJson) {
		
		
		return null;
	}
}
