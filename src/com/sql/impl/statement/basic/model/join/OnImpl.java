package com.sql.impl.statement.basic.model.join;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.join.On;
import com.sql.statement.basic.model.where.DataOperatorType;
import com.sql.statement.basic.model.where.LogicOperatorType;

/**
 * 
 * @author DougLei
 */
public class OnImpl extends BasicModelImpl implements On {
	
	private LogicOperatorType nextLogicOperator;
	private DataOperatorType dataOperator;
	private String leftColumnName;
	private String rightColumnName;
	private Function leftFunction;
	private Function rightFunction;
	
	public OnImpl(JSONObject json) {
		this.leftColumnName = json.getString("leftColumnName");
		this.leftFunction = FunctionImpl.newInstance(json.getJSONObject("leftFunction"));
		this.dataOperator = DataOperatorType.toValue(json.getString("operator"));
		this.rightColumnName = json.getString("rightColumnName");
		this.rightFunction = FunctionImpl.newInstance(json.getJSONObject("rightFunction"));
		this.nextLogicOperator = LogicOperatorType.toValue(json.getString("nextLogicOperator"));
	}

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
}
