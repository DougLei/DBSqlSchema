package com.sql.impl.statement.complex.object.procedure;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.context.CreateTypeContext;
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
					CreateTypeContext.recordCreateTypeSqlStatement(parameter.getCreateTypeSqlStatement());
				}
			}
			
			sb.append(")").append(newline());
			return sb.toString();
		}
		return null;
	}

	// oracle的存储过程是默认开启事务的，不需要begin transaction;操作
	protected String beginTransaction() {
		return "";
	}
}
