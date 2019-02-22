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
	
	public DeclareEntity(JSONObject json) {
		this(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.get("precision"), json.getString("inOut"), json.getString("defaultValue"));
		if(!isBaseType()){
			setCustomJson(json.getJSONObject("custom"));
		}
	}
	private DeclareEntity(String name, String dataType, int length, Object precision, String inOut, String defaultValue) {
		this.name = name.trim().toUpperCase();
		setDataType(dataType);
		setLength(length);
		setPrecision(precision);
		setInOut(inOut);
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
