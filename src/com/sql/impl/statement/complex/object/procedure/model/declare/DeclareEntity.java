package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity extends AbstractDataType{
	
	private String name;
	private int length;
	private String defaultValue;
	
	public DeclareEntity(String name, String dataType, int length, String defaultValue) {
		this.name = name;
		setDataType(dataType);
		this.length = length;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	public int getLength() {
		return length;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
}
