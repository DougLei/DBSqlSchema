package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.ArrayList;
import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBElseEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBIfEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBIfelseEntity;
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

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		
		IfEntity if1 = ifEntityList.remove(0);
		sb.append(getDBIfEntity(if1.getGroupList()).getSqlStatement(if1.getContent()));
		
		IfEntity ifelse = null;
		for (int i = 0; i < ifEntityList.size(); i++) {
			if(i == ifEntityList.size()-1){
				break;
			}
			ifelse = ifEntityList.remove(i);
			sb.append(getDBIfelseEntity(ifelse.getGroupList()).getSqlStatement(ifelse.getContent()));
			i--;
		}
		
		IfEntity else1 = ifEntityList.remove(0);
		sb.append(getDBElseEntity(else1.getGroupList()).getSqlStatement(else1.getContent()));
		return sb.toString();
	}
	
	private DBIfEntity getDBIfEntity(List<ConditionGroup> groupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_IF(groupList);
			case ORACLE:
				return new ORACLE_IF(groupList);
		}
		return null;
	}
	private DBIfelseEntity getDBIfelseEntity(List<ConditionGroup> groupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_IFELSE(groupList);
			case ORACLE:
				return new ORACLE_IFELSE(groupList);
		}
		return null;
	}
	private DBElseEntity getDBElseEntity(List<ConditionGroup> groupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_ELSE(groupList);
			case ORACLE:
				return new ORACLE_ELSE(groupList);
		}
		return null;
	}
	

	public void addIfEntity(IfEntity ifEntity) {
		ifEntityList.add(ifEntity);
	}
}