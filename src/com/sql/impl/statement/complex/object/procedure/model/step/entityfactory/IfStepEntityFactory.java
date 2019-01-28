package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONArray;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class IfStepEntityFactory implements StepEntityFactory {
	private IfStepEntityFactory(){}
	private static final IfStepEntityFactory factory = new IfStepEntityFactory();
	public static IfStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONArray array) {
		
		
		
		return null;
	}
}
