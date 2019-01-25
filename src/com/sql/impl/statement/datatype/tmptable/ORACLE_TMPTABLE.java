package com.sql.impl.statement.datatype.tmptable;

import com.alibaba.fastjson.JSONArray;
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
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(800);
			
			// TODO
			
			
			
			
			return sb.toString();
		}
		return null;
	}

	protected DataType getCustomDataType() {
		return DataType.TMP_TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		
		// TODO
		
		
		
		
		return null;
	}
	
	public boolean isSupportCreateType() {
		return true;
	}
}
