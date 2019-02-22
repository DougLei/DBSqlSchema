package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.for_.ForStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class ForStepEntityFactory implements StepEntityFactory {
	private ForStepEntityFactory(){}
	private static final ForStepEntityFactory factory = new ForStepEntityFactory();
	public static ForStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		ForStepEntity forStepEntity = new ForStepEntity();
		forStepEntity.setConditionEntity(new ConditionEntity(stepContent.getJSONArray("condition"), stepContent.getJSONArray("content")));
		return forStepEntity;
	}
}
