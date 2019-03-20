package com.sql.impl.statement.complex.object.procedure.model.step.entity.break_;

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
public class BreakStepEntity extends AbstractStepEntity {

	private ConditionEntity conditionEntity;
	
	public BreakStepEntity(JSONObject isExistsCondition, JSONArray condition) {
		conditionEntity = new ConditionEntity(isExistsCondition, condition, null);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getBreakEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	
	private LogicEntity getBreakEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_BREAK(conditionEntity);
			case ORACLE:
				return new ORACLE_BREAK(conditionEntity);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.BREAK;
	}
}
