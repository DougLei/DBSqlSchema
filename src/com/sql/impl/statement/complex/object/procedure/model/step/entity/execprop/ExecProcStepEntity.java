package com.sql.impl.statement.complex.object.procedure.model.step.entity.execprop;

import java.util.ArrayList;
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
public class ExecProcStepEntity extends AbstractStepEntity {
	private String procedureName;
	private ConditionEntity conditionEntity;
	private List<ExecParameter> execParameterList;
	
	public ExecProcStepEntity(String procedureName, JSONArray execParameter, JSONArray condition) {
		this.procedureName = procedureName;
		setExecParameterList(execParameter);
		conditionEntity = new ConditionEntity(condition, null);
	}

	private void setExecParameterList(JSONArray execParameter) {
		if(execParameter != null && execParameter.size()>0){
			int size = execParameter.size();
			execParameterList = new ArrayList<ExecParameter>(size);
			for(int i=0;i<size;i++){
				execParameterList.add(new ExecParameter(execParameter.getJSONObject(i)));
			}
		}
	}
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getExecProcEntity(conditionEntity.getConditionGroupList()).getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	private LogicEntity getExecProcEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_EXECPROP(conditionGroupList, procedureName, execParameterList);
			case ORACLE:
				return new ORACLE_EXECPROP(conditionGroupList, procedureName, execParameterList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.EXEC_PROC;
	}
}
