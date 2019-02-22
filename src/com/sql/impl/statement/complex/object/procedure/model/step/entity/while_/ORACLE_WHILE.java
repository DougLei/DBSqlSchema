package com.sql.impl.statement.complex.object.procedure.model.step.entity.while_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_WHILE extends LogicEntity{

	public ORACLE_WHILE(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("while ").append(conditionSqlStatement).append(" loop").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end loop;").append(newline());
		return sb.toString();
	}
}
