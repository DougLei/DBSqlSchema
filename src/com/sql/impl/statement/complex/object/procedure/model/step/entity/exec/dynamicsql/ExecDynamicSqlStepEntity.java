package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

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
public class ExecDynamicSqlStepEntity extends AbstractExecStepEntity {
	private DynamicSqlEntity dynamicSqlEntity;
	
	public ExecDynamicSqlStepEntity(JSONObject dynamicSqlEntity, JSONObject isExistsCondition, JSONArray execParameter, JSONArray condition) {
		super(isExistsCondition, execParameter, condition);
		validExecParameter();
		this.dynamicSqlEntity = new DynamicSqlEntity(dynamicSqlEntity);
	}

	private void validExecParameter() {
		if(execParameterList != null && execParameterList.size() > 0){
			for(int i=0;i<execParameterList.size();i++){
				if(!execParameterList.get(i).isParameter()){
					throw new IllegalArgumentException("执行动态sql时，系统目前只支持解析parameter类型的execParameter参数配置");
				}
			}
		}
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(getExecDynamicSqlEntity().getSqlStatement(false, null));
		sb.append(newline());
		return sb.toString();
	}

	private LogicEntity getExecDynamicSqlEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_EXECDYNAMICSQL(dynamicSqlEntity, conditionEntity, execParameterList);
			case ORACLE:
				return new ORACLE_EXECDYNAMICSQL(dynamicSqlEntity, conditionEntity, execParameterList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.EXEC_DYNAMIC_SQL;
	}
}
