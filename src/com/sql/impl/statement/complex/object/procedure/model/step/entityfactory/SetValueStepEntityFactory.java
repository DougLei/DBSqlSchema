package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue.SetValueStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class SetValueStepEntityFactory implements StepEntityFactory {
	private SetValueStepEntityFactory(){}
	private static final SetValueStepEntityFactory factory = new SetValueStepEntityFactory();
	public static SetValueStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent, JSONArray content) {
		SetValueStepEntity setValueStepEntity = new SetValueStepEntity();
		for(int i=0;i<content.size();i++){
			setValueStepEntity.addSetValueEntity(content.getJSONObject(i));
		}
		return setValueStepEntity;
	}
}
