package com.sql.impl.statement.complex.object.procedure.model.step.entity;

import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractStepEntity implements StepEntity {

	private String stepId;
	private String desc;
	
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStepId() {
		return stepId;
	}
	public String getDesc() {
		return desc;
	}
}
