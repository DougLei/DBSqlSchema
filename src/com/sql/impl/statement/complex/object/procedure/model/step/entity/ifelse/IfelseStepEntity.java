package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class IfelseStepEntity extends AbstractStepEntity {
	
	private List<ConditionEntity> conditionEntityList = new ArrayList<ConditionEntity>();
	
	public StepType getStepType() {
		return StepType.IF_ELSE;
	}

	/**
	 * 标识if-else语句是否结束
	 * @param ifEntityList
	 * @return
	 */
	private boolean isEnd(){
		return conditionEntityList.size() == 0;
	}
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		
		ConditionEntity if1 = conditionEntityList.remove(0);
		sb.append(getIFEntity(if1).getSqlStatement(isEnd(), if1.getContent()));
		
		ConditionEntity ifelse = null;
		for (int i = 0; i < conditionEntityList.size(); i++) {
			if(i == conditionEntityList.size()-1){
				break;
			}
			ifelse = conditionEntityList.remove(i);
			sb.append(getELSEIFEntity(ifelse).getSqlStatement(false, ifelse.getContent()));
			i--;
		}
		
		if(conditionEntityList.size() > 0){
			ConditionEntity else1 = conditionEntityList.remove(0);
			if(else1.getIsExistsCondition() != null || (else1.getConditionGroupList() != null && else1.getConditionGroupList().size() > 0)){
				sb.append(getELSEIFEntity(else1).getSqlStatement(true, else1.getContent()));
			}else{
				sb.append(getELSEEntity(else1).getSqlStatement(true, else1.getContent()));
			}
		}
		sb.append(newline());
		return sb.toString();
	}
	
	/** if */
	private LogicEntity getIFEntity(ConditionEntity condition) {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_IF(condition);
			case ORACLE:
				return new ORACLE_IF(condition);
		}
		return null;
	}
	/** else if */
	private LogicEntity getELSEIFEntity(ConditionEntity condition) {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_IFELSE(condition);
			case ORACLE:
				return new ORACLE_IFELSE(condition);
		}
		return null;
	}
	/** else */
	private LogicEntity getELSEEntity(ConditionEntity condition) {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_ELSE(condition);
			case ORACLE:
				return new ORACLE_ELSE(condition);
		}
		return null;
	}
	
	public void addConditionEntity(ConditionEntity conditionEntity) {
		conditionEntityList.add(conditionEntity);
	}
}