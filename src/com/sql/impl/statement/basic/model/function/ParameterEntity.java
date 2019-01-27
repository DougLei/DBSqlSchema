package com.sql.impl.statement.basic.model.function;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity {
	private Type type;
	private String value;
	private String sqlId;
	private JSONObject sqlJson;
	
	public ParameterEntity(JSONObject json) {
		this.type = Type.toValue(json.getString("type"));
		this.value = json.getString("value");
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
	}

	public String getSqlStatement() {
		if(type == Type.VALUE){
			return value;
		}else if(type == Type.SQL){
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
