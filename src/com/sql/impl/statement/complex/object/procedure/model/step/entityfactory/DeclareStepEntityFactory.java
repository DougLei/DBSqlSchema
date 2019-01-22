package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.declare.DeclareStepEntity;
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
	
	public StepEntity buildEntity(String stepId, JSONObject content) {
		DeclareStepEntity entity = new DeclareStepEntity(stepId);

		JSONArray array = content.getJSONArray("declare");
		JSONObject json = null;
		for(int i=0;i<array.size();i++){
			json = array.getJSONObject(i);
			entity.addDeclare(json);
		}
		return entity;
	}
}
