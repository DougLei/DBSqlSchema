package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.oracle;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBElseEntity;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ELSE extends DBElseEntity{
	
	public ORACLE_ELSE(List<ConditionGroup> groupList) {
		super(groupList);
	}

	public String getSqlStatement(String sqlStatement) {
		StringBuilder sb = new StringBuilder(sqlStatement.length() + conditionSqlStatement.length() + 100);
		sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end if;");
		return sb.toString();
	}
}
