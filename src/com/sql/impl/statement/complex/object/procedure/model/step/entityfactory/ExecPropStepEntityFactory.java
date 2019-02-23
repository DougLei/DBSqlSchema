package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.execprop.ExecProcStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class ExecPropStepEntityFactory implements StepEntityFactory {
	private ExecPropStepEntityFactory(){}
	private static final ExecPropStepEntityFactory factory = new ExecPropStepEntityFactory();
	public static ExecPropStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new ExecProcStepEntity(stepContent.getString("procedureName"), stepContent.getJSONArray("execParameter"), stepContent.getJSONArray("condition"));
	}
}
