package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_IFELSE extends LogicEntity{

	public SQLSERVER_IFELSE(ConditionEntity condition) {
		super(condition, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("else if (").append(conditionSqlStatement).append(")").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end").append(newline());
		return sb.toString();
	}
}
