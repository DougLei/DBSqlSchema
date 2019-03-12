package com.sql.impl.statement.complex.object.datatype.table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.util.StrUtils;

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

	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		if(StrUtils.isEmpty(name)){
			return customJson.getString("typeName");
		}
		return name + " " + customJson.getString("typeName");
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
			String typeName = customJson.getString("typeName");
			sb.append("create type ").append(typeName).append("_object").append(" as object(").append(newline());
			appendColumnSql(array, sb);
			sb.append(");").append(newline());
			
			sb.append("create type ").append(typeName).append(" as table of ").append(typeName).append("_object;");
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
