package com.sql.statement.basic.model.where;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author DougLei
 */
public interface ValueGroup {

	int size();

	String getValue(int index);

	void setValueEntity(JSONObject valueEntityJsonObject);
}
