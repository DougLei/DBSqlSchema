package com.sql.impl.statement.basic.model.set;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.set.Set;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SetImpl extends BasicModelImpl implements Set {
	
	private String columnName;
	private Type valueType;
	private String value;
	private String paramName;
	private Function valueFunction;
	private String sqlId;
	private JSONObject sqlJson;
	
	public SetImpl(JSONObject json) {
		this.columnName = json.getString("columnName");
		this.valueType = Type.toValue(json.getString("valueType"));
		this.value = json.getString("value");
		this.paramName = json.getString("paramName");
		this.valueFunction = FunctionImpl.newInstance(json.getJSONObject("valueFunction"));
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
	}
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(columnName).append(" = ");
		switch(valueType){
			case VALUE:
				if(StrUtils.notEmpty(value)){
					sb.append(value);
				}else if(StrUtils.notEmpty(paramName)){
					sb.append(paramName);
				}else if(valueFunction != null){
					sb.append(valueFunction.getSqlStatement());
				}
				break;
			case SUB_QUERY:
				sb.append("(").append(SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson)).append(")");
				break;
		}
		return sb.toString();
	}
	
	private enum Type {
		VALUE, 
		SUB_QUERY; 
		
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
