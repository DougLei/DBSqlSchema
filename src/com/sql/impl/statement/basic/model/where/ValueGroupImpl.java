package com.sql.impl.statement.basic.model.where;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.where.ValueGroup;

/**
 * 
 * @author DougLei
 */
public class ValueGroupImpl implements ValueGroup{

	private List<String> values;
	
	public int size() {
		if(values != null && values.size()>0){
			return values.size();
		}
		throw new IllegalArgumentException("where value参数值配置错误");
	}

	public String getValue(int index) {
		if(values != null && (values.size()-1)<index){
			return values.get(index);
		}
		throw new IllegalArgumentException("where value参数值配置错误");
	}

	public void setValueEntity(JSONObject valueEntityJsonObject) {
		switch(Type.toValue(valueEntityJsonObject.getString("type"))){
			case VALUE:
				addValue(valueEntityJsonObject.getString("value"));
				break;
			case PARAMETER:
				addValue(Tools.getName(null, valueEntityJsonObject.getString("paramName")));
				break;
			case FUNCTION:
				addValue(FunctionImpl.newInstance(valueEntityJsonObject.getJSONObject("function")).getSqlStatement());
				break;
		}
	}
	private void addValue(String value){
		if(values == null){
			values = new ArrayList<String>();
		}
		values.add(value);
	}
	
	private enum Type {
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
			return "{" + name() + "}";
		}
	}
}
