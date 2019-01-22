package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * declare
 * @author DougLei
 */
public class DeclareStepEntityFactory implements StepEntityFactory {
	private static final DeclareStepEntityFactory factory = new DeclareStepEntityFactory();
	
	private DeclareStepEntityFactory(){}
	public static final DeclareStepEntityFactory newInstance(){
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject content) {
		// TODO
		return null;
	}
}
