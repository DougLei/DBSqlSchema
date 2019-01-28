package com.sql.statement.complex.object.procedure.model.step;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entityfactory.SetValueStepEntityFactory;
import com.sql.impl.statement.complex.object.procedure.model.step.entityfactory.SqlStepEntityFactory;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 步骤类型
 * @author DougLei
 */
public enum StepType {
	SQL(SqlStepEntityFactory.newInstance()),
	SET_VALUE(SetValueStepEntityFactory.newInstance()),
//	IF(),
//	IF_ELSE(),
//	FOR(),
//	WHILE(),
	;
	
	private StepEntityFactory factory;
	private StepType(StepEntityFactory factory) {
		this.factory = factory;
	}

	public static StepType toValue(String str){
		try {
			return StepType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(StepType.values())+"]");
		}
	}
	
	public String toString(){
		return name();
	}

	public StepEntity buildStepEntity(Step step, JSONObject content) {
		StepEntity stepEntity = factory.buildEntity(content.getJSONArray("content"));
		stepEntity.setStepId(step.getId());
		stepEntity.setDesc(step.getDesc());
		return stepEntity;
	}
}
