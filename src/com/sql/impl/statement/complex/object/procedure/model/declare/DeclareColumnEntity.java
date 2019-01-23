package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareColumnEntity extends AbstractDataType{
	private String name;
	private int length;

	public DeclareColumnEntity(String name, String dataType, int length) {
		setDataType(dataType);
		this.name = name;
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public int getLength() {
		return length;
	}
}
