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
	private Function valueFunction;
	private String subSqlId;
	private JSONObject subSqlJson;
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(columnName).append(" = ");
		switch(valueType){
			case VALUE:
				if(valueFunction == null){
					sb.append(value);
				}else{
					sb.append(valueFunction.getSqlStatement());
				}
				break;
			case SUB_QUERY:
				sb.append("(");
				if(StrUtils.notEmpty(subSqlId)){
					sb.append(SqlStatementBuilderContext.buildSqlStatement(subSqlId));
				}else{
					SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
					infoBuilder.setJson(subSqlJson);
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
	public void setValueFunction(Function valueFunction) {
		this.valueFunction = valueFunction;
	}
	public void setValueType(String valueType) {
		this.valueType = Type.toValue(valueType);
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
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
