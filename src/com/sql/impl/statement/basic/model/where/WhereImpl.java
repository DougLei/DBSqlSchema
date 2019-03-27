package com.sql.impl.statement.basic.model.where;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
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
	
	public WhereImpl(JSONObject json) {
		this.columnName = json.getString("columnName");
		this.columnFunction = FunctionImpl.newInstance(json.getJSONObject("columnFunction"));
		this.dataOperator = DataOperatorType.toValue(json.getString("operator"));
		this.nextLogicOperator = LogicOperatorType.toValue(json.getString("nextLogicOperator"));
		
		JSONObject valueJson = json.getJSONObject("value");
		if(valueJson == null || valueJson.size() == 0){
			throw new IllegalArgumentException("where 子句中，value属性不能为空");
		}
		this.value = new ValueImpl(valueJson);
	}

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
}
