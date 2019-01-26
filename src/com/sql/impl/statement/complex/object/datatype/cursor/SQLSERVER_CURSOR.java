package com.sql.impl.statement.complex.object.datatype.cursor;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;

public class SQLSERVER_CURSOR extends AbstractCustomDataType{
	private SQLSERVER_CURSOR(){}
	private static final SQLSERVER_CURSOR cursorDataType = new SQLSERVER_CURSOR();
	public static final SQLSERVER_CURSOR newInstance(){
		return cursorDataType;
	}
	
	@Override
	public String getAppendCustomSqlStatement(JSONObject customJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupportCreateType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSupportAppendCustomSqlStatement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected DataType getCustomDataType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCreateTypeSql(JSONObject customJson) {
		// TODO Auto-generated method stub
		return null;
	}

}
