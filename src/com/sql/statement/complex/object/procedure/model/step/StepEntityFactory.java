package com.sql.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author DougLei
 */
public interface StepEntityFactory {

	/**
	 * 
	 * @param content
	 * @return
	 */
	StepEntity buildEntity(JSONObject content);
}
