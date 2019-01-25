package com.sql.impl.statement.datatype.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.AbstractCustomDataType;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public class ORACLE_TABLE extends AbstractCustomDataType{
	private ORACLE_TABLE(){}
	private static final ORACLE_TABLE tableDataType = new ORACLE_TABLE();
	public static final ORACLE_TABLE newInstance(){
		return tableDataType;
	}
	

	public String getAppendCustomSqlStatement(JSONObject customJson) {
		return null;
	}

	protected DataType getCustomDataType() {
		return DataType.TABLE;
	}


	@Override
	protected String getCreateTypeSql(JSONObject customJson) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSupportCreateType() {
		return true;
	}
}
