package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.AbstractExecStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class ExecDynamicSqlStepEntity extends AbstractExecStepEntity {
	private DynamicSqlEntity dynamicSqlEntity;
	
	public ExecDynamicSqlStepEntity(JSONObject dynamicSqlEntity, JSONArray execParameter, JSONArray condition) {
		super(execParameter, condition);
		this.dynamicSqlEntity = new DynamicSqlEntity(dynamicSqlEntity);
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getExecDynamicSqlEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	private LogicEntity getExecDynamicSqlEntity() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_EXECDYNAMICSQL(dynamicSqlEntity, conditionEntity.getConditionGroupList(), execParameterList);
			case ORACLE:
				return new ORACLE_EXECDYNAMICSQL(dynamicSqlEntity, conditionEntity.getConditionGroupList(), execParameterList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.EXEC_DYNAMIC_SQL;
	}
}
