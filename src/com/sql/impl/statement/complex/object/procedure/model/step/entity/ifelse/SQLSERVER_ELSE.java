package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

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
