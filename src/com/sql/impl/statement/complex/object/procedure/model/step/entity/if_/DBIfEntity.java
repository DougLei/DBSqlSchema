package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public abstract class DBIfEntity{

	protected List<ConditionGroup> groupList;
	public DBIfEntity(List<ConditionGroup> groupList) {
		this.groupList = groupList;
	}

	public abstract String getSqlStatement(String sqlStatement);
	
	protected final char newline(){
		return '\n';
	}
}
