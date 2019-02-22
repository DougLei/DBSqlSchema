package com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ROLLBACK extends LogicEntity{

	public ORACLE_ROLLBACK(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
			sb.append("begin").append(newline());
			sb.append("rollback;").append(newline());
			sb.append("end if;");
		}else{
			sb.append("rollback;");
		}
		sb.append(newline());
		return sb.toString();
	}
}
