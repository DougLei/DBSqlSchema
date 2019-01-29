package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.impl;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.DBIfEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_IF extends DBIfEntity{
	
	public ORACLE_IF(List<ConditionGroup> groupList) {
		super(groupList);
	}

	public String getSqlStatement(String sqlStatement) {
		StringBuilder sb = new StringBuilder(sqlStatement.length() + groupList.size() * 100);
		// TODO 
		
		
		
		
		
		
		
		return sb.toString();
	}
}
