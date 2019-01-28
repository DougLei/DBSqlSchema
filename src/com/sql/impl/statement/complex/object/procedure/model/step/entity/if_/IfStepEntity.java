package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class IfStepEntity extends AbstractStepEntity {

	public StepType getStepType() {
		return StepType.IF;
	}
}