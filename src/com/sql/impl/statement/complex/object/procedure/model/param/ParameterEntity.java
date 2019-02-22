package com.sql.impl.statement.complex.object.procedure.model.param;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity extends AbstractDataType{
	
	public ParameterEntity(String name, String dataType,int length, Object precision, String inOut, String defaultValue) {
		this.name = name.trim().toUpperCase();
		setDataType(dataType);
		setLength(length);
		setPrecision(precision);
		setInOut(inOut);
		this.defaultValue = defaultValue;
	}
	
	private String name;
	private String defaultValue;
	
	public String getName() {
		return name;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setCustomJson(JSONObject customJson) {
		if(customJson != null){
			customJson.put("isReadonly", true);
		}
		super.setCustomJson(customJson);
	}
}
