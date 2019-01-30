package com.sql.impl.statement.complex.object.procedure.model.step.entity.return_;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;

/**
 * 
 * @author DougLei
 */
public class ReturnEntity extends AbstractEntity{

	private Type type;
	private String value;
	
	public ReturnEntity(JSONObject returnEntity) {
		if(returnEntity == null || returnEntity.size() == 0){
			this.type = Type.VALUE;
		}else{
			this.type = Type.toValue(returnEntity.getString("type"));
			this.value = returnEntity.getString("value");
		}
	}

	public String getSqlStatement() {
		switch(type){
			case VALUE:
				return value;
		}
		return null;
	}
	
	private enum Type {
		VALUE;
		
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
