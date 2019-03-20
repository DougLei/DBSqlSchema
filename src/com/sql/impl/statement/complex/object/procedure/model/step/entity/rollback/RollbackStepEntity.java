package com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class RollbackStepEntity extends AbstractStepEntity {

	private ConditionEntity conditionEntity;
	
	public RollbackStepEntity(JSONObject isExistsCondition, JSONArray condition) {
		conditionEntity = new ConditionEntity(isExistsCondition, condition, null);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getRollbackEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	
	private LogicEntity getRollbackEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_ROLLBACK(conditionEntity);
			case ORACLE:
				return new ORACLE_ROLLBACK(conditionEntity);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.ROLLBACK;
	}
}
