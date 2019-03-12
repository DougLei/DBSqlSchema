package com.sql.impl.statement.complex.object.procedure.model.param;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class ParameterEntity extends AbstractDataType{
	
	public ParameterEntity(JSONObject json) {
		super(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.get("precision"), json.getString("inOut"), json.getString("defaultValue"));
	}
}
