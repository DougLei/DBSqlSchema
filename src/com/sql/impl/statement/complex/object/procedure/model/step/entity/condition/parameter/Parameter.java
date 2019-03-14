package com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.parameter;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareContext;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class Parameter {
	private boolean isDeclare;
	private JSONObject declareEntityJson;
	
	private Type type;
	private String value;
	private String name;
	private Function function;
	
	public String getSqlStatement() {
		if(isDeclare){
			DeclareContext.recordDeclare(declareEntityJson);
		}
		switch(type){
			case VALUE:
				return value;
			case PARAMETER:
				return Tools.getName(null, name);
			case FUNCTION:
				return function.getSqlStatement();
		}
		return null;
	}
	
	public void setType(String type) {
		this.type = Type.toValue(type);
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setDeclareInfo(boolean isDeclare, JSONObject declareEntityJson, String name) {
		this.isDeclare = isDeclare;
		this.declareEntityJson = declareEntityJson;
		if(declareEntityJson != null && StrUtils.notEmpty(declareEntityJson.getString("name"))){
			this.name = declareEntityJson.getString("name");
		}else{
			this.name = name;
		}
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	
	public boolean isNullValueType(){
		return type == Type.NULL_VALUE;
	}
	
	private enum Type{
		NULL_VALUE,
		VALUE,
		PARAMETER,
		FUNCTION;
		
		static Type toValue(String str){
			try {
				return Type.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(Type.values())+"]");
			}
		}
		public String toString(){
			return "{"+name()+"}";
		}
	}
}
