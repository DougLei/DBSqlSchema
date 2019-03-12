package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity extends AbstractDataType{
	
	public DeclareEntity(JSONObject json) {
		super(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.get("precision"), json.getString("inOut"), json.getString("defaultValue"));
		if(!isBaseType()){
			setCustomJson(json.getJSONObject("custom"));
		}
	}
}
