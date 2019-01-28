package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class SetValueStepEntity extends AbstractStepEntity {

	public StepType getStepType() {
		return StepType.SET_VALUE;
	}

	public void addSetValueEntity(JSONObject json) {
		SetValueEntity entity = SetValueEntity.getInstance(json);
		if(entity != null){
			addList(entity);
		}
	}
}
