package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop.CursorOpStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class CursorOpStepEntityFactory implements StepEntityFactory {
	private CursorOpStepEntityFactory(){}
	private static final CursorOpStepEntityFactory factory = new CursorOpStepEntityFactory();
	public static CursorOpStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		CursorOpStepEntity cursorOpStepEntity = new CursorOpStepEntity();
		cursorOpStepEntity.setCursorName(stepContent.getString("cursorName"));
		cursorOpStepEntity.setOpType(stepContent.getString("opType"));
		cursorOpStepEntity.setConditionEntity(new ConditionEntity(stepContent.getJSONObject("isExistsCondition"), stepContent.getJSONArray("condition"), stepContent.getJSONArray("content")));
		return cursorOpStepEntity;
	}
}
