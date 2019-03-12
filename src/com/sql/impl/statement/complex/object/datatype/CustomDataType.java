package com.sql.impl.statement.complex.object.datatype;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义的数据类型
 * @author DougLei
 */
public interface CustomDataType {

	/**
	 * 是否支持创建类型
	 * @return
	 */
	boolean isSupportCreateType();
	
	/**
	 * 获取创建类型的sql语句
	 * @param customJson
	 * @return
	 */
	String getCreateTypeSqlStatement(JSONObject customJson);

	// -----------------------------------------------------------------
	
	/**
	 * 是否支持追加自定义的sql语句
	 * @return
	 */
	boolean isSupportAppendCustom();
	
	/**
	 * 获取要追加到整个sql语句中，要呈现的sql语句内容
	 * @param name 
	 * @param customJson
	 * @return
	 */
	String getAppendCustomSqlStatement(String name, JSONObject customJson);

	// -----------------------------------------------------------------
	
	/**
	 * 是否支持动态创建类型的sql语句
	 * @return
	 */
	boolean isSupportDynamicCreateType();
	
	/**
	 * 获取动态创建类型的类型名称
	 * @param name
	 * @return
	 */
	String getDynamicCreateTypeName(String name);
	
	/**
	 * 获取动态创建类型的sql语句
	 * @param name 
	 * @param customJson
	 * @return
	 */
	String getDynamicCreateTypeSqlStatement(String name, JSONObject customJson);
	
	/**
	 * 获取动态drop类型的sql语句
	 * @param name 
	 * @param customJson
	 * @return
	 */
	String getDynamicDropTypeSqlStatement(String name, JSONObject customJson);
}
