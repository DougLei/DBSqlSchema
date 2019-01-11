package com.sql.impl.statement.model.where;

import com.sql.impl.statement.model.BasicImpl;
import com.sql.impl.statement.model.function.FunctionImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.where.DataOperatorType;
import com.sql.statement.model.where.LogicOperatorType;
import com.sql.statement.model.where.Where;

/**
 * 
 * @author DougLei
 */
public class WhereImpl extends BasicImpl implements Where {

	private LogicOperatorType nextLogicOperator;
	private DataOperatorType dataOperator;
	private String columnName;
	private String[] values;
	private Function columnFunction;
	private Function valueFunction;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(processSqlStatement(columnFunction, columnName));
		sb.append(" ");
		sb.append(processSqlStatement(dataOperator, valueFunction, values));
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
	
	private String processSqlStatement(DataOperatorType dataOperator, Function function, String[] values){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement != null){
			sqlStatement = dataOperator.getSqlStatement() + " " + sqlStatement;
		}else{
			sqlStatement = dataOperator.getSqlStatement(values);
		}
		return sqlStatement;
	}
	
	public void setDataOperator(String dataOperator) {
		this.dataOperator = DataOperatorType.toValue(dataOperator);
	}
	public void setNextLogicOperator(String nextLogicOperator) {
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	public void setColumnFunction(String functionName, String[] parameters) {
		if(columnFunction == null){
			columnFunction = FunctionImpl.newInstance(functionName, parameters);
		}
	}
	public void setValueFunction(String functionName, String[] parameters) {
		if(valueFunction == null){
			valueFunction = FunctionImpl.newInstance(functionName, parameters);
		}
	}
}
