package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.proc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.AbstractExecStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class ExecProcStepEntity extends AbstractExecStepEntity {
	private String procedureName;
	
	public ExecProcStepEntity(String procedureName, JSONObject isExistsCondition, JSONArray execParameter, JSONArray condition) {
		super(isExistsCondition, execParameter, condition);
		this.procedureName = procedureName;
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getExecProcEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	private LogicEntity getExecProcEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_EXECPROP(conditionEntity, procedureName, execParameterList);
			case ORACLE:
				return new ORACLE_EXECPROP(conditionEntity, procedureName, execParameterList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.EXEC_PROC;
	}
}
