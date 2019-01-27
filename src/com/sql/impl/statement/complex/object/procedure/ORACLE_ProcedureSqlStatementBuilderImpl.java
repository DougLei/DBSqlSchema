package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterEntity;
import com.sql.util.StrUtils;

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
			boolean isInputParameter = false;
			for(int i=0;i<size;i++){
				parameter = parameterEntityList.get(i);
				if(parameter.isSupportAppendCustomSqlStatement()){
					sb.append(parameter.getName()).append(" ");
					if(parameter.isIN()){
						sb.append("in ");
						isInputParameter = true;
					}else if(parameter.isOUT()){
						sb.append("out ");
						isInputParameter = false;
					}else{
						sb.append("in out ");
						isInputParameter = false;
					}
					
					if(parameter.isBaseType()){
						sb.append(parameter.getBaseDataType());
						if(isInputParameter && StrUtils.notEmpty(parameter.getDefaultValue())){
							sb.append(" :=").append(parameter.getDefaultValue());
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
			
			sb.append(")").append(newline());
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
					if(declare.isBaseType()){
						sb.append(declare.getName()).append(" ");
						sb.append(declare.getBaseDataType());
						if(declare.getLength() > 0){
							sb.append("(").append(declare.getLength());
							if(declare.getPrecision() > -1){
								sb.append(", ").append(declare.getPrecision());
							}
							sb.append(")");
						}
						if(StrUtils.notEmpty(declare.getDefaultValue())){
							sb.append(" :=").append(declare.getDefaultValue());
						}
					}else{
						sb.append(declare.getAppendCustomSqlStatement(declare.getName()));
					}
					sb.append(";");
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
		return newline()+";"+newline();
	}
}
