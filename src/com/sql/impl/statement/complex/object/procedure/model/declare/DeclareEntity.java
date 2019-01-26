package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity extends AbstractDataType{
	
	private String name;
	private String defaultValue;
	
	public DeclareEntity(String name, String dataType, int length, Object precision, String defaultValue) {
		this.name = name;
		setDataType(dataType);
		setLength(length);
		setPrecision(precision);
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setCustomJson(JSONObject customJson) {
		if(customJson != null){
			customJson.put("isReadonly", false);
		}
		super.setCustomJson(customJson);
	}
}
