package com.sql.impl.statement.complex.object.procedure.model;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDataType {
	private DataType dataType;
	private String dataTypeStr;
	private JSONObject customJson;
	
	protected void setDataType(String dataType){
		this.dataType = DataType.toValue(dataType);
		if(this.dataType == DataType.CUSTOM){
			this.dataTypeStr = dataType.trim();
		}else{
			this.dataTypeStr = this.dataType.getDataType();
		}
	}
	
	public String getDataType() {
		return dataTypeStr;
	}
	
	public boolean isCustomType(){
		return dataType.isCustomType();
	}
	
	public void setCustomJson(JSONObject customJson) {
		this.customJson = customJson;
	}
	public String getCustomSqlStatement(){
		if(isCustomType()){
			return dataType.getCustomSqlStatement(customJson);
		}
		return null;
	}
}
