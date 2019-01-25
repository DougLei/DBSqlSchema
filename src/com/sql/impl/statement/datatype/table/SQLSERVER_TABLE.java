package com.sql.impl.statement.datatype.table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.AbstractCustomDataType;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_TABLE extends AbstractCustomDataType{
	private SQLSERVER_TABLE(){}
	private static final SQLSERVER_TABLE tableDataType = new SQLSERVER_TABLE();
	public static final SQLSERVER_TABLE newInstance(){
		return tableDataType;
	}

	public String getAppendCustomSqlStatement(JSONObject customJson) {
		String readonly = "";
		if(customJson.getBooleanValue("isReadonly")){
			readonly = " readonly";
		}
		return "as "+customJson.getString("typeName") + readonly;
	}


	protected DataType getCustomDataType() {
		return DataType.TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		StringBuilder sb = new StringBuilder(800);
		sb.append("create type ").append(customJson.getString("typeName")).append(" as table");
		sb.append(newline()).append("(").append(newline());
		
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
		sb.append(")");
		return sb.toString();
	}
	
	public boolean isSupportCreateType() {
		return true;
	}
}
