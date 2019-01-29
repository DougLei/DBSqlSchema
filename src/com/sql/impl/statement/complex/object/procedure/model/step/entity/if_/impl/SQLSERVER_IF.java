package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.impl;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.DBIfEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_IF extends DBIfEntity{

	public SQLSERVER_IF(List<ConditionGroup> groupList) {
		super(groupList);
	}

	public String getSqlStatement(String sqlStatement) {
		StringBuilder sb = new StringBuilder(sqlStatement.length() + conditionSqlStatement.length() + 100);
		sb.append("if ").append(conditionSqlStatement).append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end");
		return sb.toString();
	}
}
