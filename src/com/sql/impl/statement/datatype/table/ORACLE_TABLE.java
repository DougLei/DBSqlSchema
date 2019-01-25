package com.sql.impl.statement.datatype.table;

import com.alibaba.fastjson.JSONArray;
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
		return customJson.getString("typeName");
	}

	protected DataType getCustomDataType() {
		return DataType.TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		StringBuilder sb = new StringBuilder(800);
		String typeName = customJson.getString("typeName");
		sb.append("create type ").append(typeName).append("_object").append(" as object(").append(newline());
		
		JSONArray array = customJson.getJSONArray("column");
		JSONObject json = null;
		for(int i=0;i<array.size();i++){
			json = array.getJSONObject(i);
			sb.append(json.getString("name")).append(" ").append(DataType.toValue(json.getString("dataType")).getDataType());
			if(json.getIntValue("length")> 0){
				sb.append("(").append(json.getIntValue("length")).append(")");
			}
			if(i<array.size()-1){
				sb.append(",");
			}
			sb.append(newline());
		}
		sb.append(");").append(newline());
		
		sb.append("create type ").append(typeName).append(" as table of ").append(typeName).append("_object");
		return sb.toString();
	}

	public boolean isSupportCreateType() {
		return true;
	}
}
