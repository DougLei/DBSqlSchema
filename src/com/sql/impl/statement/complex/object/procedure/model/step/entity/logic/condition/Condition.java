package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.parameter.Parameter;
import com.sql.statement.basic.model.where.DataOperatorType;
import com.sql.statement.basic.model.where.LogicOperatorType;

/**
 * 
 * @author DougLei
 */
public class Condition {

	private Parameter leftParameter;
	private Parameter rightParameter;
	private LogicOperatorType nextLogicOperator;
	private DataOperatorType dataOperator;
	
	public String getSqlStatement() {
		return leftParameter.getSqlStatement() + dataOperator.getSqlStatement() + rightParameter.getSqlStatement();
	}
	
	public Condition(JSONObject json){
		setLeftParameter(json);
		setRightParameter(json);
		nextLogicOperator = LogicOperatorType.toValue(json.getString("nextLogicOperator"));
		dataOperator = DataOperatorType.toValue(json.getString("operator"));
	}
	
	private void setLeftParameter(JSONObject json) {
		leftParameter = getParameter("left", json);
	}
	private void setRightParameter(JSONObject json) {
		rightParameter = getParameter("right", json);
	}
	private Parameter getParameter(String str, JSONObject json){
		Parameter parameter = new Parameter();
		parameter.setDeclare(json.getBooleanValue(str+"Declare"));
		parameter.setType(json.getString(str+"Type"));
		parameter.setValue(json.getString(str+"Value"));
		parameter.setName(json.getString(str+"Name"));
		parameter.setFunction(FunctionImpl.newInstance(json.getJSONObject(str+"Function")));
		return parameter;
	}

	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}
}
