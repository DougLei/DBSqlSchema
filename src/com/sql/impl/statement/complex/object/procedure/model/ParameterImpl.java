package com.sql.impl.statement.complex.object.procedure.model;

import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.complex.object.procedure.model.Parameter;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterImpl extends BasicImpl implements Parameter{
	
	private String name;
	private String dataType;
	private int length;
	private String inOut;
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(name).append(" ");
		sb.append(dataType);
		if(length > 0){
			sb.append("(").append(length).append(")");
		}
		if(StrUtils.notEmpty(inOut)){
			sb.append(" ").append(inOut);
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
}
