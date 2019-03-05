package com.sql.impl.statement.basic.model.function;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.util.NameUtil;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity {
	private Type type;
	
	private String paramName;
	private String value;
	
	private Function function;
	
	private String sqlId;
	private JSONObject sqlJson;
	
	public ParameterEntity() {
	}
	public ParameterEntity(JSONObject json) {
		setConfJson(json);
	}
	
	public ParameterEntity reload(JSONObject json){
		setConfJson(json);
		return this;
	}
	
	private void setConfJson(JSONObject json){
		this.type = Type.toValue(json.getString("type"));
		this.value = json.getString("value");
		this.paramName = json.getString("paramName");
		this.function = FunctionImpl.newInstance(json.getJSONObject("function"));
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
	}

	public String getSqlStatement() {
		switch(type){
			case VALUE:
				return value;
			case PARAMETER:
				return NameUtil.getName(null, paramName);
			case FUNCTION:
				return function.getSqlStatement();
			case SQL:
				StringBuilder sb = new StringBuilder(200);
				sb.append("( ");
				if(StrUtils.notEmpty(sqlId)){
					sb.append(SqlStatementBuilderContext.buildSqlStatement(sqlId));
				}else{
					SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
					infoBuilder.setJson(sqlJson);
					sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
				}
				sb.append(" )");
				return sb.toString();
		}
		throw new IllegalArgumentException("function type值异常");
	}
	
	private enum Type{
		VALUE,
		PARAMETER,
		FUNCTION,
		SQL;
		
		static Type toValue(String str){
			if(StrUtils.notEmpty(str)){
				str = str.trim().toUpperCase();
				for(Type type: Type.values()){
					if(str.equals(type.name())){
						return type;
					}
				}
			}
			return VALUE;
		}
	}
}
