package com.sql.impl.statement.complex.object.procedure.model.declare;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity extends AbstractDataType{
	private String name;
	
	private int length;
	private String defaultValue;
	
	private List<DeclareColumnEntity> columns;
	
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
	public List<DeclareColumnEntity> getColumns() {
		return columns;
	}
	public String getDefaultValue() {
		return defaultValue;
	}

	public void addColumn(DeclareColumnEntity declareColumnEntity) {
		if(columns == null){
			columns = new ArrayList<DeclareColumnEntity>();
		}
		columns.add(declareColumnEntity);
	}
}
