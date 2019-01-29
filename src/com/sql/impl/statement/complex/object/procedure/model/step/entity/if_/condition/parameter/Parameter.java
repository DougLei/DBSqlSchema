package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition.parameter;

import com.sql.statement.basic.model.function.Function;

/**
 * 
 * @author DougLei
 */
public class Parameter {
	private String type;
	private String value;
	private String name;
	private Function function;
	
	public String getSqlStatement() {
		return null;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
}
