package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop.CursorEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop.CursorLoopStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class CursorLoopStepEntityFactory implements StepEntityFactory {
	private CursorLoopStepEntityFactory(){}
	private static final CursorLoopStepEntityFactory factory = new CursorLoopStepEntityFactory();
	public static CursorLoopStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		CursorLoopStepEntity cursorLoopStepEntity = new CursorLoopStepEntity();
		cursorLoopStepEntity.setCursorEntity(new CursorEntity(stepContent.getJSONObject("cursor"), stepContent.getJSONArray("content")));
		return cursorLoopStepEntity;
	}
}
