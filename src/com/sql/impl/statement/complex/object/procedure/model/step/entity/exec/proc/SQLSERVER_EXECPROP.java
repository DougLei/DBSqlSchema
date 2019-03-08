package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.proc;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_EXECPROP extends AbstractExecProc{

	public SQLSERVER_EXECPROP(List<ConditionGroup> groupList, String procedureName, List<ExecParameter> execParameterList) {
		super(groupList, procedureName, execParameterList);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		processExecuteProcedureSql();
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append(executeProcedureSql).append(newline());
			sb.append("end");
		}else{
			sb.append(executeProcedureSql);
		}
		sb.append(newline());
		return sb.toString();
	}

	protected void processExecuteProcedureSql() {
		executeProcedureSql.append("exec ").append(procedureName).append(" ");
		if(execParameterList != null && execParameterList.size() > 0){
			for(int i=0;i<execParameterList.size();i++){
				executeProcedureSql.append(execParameterList.get(i).getParamSqlStatement());
				if(execParameterList.get(i).isOutParameter()){
					executeProcedureSql.append(" output");
				}
				if(i<execParameterList.size()-1){
					executeProcedureSql.append(", ");
				}
			}
		}
	}
}
