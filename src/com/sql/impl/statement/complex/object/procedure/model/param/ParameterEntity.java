package com.sql.impl.statement.complex.object.procedure.model.param;

import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity {
	
	private String name;
	private String dataType;
	private int length;
	private InOut inOut;
	private String defaultValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public InOut getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		if(StrUtils.isEmpty(inOut)){
			this.inOut = InOut.IN;
		}else{
			this.inOut = InOut.toValue(inOut);
		}
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDataType() {
		return dataType;
	}
	public int getLength() {
		return length;
	}
	public void setInOut(InOut inOut) {
		this.inOut = inOut;
	}
}
