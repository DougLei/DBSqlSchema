package com.sql.impl.statement.complex.object.procedure.model.step.entity.break_;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_BREAK extends LogicEntity{

	public SQLSERVER_BREAK(ConditionEntity condition) {
		super(condition, true);
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
