package com.sql.impl.statement.complex.object.procedure.model.param;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity extends AbstractDataType{
	
	public ParameterEntity(String name, String dataType,int length, String inOut, String defaultValue) {
		this.name = name;
		setDataType(dataType);
		this.length = length;
		setInOut(inOut);
		this.defaultValue = defaultValue;
	}
	
	private String name;
	private int length;
	private InOut inOut;
	private String defaultValue;
	
	public String getName() {
		return name;
	}
	public InOut getInOut() {
		return inOut;
	}
	private void setInOut(String inOut) {
		if(StrUtils.isEmpty(inOut)){
			this.inOut = InOut.IN;
		}else{
			this.inOut = InOut.toValue(inOut);
		}
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public int getLength() {
		return length;
	}
	
	public void setCustomJson(JSONObject customJson) {
		if(customJson != null){
			customJson.put("isReadonly", true);
		}
		super.setCustomJson(customJson);
	}
}
