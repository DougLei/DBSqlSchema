package com.sql.impl.statement.complex.object.procedure.model.step.entity.declare;

/**
 * 
 * @author DougLei
 */
public class DeclareColumnEntity {
	private String name;
	private String dataType;
	private int length;

	public DeclareColumnEntity(String name, String dataType, int length) {
		this.name = name;
		this.dataType = dataType;
		this.length = length;
	}
	
	public String getName() {
		return name;
	}
	public String getDataType() {
		return dataType;
	}
	public int getLength() {
		return length;
	}
}
