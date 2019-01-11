package com.sql.impl.statement.select.model.join;

import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.impl.statement.select.model.function.FunctionImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.join.On;
import com.sql.statement.model.where.DataOperatorType;
import com.sql.statement.model.where.LogicOperatorType;

/**
 * 
 * @author DougLei
 */
public class OnImpl extends BasicImpl implements On {
	
	private LogicOperatorType nextLogicOperator;
	private DataOperatorType dataOperator;
	private String leftColumnName;
	private String rightColumnName;
	private Function leftFunction;
	private Function rightFunction;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(processSqlStatement(leftFunction, leftColumnName));
		sb.append(" ");
		sb.append(dataOperator.getSqlStatement());
		sb.append(" ");
		sb.append(processSqlStatement(rightFunction, rightColumnName));
		return sb.toString();
	}
	
	private String processSqlStatement(Function function, String columnName){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = columnName;
		}
		return sqlStatement;
	}

	
	public void setDataOperator(String dataOperator) {
		this.dataOperator = DataOperatorType.toValue(dataOperator);
	}
	public void setNextLogicOperator(String nextLogicOperator) {
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
	public void setLeftColumnName(String leftColumnName) {
		this.leftColumnName = leftColumnName;
	}
	public void setRightColumnName(String rightColumnName) {
		this.rightColumnName = rightColumnName;
	}
	public void setLeftFunction(String functionName, String[] parameters) {
		if(leftFunction == null){
			leftFunction = new FunctionImpl(functionName, parameters);
		}
	}
	public void setRightFunction(String functionName, String[] parameters) {
		if(rightFunction == null){
			rightFunction = new FunctionImpl(functionName, parameters);
		}
	}
}
