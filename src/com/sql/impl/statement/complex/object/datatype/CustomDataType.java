package com.sql.impl.statement.complex.object.datatype;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义的数据类型
 * @author DougLei
 */
public interface CustomDataType {

	/**
	 * 获取创建类型的sql语句
	 * @param customJson
	 * @return
	 */
	String getCreateTypeSqlStatement(JSONObject customJson);

	/**
	 * 获取要追加到整个sql语句中，要呈现的sql语句内容
	 * @param customJson
	 * @return
	 */
	String getAppendCustomSqlStatement(JSONObject customJson);

	/**
	 * 是否支持创建类型
	 * @return
	 */
	boolean isSupportCreateType();

	/**
	 * 是否支持追加自定义的sql语句
	 * @return
	 */
	boolean isSupportAppendCustomSqlStatement();
}
