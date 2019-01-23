package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.sql.enums.DataType;

/**
 * 
 * @author DougLei
 */
public class DeclareColumnEntity {
	private String name;
	private DataType dataType;
	private int length;

	public DeclareColumnEntity(String name, String dataType, int length) {
		this.dataType = DataType.toValue(dataType);
		this.name = name;
		this.length = length;
	}
	
	public String getName() {
		return name;
	}
	public String getDataType() {
		return dataType.getDataType();
	}
	public int getLength() {
		return length;
	}
}
