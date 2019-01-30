package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.return_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_RETURN extends LogicEntity{

	public SQLSERVER_RETURN(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		boolean sqlStatementNotNull = StrUtils.notEmpty(sqlStatement);
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append("return");
			if(sqlStatementNotNull){
				sb.append(" ").append(sqlStatement);
			}
			sb.append(newline());
			sb.append("end");
		}else{
			sb.append("return");
			if(sqlStatementNotNull){
				sb.append(" ").append(sqlStatement);
			}
		}
		sb.append(newline());
		return sb.toString();
	}
}
