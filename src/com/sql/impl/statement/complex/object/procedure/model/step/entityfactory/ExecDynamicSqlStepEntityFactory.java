package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.proc.ExecProcStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class ExecDynamicSqlStepEntityFactory implements StepEntityFactory {
	private ExecDynamicSqlStepEntityFactory(){}
	private static final ExecDynamicSqlStepEntityFactory factory = new ExecDynamicSqlStepEntityFactory();
	public static ExecDynamicSqlStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new ExecProcStepEntity(stepContent.getString("procedureName"), stepContent.getJSONArray("execParameter"), stepContent.getJSONArray("condition"));
	}
}
