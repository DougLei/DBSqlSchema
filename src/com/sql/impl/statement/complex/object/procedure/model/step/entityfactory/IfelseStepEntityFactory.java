package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.IfelseStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class IfelseStepEntityFactory implements StepEntityFactory {
	private IfelseStepEntityFactory(){}
	private static final IfelseStepEntityFactory factory = new IfelseStepEntityFactory();
	public static IfelseStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		IfelseStepEntity ifStepEntity = new IfelseStepEntity();
		
		int index = 1;
		JSONObject json = null;
		while((json = stepContent.getJSONObject("if"+index)) != null){
			ifStepEntity.addConditionEntity(new ConditionEntity(json.getJSONArray("condition"), json.getJSONArray("content")));
			index++;
		}
		return ifStepEntity;
	}
}
