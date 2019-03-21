package com.sql.impl.statement.basic.model.values;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ValuesEntity {
	protected String value;
	protected String paramName;
	protected Function function;
	
	public ValuesEntity(JSONObject json) {
		this.value = StrUtils.isEmpty(json.getString("value"))?null:json.getString("value");
		this.paramName = StrUtils.isEmpty(json.getString("paramName"))?null:json.getString("paramName");
		this.function = FunctionImpl.newInstance(json.getJSONObject("valueFunction"));
	}

	public String getSqlStatement(){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = value;
		}
		if(sqlStatement == null){
			sqlStatement = Tools.getName(null, paramName);
		}
		return sqlStatement;
	}
}
