package com.sql.statement.basic.model.function;

import com.alibaba.fastjson.JSONObject;


/**
 * 数据库函数
 * @author DougLei
 */
public interface BuiltinFunction extends Function{
	
	/**
	 * 初始化内置函数
	 * @param confJson
	 * @return
	 */
	BuiltinFunction init(JSONObject confJson);
}
