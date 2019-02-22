package com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ROLLBACK extends LogicEntity{

	public SQLSERVER_ROLLBACK(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append("rollback").append(newline());
			sb.append("end");
		}else{
			sb.append("rollback");
		}
		sb.append(newline());
		return sb.toString();
	}
}
