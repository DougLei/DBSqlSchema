package com.sql.impl.statement.complex.object.procedure.model.step.entity.declare;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity {
	private String name;
	private String dataType;
	private int length;
	private List<DeclareColumnEntity> columns;
	
	public DeclareEntity(String name, String dataType, int length) {
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
	public List<DeclareColumnEntity> getColumns() {
		return columns;
	}

	public void addColumn(DeclareColumnEntity declareColumnEntity) {
		if(columns == null){
			columns = new ArrayList<DeclareColumnEntity>();
		}
		columns.add(declareColumnEntity);
	}
}
