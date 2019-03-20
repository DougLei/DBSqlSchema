package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.ExecParameter;


/**
 * 
 * @author DougLei
 */
public class SQLSERVER_EXECDYNAMICSQL extends AbstractExecDynamicSql{

	public SQLSERVER_EXECDYNAMICSQL(DynamicSqlEntity dynamicSqlEntity, ConditionEntity condition, List<ExecParameter> execParameterList) {
		super(dynamicSqlEntity, condition, execParameterList);
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
		executeDynamicSql.append("exec sp_executesql ");
		if(dynamicSqlEntity.isSqlStatement()){
			executeDynamicSql.append("N'").append(dynamicSqlEntity.getDynamicSqlStatement()).append("'");
		}else if(dynamicSqlEntity.isParameter()){
			executeDynamicSql.append("@").append(dynamicSqlEntity.getDynamicSqlParamName());
		}
		
		if(execParameterList != null && execParameterList.size() > 0){
			executeDynamicSql.append(",N'");
			
			int length = execParameterList.size();
			StringBuilder paramSb = new StringBuilder(50 * length);
			
			ExecParameter ep = null;
			for(int i=0;i<length;i++){
				ep = execParameterList.get(i);
				
				executeDynamicSql.append(ep.getParamSqlStatement());
				executeDynamicSql.append(" ").append(ep.getDataTypeSql());
				
				paramSb.append(ep.getParamSqlStatement());
				
				if(ep.isOutParameter()){
					executeDynamicSql.append(" output");
					paramSb.append(" output");
				}
				if(i<length-1){
					executeDynamicSql.append(", ");
					paramSb.append(", ");
				}
			}
			executeDynamicSql.append("', ").append(paramSb).append(";");
		}
	}
}
