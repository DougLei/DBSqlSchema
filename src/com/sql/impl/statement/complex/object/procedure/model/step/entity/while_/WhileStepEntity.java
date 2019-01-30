package com.sql.impl.statement.complex.object.procedure.model.step.entity.while_;

import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.while_.ORACLE_WHILE;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.while_.SQLSERVER_WHILE;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class WhileStepEntity extends AbstractStepEntity {
	private ConditionEntity conditionEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getWhileEntity(conditionEntity.getConditionGroupList()).getSqlStatement(true, conditionEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getWhileEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_WHILE(conditionGroupList);
			case ORACLE:
				return new ORACLE_WHILE(conditionGroupList);
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