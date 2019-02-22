package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.while_.WhileStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class WhileStepEntityFactory implements StepEntityFactory {
	private WhileStepEntityFactory(){}
	private static final WhileStepEntityFactory factory = new WhileStepEntityFactory();
	public static WhileStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		WhileStepEntity whileStepEntity = new WhileStepEntity();
		whileStepEntity.setConditionEntity(new ConditionEntity(stepContent.getJSONArray("condition"), stepContent.getJSONArray("content")));
		return whileStepEntity;
	}
}
