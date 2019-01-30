package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.break_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_BREAK extends LogicEntity{

	public SQLSERVER_BREAK(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append("break").append(newline());
			sb.append("end");
		}else{
			sb.append("break");
		}
		sb.append(newline());
		return sb.toString();
	}
}
