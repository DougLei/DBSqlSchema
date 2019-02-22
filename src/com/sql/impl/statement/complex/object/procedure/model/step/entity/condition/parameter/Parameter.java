package com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.parameter;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.context.DeclareVariableContext;
import com.sql.statement.basic.model.function.Function;

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
			DeclareVariableContext.recordDeclare(declareEntityJson);
		}
		switch(type){
			case VALUE:
				return value;
			case PARAMETER:
				return getParameter();
			case FUNCTION:
				return function.getSqlStatement();
		}
		return null;
	}
	
	private String getParameter() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return "@"+name;
			case ORACLE:
				return name;
		}
		return null;
	}
	
	public void setDeclare(boolean isDeclare) {
		this.isDeclare = isDeclare;
	}
	public void setDeclareJson(JSONObject declareEntityJson) {
		this.declareEntityJson = declareEntityJson;
	}
	public void setType(String type) {
		this.type = Type.toValue(type);
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	
	private enum Type{
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
