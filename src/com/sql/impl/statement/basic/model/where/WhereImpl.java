package com.sql.impl.statement.basic.model.where;

import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.where.DataOperatorType;
import com.sql.statement.basic.model.where.LogicOperatorType;
import com.sql.statement.basic.model.where.Value;
import com.sql.statement.basic.model.where.Where;

/**
 * 
 * @author DougLei
 */
public class WhereImpl extends BasicModelImpl implements Where {

	private LogicOperatorType nextLogicOperator;
	private DataOperatorType dataOperator;
	private String columnName;
	private Value value;
	private Function columnFunction;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(processSqlStatement(columnFunction, columnName)).append(" ");
		
		if(value.isNullValueType()){
			if(dataOperator == DataOperatorType.EQ){
				sb.append("is null");
			}else{
				sb.append("is not null");
			}
		}else{
			sb.append(dataOperator.getSqlStatement(value.getSqlStatements()));
		}
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
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public void setColumnFunction(Function function) {
		if(columnFunction == null){
			columnFunction = function;
		}
	}
}
