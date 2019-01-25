package com.sql.impl.statement.datatype;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author DougLei
 */
public abstract class CustomDataTypeImpl implements CustomDataType{
	
	
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
}
