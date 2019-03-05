package com.sql.statement.basic.model.function;

import com.alibaba.fastjson.JSONObject;


/**
 * 数据库函数
 * @author DougLei
 */
public interface BuiltinFunction extends Function{
	BuiltinFunction init(JSONObject confJson);
	
	/**
	 * 获取函数名
	 * @return
	 */
	String getFunctionName();
}
