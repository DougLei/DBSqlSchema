package com.sql.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public interface StepEntityFactory {

	/**
	 * 
	 * @param stepContent step的json对象
	 * @param content step中content属性中的数组对象
	 * @return
	 */
	StepEntity buildEntity(JSONObject stepContent, JSONArray content);
}
