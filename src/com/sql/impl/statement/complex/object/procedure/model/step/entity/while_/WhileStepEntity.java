package com.sql.impl.statement.complex.object.procedure.model.step.entity.while_;

import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class WhileStepEntity extends AbstractStepEntity {
	private ConditionEntity conditionEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getWhileEntity().getSqlStatement(true, conditionEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getWhileEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_WHILE(conditionEntity);
			case ORACLE:
				return new ORACLE_WHILE(conditionEntity);
		}
		return null;
	}
	
	
	public StepType getStepType() {
		return StepType.WHILE;
	}

	public void setConditionEntity(ConditionEntity conditionEntity) {
		this.conditionEntity = conditionEntity;
	}
}