package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.commit;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_COMMIT extends LogicEntity{

	public ORACLE_COMMIT(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
			sb.append("begin").append(newline());
			sb.append("commit;").append(newline());
			sb.append("end if;");
		}else{
			sb.append("commit;");
		}
		sb.append(newline());
		return sb.toString();
	}
}
