package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue.impl.ORACLE_SetValueEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue.impl.SQLSERVER_SetValueEntity;
import com.sql.statement.basic.model.function.Function;

/**
 * 
 * @author DougLei
 */
public abstract class SetValueEntity extends AbstractEntity{
	private Type type;
	protected String paramName;
	
	protected String value;
	protected Function valueFunction;
	
	protected String selectSqlId;
	protected JSONObject selectSqlJson;
	
	public static final SetValueEntity getInstance(JSONObject json) {
		SetValueEntity entity = getSetValueEntity();
		entity.type = Type.toValue(json.getString("type"));
		entity.paramName = json.getString("paramName");
		
		entity.value = json.getString("value");
		
		entity.setValueFunction(json.getJSONObject("valueFunction"));
		
		entity.selectSqlId = json.getString("selectSqlId");
		entity.selectSqlJson = json.getJSONObject("selectSqlJson");
		return entity;
	}
	
	private static SetValueEntity getSetValueEntity() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_SetValueEntity();
			case ORACLE:
				return new ORACLE_SetValueEntity();
		}
		return null;
	}

	private void setValueFunction(JSONObject function){
		this.valueFunction = FunctionImpl.newInstance(function);
	}

	public String getSqlStatement(){
		switch(type){
			case VALUE:
				return getVALUESqlStatement();
			case FUNCTION:
				return getFUNCTIONSqlStatement();
			case SELECT_SQL:
				return getSELECT_SQLSqlStatement();
			case PROCEDURE:
				return getPROCEDURESqlStatement();
		}
		return null;
	}
	
	protected abstract String getVALUESqlStatement();
	protected abstract String getFUNCTIONSqlStatement();
	protected abstract String getSELECT_SQLSqlStatement();
	protected abstract String getPROCEDURESqlStatement();

	/**
	 * 
	 * @author DougLei
	 */
	private enum Type {
		VALUE,
		FUNCTION,
		SELECT_SQL,
		PROCEDURE;
		
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
