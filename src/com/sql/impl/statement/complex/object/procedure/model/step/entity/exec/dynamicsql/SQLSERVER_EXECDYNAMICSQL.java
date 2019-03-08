package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;


/**
 * 
 * @author DougLei
 */
public class SQLSERVER_EXECDYNAMICSQL extends AbstractExecDynamicSql{

	public SQLSERVER_EXECDYNAMICSQL(DynamicSqlEntity dynamicSqlEntity, List<ConditionGroup> groupList, List<ExecParameter> execParameterList) {
		super(dynamicSqlEntity, groupList, execParameterList);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		processExecuteDynamicSql();
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append(executeDynamicSql).append(newline());
			sb.append("end");
		}else{
			sb.append(executeDynamicSql);
		}
		sb.append(newline());
		return sb.toString();
	}

	protected void processExecuteDynamicSql() {
		
	}
}
