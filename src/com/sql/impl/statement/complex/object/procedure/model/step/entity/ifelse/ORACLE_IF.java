package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_IF extends LogicEntity{
	
	public ORACLE_IF(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		
		if(isEnd){
			sb.append("end if;").append(newline());
		}
		return sb.toString();
	}
}
