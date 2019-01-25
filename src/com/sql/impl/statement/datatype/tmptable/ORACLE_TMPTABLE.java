package com.sql.impl.statement.datatype.tmptable;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.AbstractCustomDataType;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public class ORACLE_TMPTABLE extends AbstractCustomDataType{
	private ORACLE_TMPTABLE(){}
	private static final ORACLE_TMPTABLE tmptableDataType = new ORACLE_TMPTABLE();
	public static final ORACLE_TMPTABLE newInstance(){
		return tmptableDataType;
	}

	public String getAppendCustomSqlStatement(JSONObject customJson) {
		return null;
	}

	protected DataType getCustomDataType() {
		return null;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		return null;
	}
	
	public boolean isSupportCreateType() {
		return false;
	}
}
