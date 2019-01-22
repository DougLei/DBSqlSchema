package com.sql.statement.complex.object.procedure.model.step;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entityfactory.DeclareStepEntityFactory;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 步骤类型
 * @author DougLei
 */
public enum StepType {
	DECLARE("declare", DeclareStepEntityFactory.newInstance());
	
	private String type;
	private StepEntityFactory factory;
	private StepType(String type, StepEntityFactory factory) {
		this.type = type;
	}

	public static StepType toValue(String str){
		try {
			return StepType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(StepType.values())+"]");
		}
	}
	
	public String toString(){
		return "{" + type + "}";
	}

	public StepEntity buildStepEntity(String stepId, JSONObject content) {
		return factory.buildEntity(stepId, content);
	}
}