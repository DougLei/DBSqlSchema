package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.AbstractDataType;

/**
 * 
 * @author DougLei
 */
public class DeclareEntity extends AbstractDataType{
	
	public DeclareEntity(JSONObject json) {
		super(json);
		if(!isBaseType()){
			setCustomJson(json.getJSONObject("custom"));
		}
	}
}
