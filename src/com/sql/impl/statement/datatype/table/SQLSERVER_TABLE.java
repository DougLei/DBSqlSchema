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
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(800);
			sb.append("create type ").append(customJson.getString("typeName")).append(" as table");
			sb.append(newline()).append("(").append(newline());
			
			appendColumnSql(array, sb);
			sb.append(")");
			return sb.toString();
		}
		return null;
	}
	
	public boolean isSupportCreateType() {
		return true;
	}
}
