package com.sql.impl.statement.datatype;

import com.alibaba.fastjson.JSONObject;

/**
 * 自定义的数据类型
 * @author DougLei
 */
public interface CustomDataType {

	/**
	 * 获取自定义的sql语句
	 * @param customJson
	 * @return
	 */
	String getCustomSqlStatement(JSONObject customJson);

}
