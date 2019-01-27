package com.sql.statement.complex.object.procedure.model.step.entity;

import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public interface StepEntity {
	
	StepType getStepType();
	
	String getSqlStatement();

	String getStepId();

	String getDesc();

	void setStepId(String id);

	void setDesc(String desc);
}
