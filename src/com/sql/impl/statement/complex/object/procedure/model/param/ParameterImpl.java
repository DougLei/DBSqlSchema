package com.sql.impl.statement.complex.object.procedure.model.param;

import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.complex.object.procedure.model.param.InOut;
import com.sql.statement.complex.object.procedure.model.param.Parameter;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterImpl extends BasicModelImpl implements Parameter{
	
	private String name;
	private String dataType;
	private int length;
	private InOut inOut;
	private String inoutSqlStatement;
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(name).append(" ");
		sb.append(dataType);
		if(length > 0){
			sb.append("(").append(length).append(")");
		}
		if(inOut != null && StrUtils.notEmpty(inoutSqlStatement)){
			sb.append(" ").append(inoutSqlStatement);
		}
		return sb.toString();
	}

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
		this.inOut = InOut.toValue(inOut);
	}
	public void setInoutSqlStatement(String inoutSqlStatement) {
		this.inoutSqlStatement = inoutSqlStatement;
	}
}
