package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.return_.ReturnStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class ReturnStepEntityFactory implements StepEntityFactory {
	private ReturnStepEntityFactory(){}
	private static final ReturnStepEntityFactory factory = new ReturnStepEntityFactory();
	public static ReturnStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new ReturnStepEntity(stepContent.getJSONArray("condition"), stepContent.getJSONObject("returnValue"));
	}
}
