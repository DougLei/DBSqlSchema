package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.break_.BreakStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class BreakStepEntityFactory implements StepEntityFactory {
	private BreakStepEntityFactory(){}
	private static final BreakStepEntityFactory factory = new BreakStepEntityFactory();
	public static BreakStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new BreakStepEntity(stepContent.getJSONObject("isExistsCondition"), stepContent.getJSONArray("condition"));
	}
}
