package com.sql.impl.statement.basic.model.function.builtin.substring;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class Substring implements BuiltinFunction{
	protected String parameterSqlStatement;
	protected int subIndex;
	protected int subLength;
	
	public BuiltinFunction init(JSONObject confJson) {
		parameterSqlStatement = new Parameter(confJson.getJSONObject("parameter")).getSqlStatement();
		setSubIndex(confJson.getIntValue("subIndex"));
		setSubLength(confJson.getIntValue("subLength"));
		return this;
	}
	private void setSubIndex(int subIndex){
		if(subIndex == 0){
			subIndex = 1;
		}
		this.subIndex = subIndex;
	}
	private void setSubLength(int subLength){
		if(subLength < 0){
			subLength = 0;
		}
		this.subLength = subLength;
	}

	public String getFunctionName() {
		return "_substring";
	}
}
