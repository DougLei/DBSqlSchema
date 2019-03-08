package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;

/**
 * 
 * @author DougLei
 */
public class ORACLE_EXECDYNAMICSQL extends AbstractExecDynamicSql{

	public ORACLE_EXECDYNAMICSQL(DynamicSqlEntity dynamicSqlEntity, List<ConditionGroup> groupList, List<ExecParameter> execParameterList) {
		super(dynamicSqlEntity, groupList, execParameterList);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		processExecuteDynamicSql();
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
			sb.append("begin").append(newline());
			sb.append(executeDynamicSql).append(newline());
			sb.append("end if;");
		}else{
			sb.append(executeDynamicSql);
		}
		sb.append(newline());
		return sb.toString();
	}

	protected void processExecuteDynamicSql() {
	}
}
