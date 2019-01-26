package com.sql.impl.statement.complex.object.datatype.cursor;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;

public class ORACLE_CURSOR extends AbstractCustomDataType{
	private ORACLE_CURSOR(){}
	private static final ORACLE_CURSOR cursorDataType = new ORACLE_CURSOR();
	public static final ORACLE_CURSOR newInstance(){
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
