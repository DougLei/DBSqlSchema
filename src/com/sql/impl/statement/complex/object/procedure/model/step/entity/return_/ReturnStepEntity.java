package com.sql.impl.statement.complex.object.procedure.model.step.entity.return_;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class ReturnStepEntity extends AbstractStepEntity {

	private ConditionEntity conditionEntity;
	private ReturnEntity returnEntity;
	
	public ReturnStepEntity(JSONArray condition, JSONObject returnEntity) {
		conditionEntity = new ConditionEntity(condition, null);
		this.returnEntity = new ReturnEntity(returnEntity);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getReturnEntity(conditionEntity.getConditionGroupList()).getSqlStatement(false, returnEntity.getSqlStatement()));
		sb.append(newline());
		return sb.toString();
	}

	
	private LogicEntity getReturnEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_RETURN(conditionGroupList);
			case ORACLE:
				return new ORACLE_RETURN(conditionGroupList);
		}
		return null;
	}
	
	
	
	
	public StepType getStepType() {
		return StepType.RETURN;
	}
}
