package com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
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
public class RollbackStepEntity extends AbstractStepEntity {

	private ConditionEntity conditionEntity;
	
	public RollbackStepEntity(JSONArray condition) {
		conditionEntity = new ConditionEntity(condition, null);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getRollbackEntity(conditionEntity.getConditionGroupList()).getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	
	private LogicEntity getRollbackEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_ROLLBACK(conditionGroupList);
			case ORACLE:
				return new ORACLE_ROLLBACK(conditionGroupList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.ROLLBACK;
	}
}
