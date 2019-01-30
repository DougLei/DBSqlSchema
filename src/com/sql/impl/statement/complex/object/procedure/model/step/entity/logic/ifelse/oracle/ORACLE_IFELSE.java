package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.ifelse.oracle;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_IFELSE extends LogicEntity{
	
	public ORACLE_IFELSE(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("elsif ").append(conditionSqlStatement).append(" then").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		if(isEnd){
			sb.append("end if;").append(newline());
		}
		return sb.toString();
	}
}
