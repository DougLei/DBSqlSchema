package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.oracle;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db.DBIfEntity;

/**
 * 
 * @author DougLei
 */
public class ORACLE_IFELSE extends DBIfEntity{
	
	public ORACLE_IFELSE(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = new StringBuilder(sqlStatement.length() + conditionSqlStatement.length() + 100);
		sb.append("elsif ").append(conditionSqlStatement).append(" then").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		if(isEnd){
			sb.append("end if;").append(newline());
		}
		return sb.toString();
	}
}
