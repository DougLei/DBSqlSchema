package com.sql.impl.statement.basic.model.set;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.BasicModelImpl;
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
				sb.append("(");
				if(StrUtils.notEmpty(sqlId)){
					sb.append(SqlStatementBuilderContext.buildSqlStatement(sqlId));
				}else{
					SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
					infoBuilder.setJson(sqlJson);
					sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
				}
				sb.append(")");
				break;
		}
		return sb.toString();
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public void setValueFunction(Function valueFunction) {
		this.valueFunction = valueFunction;
	}
	public void setValueType(String valueType) {
		this.valueType = Type.toValue(valueType);
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public void setSqlJson(JSONObject sqlJson) {
		this.sqlJson = sqlJson;
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
