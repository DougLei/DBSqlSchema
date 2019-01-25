package com.sql.impl.statement.datatype.tmptable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.AbstractCustomDataType;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_TMPTABLE extends AbstractCustomDataType{
	private SQLSERVER_TMPTABLE(){}
	private static final SQLSERVER_TMPTABLE tmptableDataType = new SQLSERVER_TMPTABLE();
	public static final SQLSERVER_TMPTABLE newInstance(){
		return tmptableDataType;
	}

	public String getAppendCustomSqlStatement(JSONObject customJson) {
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(800);
			sb.append("table ").append(newline()).append("(").append(newline());
			appendColumnSql(array, sb);
			sb.append(")");
		}
		return null;
	}

	protected DataType getCustomDataType() {
		return DataType.TMP_TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		return null;
	}
	
	public boolean isSupportCreateType() {
		return false;
	}
}
