package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class SqlStepEntityFactory implements StepEntityFactory {
	private SqlStepEntityFactory(){}
	private static final SqlStepEntityFactory factory = new SqlStepEntityFactory();
	public static SqlStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject content) {
		return null;
	}
}
