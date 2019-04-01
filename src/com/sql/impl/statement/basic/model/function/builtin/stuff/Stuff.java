package com.sql.impl.statement.basic.model.function.builtin.stuff;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class Stuff implements BuiltinFunction{
	protected String source;
	
	protected int startIndex=-1;
	protected int subLength=-1;
	
	protected String startIndexParam;
	protected String subLengthParam;
	
	protected String replaceTarget;
	
	public BuiltinFunction init(JSONObject confJson) {
		this.source = new Parameter(confJson.getJSONObject("source")).getSqlStatement();
		if(!setStartIndex(confJson)){
			this.startIndexParam = new Parameter(confJson.getJSONObject("startIndexParam")).getSqlStatement();
			this.subLengthParam = new Parameter(confJson.getJSONObject("subLengthParam")).getSqlStatement();
		}
		this.replaceTarget = new Parameter(confJson.getJSONObject("replaceTarget")).getSqlStatement();
		return this;
	}

	private boolean setStartIndex(JSONObject confJson) {
		if(confJson.get("startIndex") != null){
			setStartIndex(confJson.getIntValue("startIndex"));
			return setSubLength(confJson);
		}
		return false;
	}
	
	private boolean setSubLength(JSONObject confJson) {
		if(confJson.get("subLength") != null){
			setSubLength(confJson.getIntValue("subLength"));
			return true;
		}
		return false;
	}

	private void setStartIndex(int startIndex) {
		if(startIndex <1){
			throw new IllegalArgumentException("配置函数_stuff时，startIndex参数值不能小于1");
		}
		this.startIndex = startIndex;
	}
	private void setSubLength(int subLength) {
		if(subLength <0){
			throw new IllegalArgumentException("配置函数_stuff时，subLength参数值不能小于0");
		}
		this.subLength = subLength;
	}
}
