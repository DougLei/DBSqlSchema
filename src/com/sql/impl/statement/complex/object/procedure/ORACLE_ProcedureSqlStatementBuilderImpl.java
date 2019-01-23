package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.param.InOut;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterEntity;

/**
 * 
 * @author DougLei
 */
public class ORACLE_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl {

	public String coverSqlServerSql(String procedureName) {
		return "";
	}

	public String coverOracleSql() {
		return "or replace ";
	}
	
	protected String getParameterSql() {
		List<ParameterEntity> parameterEntityList = super.getParameterEntityList();
		if(parameterEntityList != null && parameterEntityList.size() > 0){
			int size = parameterEntityList.size();
			StringBuilder sb = new StringBuilder(size*50);
			sb.append("(").append(newline());
			
			ParameterEntity parameter = null;
			for(int i=0;i<size;i++){
				parameter = parameterEntityList.get(i);
				sb.append(parameter.getName());
				sb.append(" ");
				if(parameter.getInOut() == InOut.IN){
					sb.append("in ");
				}else if(parameter.getInOut() == InOut.OUT){
					sb.append("out ");
				}else{
					sb.append("in out ");
				}
				sb.append(parameter.getDataType());
				if(i<size-1){
					sb.append(",");
				}
				sb.append(newline());
			}
			
			sb.append(")").append(newline());
			return sb.toString();
		}
		return null;
	}
}
