package com.sql.impl.statement.complex.object.procedure.model.step.entity.break_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_BREAK extends LogicEntity{

	public ORACLE_BREAK(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("exit");
		if(conditionSqlStatement.length() > 0){
			sb.append(" when ").append(conditionSqlStatement);
		}
		sb.append(";").append(newline());
		return sb.toString();
	}
}
