package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.impl.statement.complex.object.procedure.model.param.InOut;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ProcedureSqlStatementBuilderImpl extends ProcedureSqlStatementBuilderImpl {

	public String coverSqlServerSql(String procedureName) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("if exists (select name from sysobjects where xtype = 'P' and name = N'").append(procedureName).append("')");
		sb.append(newline());
		sb.append("drop procedure ").append(procedureName);
		sb.append(newline());
		sb.append("go");
		sb.append(newline());
		return sb.toString();
	}

	public String coverOracleSql() {
		return "";
	}
	
	protected String getParameterSql() {
		List<ParameterEntity> parameterEntityList = getParameterEntityList();
		if(parameterEntityList != null && parameterEntityList.size() > 0){
			int size = parameterEntityList.size();
			StringBuilder sb = new StringBuilder(size*50);
			
			ParameterEntity parameter = null;
			for(int i=0;i<size;i++){
				parameter = parameterEntityList.get(i);
				if(parameter.isSupportAppendCustomSqlStatement()){
					sb.append("@").append(parameter.getName()).append(" ");
					
					if(parameter.isBaseType()){
						sb.append(parameter.getDataType());
						if(parameter.getLength() > 0){
							sb.append("(").append(parameter.getLength()).append(")");
						}
						if(StrUtils.notEmpty(parameter.getDefaultValue())){
							sb.append("=").append(parameter.getDefaultValue());
						}
						if(parameter.getInOut() == InOut.OUT){
							sb.append(" ").append("output");
						}
					}else{
						sb.append(parameter.getAppendCustomSqlStatement());
					}
					if(i<size-1){
						sb.append(",");
					}
					sb.append(newline());
				}
				if(parameter.isCreateType()){
					recordBeforeProcedureSqlStatement(parameter.getCreateTypeSqlStatement());
				}
			}
			return sb.toString();
		}
		return null;
	}

	protected String getDeclareSql() {
		List<DeclareEntity> declareEntityList = getDeclareEntityList();
		if(declareEntityList != null && declareEntityList.size() > 0){
			int size = declareEntityList.size();
			StringBuilder sb = new StringBuilder(size*50);
			
			DeclareEntity declare = null;
			for (int i = 0; i < size; i++) {
				declare = declareEntityList.get(i);
				if(declare.isSupportAppendCustomSqlStatement()){
					sb.append("declare @").append(declare.getName()).append(" ");
					
					if(declare.isBaseType()){
						sb.append(declare.getDataType());
						if(declare.getLength() > 0){
							sb.append("(").append(declare.getLength()).append(")");
						}
						if(StrUtils.notEmpty(declare.getDefaultValue())){
							sb.append(" =").append(declare.getDefaultValue());
						}
						
					}else{
						sb.append(declare.getAppendCustomSqlStatement());
					}
					sb.append(newline());
				}
				if(declare.isCreateType()){
					recordBeforeProcedureSqlStatement(declare.getCreateTypeSqlStatement());
				}
			}
			return sb.toString();
		}
		return null;
	}

	protected String linkNextSqlStatementToken() {
		return newline()+"go"+newline();
	}
}
