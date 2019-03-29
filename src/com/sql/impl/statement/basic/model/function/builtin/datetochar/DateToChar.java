package com.sql.impl.statement.basic.model.function.builtin.datetochar;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 日期类型转换到字符串类型
 * @author DougLei
 */
public abstract class DateToChar implements BuiltinFunction{
	protected String sourceParameterSqlStatement;
	protected int dateStyleCode;
	
	public BuiltinFunction init(JSONObject confJson) {
		sourceParameterSqlStatement = new Parameter(confJson.getJSONObject("sourceParameter")).getSqlStatement();
		dateStyleCode = confJson.getIntValue("dateStyleCode");
		return this;
	}
	
	protected abstract String getDateStyle();
	protected abstract int getDateStyleLength();
	
	
	/*
		1: yyyy-MM-dd HH:mi:ss (24h)
		2: yyyy-MM-dd HH:mi:ss.SSS (24h)
		3: yyyy/MM/dd
		4: HH:mi:ss (24h)
		5: HH:mi:ss:SSS (24h)
		6: yyyyMMdd
	 */
}
