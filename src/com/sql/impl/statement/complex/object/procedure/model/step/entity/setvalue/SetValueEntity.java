package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;
import com.sql.statement.basic.model.function.Function;

/**
 * 
 * @author DougLei
 */
public class SetValueEntity extends AbstractEntity{
	private Type type;
	private String paramName;
	
	private String value;
	private Function valueFunction;
	
	
	public static SetValueEntity getInstance(JSONObject json) {
		SetValueEntity entity = new SetValueEntity();
		entity.type = Type.toValue(json.getString("type"));
		entity.value = json.getString("value");
		entity.setValueFunction(json.getJSONObject("valueFunction"));
		
		return entity;
	}
	
	private void setValueFunction(JSONObject function){
		this.valueFunction = FunctionImpl.newInstance(function.getString("name"), function.getJSONArray("parameters"));
	}

	public String getSqlStatement() {
		return null;
	}
	
	private enum Type{
		VALUE,
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
