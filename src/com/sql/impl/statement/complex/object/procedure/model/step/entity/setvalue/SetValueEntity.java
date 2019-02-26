package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.select.SelectSqlStatementBuilder;
import com.sql.statement.complex.select.CombinationSelectSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class SetValueEntity extends AbstractEntity{
	private Type type;
	protected String[] paramName;
	
	protected String value;
	protected Function valueFunction;
	
	protected String selectSqlId;
	protected JSONObject selectSqlJson;
	
	public static final SetValueEntity getInstance(JSONObject setJson) {
		SetValueEntity entity = getSetValueEntity();
		
		JSONArray declareEntityJsonArray = setJson.getJSONArray("declare");
		if(declareEntityJsonArray == null || declareEntityJsonArray.size() == 0){
			throw new NullPointerException("给变量赋值时，declare属性配置的变量信息不能为空");
		}
		int size = declareEntityJsonArray.size();
		entity.paramName = new String[size];
		JSONObject json = null;
		for(int i=0;i<size;i++){
			json = declareEntityJsonArray.getJSONObject(i);
			if(StrUtils.isEmpty(json.getString("name"))){
				throw new NullPointerException("给变量赋值时，declare属性配置的变量name不能为空");
			}
			entity.paramName[i] = json.getString("name");
			if(json.getBooleanValue("isDeclare")){
				DeclareContext.recordDeclare(json);
			}
		}
		
		entity.type = Type.toValue(setJson.getString("type"));
		entity.value = setJson.getString("value");
		
		entity.setValueFunction(setJson.getJSONObject("valueFunction"));
		
		entity.selectSqlId = setJson.getString("selectSqlId");
		entity.selectSqlJson = setJson.getJSONObject("selectSqlJson");
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
		}
		return null;
	}
	
	protected abstract String getVALUESqlStatement();
	protected abstract String getFUNCTIONSqlStatement();

	/**
	 * 
	 * @author DougLei
	 */
	private enum Type {
		VALUE,
		FUNCTION,
		SELECT_SQL;
		
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
	
	private String getSELECT_SQLSqlStatement(){
		SqlStatementBuilder builder = getSelectSqlStatementBuilder();
		if(builder instanceof SelectSqlStatementBuilder){
			return getSIMPLE_SELECT_SQLSqlStatement((SelectSqlStatementBuilder) builder);
		}else if(builder instanceof CombinationSelectSqlStatementBuilder){
			return getCOMBINATION_SELECT_SQLSqlStatement((CombinationSelectSqlStatementBuilder) builder);
		}
		throw new IllegalArgumentException("在存储过程set value时，通过语句给变量赋值，语句类型目前只支持[select、combination_select]");
	}
	
	private SqlStatementBuilder getSelectSqlStatementBuilder(){
		SqlStatementBuilder builder = null;
		if(StrUtils.notEmpty(selectSqlId)){
			builder = SqlStatementBuilderContext.getSqlStatementBuilder(selectSqlId);
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(selectSqlJson);
			builder = infoBuilder.createSqlStatementBuilder();
			builder.buildSqlStatement();
		}
		return builder;
	}
	
	private String getSIMPLE_SELECT_SQLSqlStatement(SelectSqlStatementBuilder builder){
		StringBuilder sb = new StringBuilder(builder.getBody().length() + builder.getResultSetColumnNames().size() * 50);
		sb.append("select \n");
		
		appendSetValueFromColumnName(builder.getResultSetColumnNames(), sb);
		return sb.append(builder.getBody()).toString();
	}
	
	private String getCOMBINATION_SELECT_SQLSqlStatement(CombinationSelectSqlStatementBuilder builder){
		StringBuilder sb = new StringBuilder(builder.getWithBody().length() + builder.getBody().length() + builder.getResultSetColumnNames().size() * 50);
		sb.append(builder.getWithBody());
		sb.append("select \n");
		
		appendSetValueFromColumnName(builder.getResultSetColumnNames(), sb);
		
		sb.append(" from (\n");
		sb.append(builder.getBody());
		sb.append("\n)").append(" _subUnionQueryD_");
		return sb.append(builder.getBody()).toString();
	}
	
	private void appendSetValueFromColumnName(List<String> columnNames, StringBuilder sb){
		int flag = columnNames.size()-1;
		for (int i=0;i<columnNames.size();i++) {
			if(i<paramName.length){
				sb.append(getSQL_SetParamSql(paramName[i]));
			}
			sb.append(columnNames.get(i));
			if(i<flag){
				sb.append(", ");
			}
		}
	}
	
	/**
	 * 获取通过select sql语句赋值时，给参数赋值的语句
	 * @param paramName
	 * @return
	 */
	protected abstract String getSQL_SetParamSql(String paramName);
}
