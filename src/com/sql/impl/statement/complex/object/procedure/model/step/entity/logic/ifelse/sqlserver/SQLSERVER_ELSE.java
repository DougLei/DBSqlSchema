package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.ifelse.sqlserver;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ELSE extends LogicEntity{

	public SQLSERVER_ELSE(List<ConditionGroup> groupList) {
		super(groupList, false);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("else ").append(conditionSqlStatement).append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end").append(newline());
		return sb.toString();
	}
}
