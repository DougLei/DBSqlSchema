package com.sql.impl.statement.complex.object.procedure.model.step.entity.for_;

import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class ForStepEntity extends AbstractStepEntity {
	private ConditionEntity conditionEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getForEntity(conditionEntity.getConditionGroupList()).getSqlStatement(true, conditionEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getForEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_FOR(conditionGroupList);
			case ORACLE:
				return new ORACLE_FOR(conditionGroupList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.FOR;
	}

	public void setConditionEntity(ConditionEntity conditionEntity) {
		this.conditionEntity = conditionEntity;
	}
}