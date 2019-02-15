package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.for_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_FOR extends LogicEntity{

	public SQLSERVER_FOR(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		
		
		
		return sb.toString();
	}
}
