package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback.RollbackStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class RollbackStepEntityFactory implements StepEntityFactory {
	private RollbackStepEntityFactory(){}
	private static final RollbackStepEntityFactory factory = new RollbackStepEntityFactory();
	public static RollbackStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new RollbackStepEntity(stepContent.getJSONObject("isExistsCondition"), stepContent.getJSONArray("condition"));
	}
}
