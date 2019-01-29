package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.ArrayList;
import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBIfEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.oracle.ORACLE_ELSE;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.oracle.ORACLE_IF;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.oracle.ORACLE_IFELSE;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.sqlserver.SQLSERVER_ELSE;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.sqlserver.SQLSERVER_IF;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.sqlserver.SQLSERVER_IFELSE;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class IfelseStepEntity extends AbstractStepEntity {
	
	private List<IfEntity> ifEntityList = new ArrayList<IfEntity>();
	
	public StepType getStepType() {
		return StepType.IF_ELSE;
	}

	/**
	 * 标识if-else语句是否结束
	 * @param ifEntityList
	 * @return
	 */
	private boolean isEnd(){
		return ifEntityList.size() == 0;
	}
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		
		IfEntity if1 = ifEntityList.remove(0);
		sb.append(getIFEntity(if1.getConditionGroupList()).getSqlStatement(isEnd(), if1.getContent()));
		
		IfEntity ifelse = null;
		for (int i = 0; i < ifEntityList.size(); i++) {
			if(i == ifEntityList.size()-1){
				break;
			}
			ifelse = ifEntityList.remove(i);
			sb.append(getELSEIFEntity(ifelse.getConditionGroupList()).getSqlStatement(false, ifelse.getContent()));
			i--;
		}
		
		if(ifEntityList.size() > 0){
			IfEntity else1 = ifEntityList.remove(0);
			if(else1.getConditionGroupList() != null && else1.getConditionGroupList().size() > 0){
				sb.append(getELSEIFEntity(else1.getConditionGroupList()).getSqlStatement(true, else1.getContent()));
			}else{
				sb.append(getELSEEntity(else1.getConditionGroupList()).getSqlStatement(true, else1.getContent()));
			}
		}
		return sb.toString();
	}
	
	/** if */
	private DBIfEntity getIFEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_IF(conditionGroupList);
			case ORACLE:
				return new ORACLE_IF(conditionGroupList);
		}
		return null;
	}
	/** else if */
	private DBIfEntity getELSEIFEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_IFELSE(conditionGroupList);
			case ORACLE:
				return new ORACLE_IFELSE(conditionGroupList);
		}
		return null;
	}
	/** else */
	private DBIfEntity getELSEEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_ELSE(conditionGroupList);
			case ORACLE:
				return new ORACLE_ELSE(conditionGroupList);
		}
		return null;
	}
	
	public void addIfEntity(IfEntity ifEntity) {
		ifEntityList.add(ifEntity);
	}
}