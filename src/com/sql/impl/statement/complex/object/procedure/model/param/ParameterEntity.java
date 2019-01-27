package com.sql.impl.statement.complex.object.procedure.model.param;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity extends AbstractDataType{
	
	public ParameterEntity(String name, String dataType,int length, Object precision, String inOut, String defaultValue) {
		this.name = name;
		setDataType(dataType);
		setLength(length);
		setPrecision(precision);
		setInOut(inOut);
		this.defaultValue = defaultValue;
	}
	
	private String name;
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
	
	public void setCustomJson(JSONObject customJson) {
		if(customJson != null){
			customJson.put("isReadonly", true);
		}
		super.setCustomJson(customJson);
	}
	
	/**
	 * 
	 * @author DougLei
	 */
	private enum InOut {
		IN,
		OUT,
		INOUT;
		
		static InOut toValue(String str){
			try {
				return InOut.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：[in, out, inout]");
			}
		}
	}
	
	public boolean isIN(){
		return inOut == InOut.IN;
	}
	public boolean isOUT(){
		return inOut == InOut.OUT;
	}
	public boolean isIN_OUT(){
		return inOut == InOut.INOUT;
	}
}
