package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.commit.CommitStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class CommitStepEntityFactory implements StepEntityFactory {
	private CommitStepEntityFactory(){}
	private static final CommitStepEntityFactory factory = new CommitStepEntityFactory();
	public static CommitStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		return new CommitStepEntity(stepContent.getJSONObject("isExistsCondition"), stepContent.getJSONArray("condition"));
	}
}
