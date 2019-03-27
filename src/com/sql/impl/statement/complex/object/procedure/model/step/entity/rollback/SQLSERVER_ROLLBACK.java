package com.sql.impl.statement.complex.object.procedure.model.step.entity.rollback;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ROLLBACK extends LogicEntity{

	public SQLSERVER_ROLLBACK(ConditionEntity condition) {
		super(condition, false);
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
