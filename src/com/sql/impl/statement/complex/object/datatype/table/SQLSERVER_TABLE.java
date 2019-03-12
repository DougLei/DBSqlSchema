package com.sql.impl.statement.complex.object.datatype.table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;

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

	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		String readonly = "";
		if(customJson.getBooleanValue("isReadonly")){
			readonly = " readonly";
		}
		return "@" + name + " as "+customJson.getString("typeName") + readonly;
	}

	public boolean isSupportAppendCustom() {
		return true;
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
	
	public boolean isSupportDynamicCreateType() {
		return false;
	}

	public String getDynamicCreateTypeSqlStatement(String name, JSONObject customJson) {
		return null;
	}

	public String getDynamicDropTypeSqlStatement(String name, JSONObject customJson) {
		return null;
	}
	
	public String getDynamicCreateTypeName(String name) {
		return null;
	}
}
