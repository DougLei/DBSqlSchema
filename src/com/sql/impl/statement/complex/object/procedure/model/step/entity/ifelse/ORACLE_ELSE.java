package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ELSE extends LogicEntity{
	
	public ORACLE_ELSE(ConditionEntity condition) {
		super(condition, false);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("else ").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end if;").append(newline());
		return sb.toString();
	}
}
