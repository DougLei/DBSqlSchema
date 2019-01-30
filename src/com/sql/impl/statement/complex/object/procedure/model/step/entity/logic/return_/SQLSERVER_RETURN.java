package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.return_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_RETURN extends LogicEntity{

	public SQLSERVER_RETURN(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);

		
		
		return sb.toString();
	}
}
