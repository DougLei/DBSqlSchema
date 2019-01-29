package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.sqlserver;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBIfelseEntity;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_IFELSE extends DBIfelseEntity{

	public SQLSERVER_IFELSE(List<ConditionGroup> groupList) {
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
