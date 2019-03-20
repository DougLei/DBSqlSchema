package com.sql.impl.statement.complex.object.procedure.model.step.entity.commit;

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
public class CommitStepEntity extends AbstractStepEntity {

	private ConditionEntity conditionEntity;
	
	public CommitStepEntity(JSONObject isExistsCondition, JSONArray condition) {
		conditionEntity = new ConditionEntity(isExistsCondition, condition, null);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getCommitEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	
	private LogicEntity getCommitEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_COMMIT(conditionEntity);
			case ORACLE:
				return new ORACLE_COMMIT(conditionEntity);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.COMMIT;
	}
}
