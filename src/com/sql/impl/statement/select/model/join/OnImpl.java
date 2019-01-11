package com.sql.impl.statement.select.model.join;

import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.join.On;
import com.sql.statement.model.where.LogicOperatorType;

/**
 * 
 * @author DougLei
 */
public class OnImpl extends BasicImpl implements On {
	private StringBuilder sb = new StringBuilder();
	
	private LogicOperatorType nextLogicOperator;
	private Function leftFunction;
	private Function rightFunction;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		
		
		return sb.toString();
	}

	public void setNextLogicOperator(String nextLogicOperator) {
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
}
