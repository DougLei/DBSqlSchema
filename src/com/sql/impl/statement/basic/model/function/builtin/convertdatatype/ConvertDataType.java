package com.sql.impl.statement.basic.model.function.builtin.convertdatatype;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 转换数据类型
 * @author DougLei
 */
public abstract class ConvertDataType implements BuiltinFunction{
	protected Parameter sourceParameter;
	protected TargetDataType targetDataType;
	protected ConvertStyle style;
	
	public BuiltinFunction init(JSONObject confJson) {
		sourceParameter = new Parameter(confJson.getJSONObject("sourceParameter"));
		targetDataType = new TargetDataType(confJson.getJSONObject("targetDataType"));
		style = new ConvertStyle(confJson.getJSONObject("style"));
		return this;
	}

	public String getFunctionName() {
		return "_convert_data_type";
	}
}
