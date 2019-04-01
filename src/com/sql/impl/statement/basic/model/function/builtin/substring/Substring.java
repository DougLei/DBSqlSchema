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
	
	protected String subIndexParam;
	protected String subLengthParam;
	
	protected int subIndex=-1;
	protected int subLength=-1;
	
	public BuiltinFunction init(JSONObject confJson) {
		parameterSqlStatement = new Parameter(confJson.getJSONObject("parameter")).getSqlStatement();
		if(!setSubIndex(confJson)){
			subIndexParam = new Parameter(confJson.getJSONObject("subIndexParam")).getSqlStatement();
			subLengthParam = new Parameter(confJson.getJSONObject("subLengthParam")).getSqlStatement();
		}
		return this;
	}
	private boolean setSubIndex(JSONObject confJson){
		if(confJson.get("subIndex") != null){
			setSubIndex(confJson.getIntValue("subIndex"));
			return setSubLength(confJson);
		}
		return false;
	}
	private boolean setSubLength(JSONObject confJson){
		if(confJson.get("subLength") != null){
			setSubLength(confJson.getIntValue("subLength"));
			return true;
		}
		return false;
	}
	
	private void setSubIndex(int subIndex) {
		if(subIndex == 0){
			subIndex = 1;
		}
		this.subIndex = subIndex;
	}
	private void setSubLength(int subLength) {
		if(subLength < 0){
			subLength = 0;
		}
		this.subLength = subLength;
	}
}
