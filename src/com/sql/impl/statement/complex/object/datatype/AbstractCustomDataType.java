package com.sql.impl.statement.complex.object.datatype;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractCustomDataType implements CustomDataType{
	
	public String getCreateTypeSqlStatement(JSONObject customJson) {
		if(customJson.getBooleanValue("isCreateType")){
			String typeName = customJson.getString("typeName");
			if(!CustomDataTypeContext.customDataTypeIsExists(getCustomDataType(), typeName)){
				return getCreateTypeSql(customJson);
			}
		}
		return null;
	}
	
	protected abstract DataType getCustomDataType();
	protected abstract String getCreateTypeSql(JSONObject customJson);
	
	protected static final char newline(){
		return '\n';
	}
	
	protected void appendColumnSql(JSONArray columnJsonarray, StringBuilder sb){
		if(columnJsonarray != null && columnJsonarray.size()>0){
			JSONObject json = null;
			for(int i=0;i<columnJsonarray.size();i++){
				json = columnJsonarray.getJSONObject(i);
				sb.append(json.getString("name")).append(" ").append(DataType.toValue(json.getString("dataType")).getBaseDataType());
				if(json.getIntValue("length")> 0){
					sb.append("(").append(json.getIntValue("length")).append(")");
				}
				if(i<columnJsonarray.size()-1){
					sb.append(",");
				}
				sb.append(newline());
			}
		}
	}
}
