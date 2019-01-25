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
				sb.append(parameter.getName());
				sb.append(" ");
				if(parameter.getInOut() == InOut.IN){
					sb.append("in ");
					isInputParameter = true;
				}else if(parameter.getInOut() == InOut.OUT){
					sb.append("out ");
					isInputParameter = false;
				}else{
					sb.append("in out ");
					isInputParameter = false;
				}
				sb.append(parameter.getDataType());
				
				if(!parameter.isCustomType() && isInputParameter && StrUtils.notEmpty(parameter.getDefaultValue())){
					sb.append(" :=").append(parameter.getDefaultValue());
				}
				
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

	protected String getDeclareSql() {
		List<DeclareEntity> declareEntityList = getDeclareEntityList();
		if(declareEntityList != null && declareEntityList.size() > 0){
			int size = declareEntityList.size();
			StringBuilder sb = new StringBuilder(size*50);
			
			DeclareEntity declare = null;
			for (int i = 0; i < size; i++) {
				declare = declareEntityList.get(i);
				sb.append(declare.getName()).append(" ").append(declare.getDataType());
				
				if(declare.isCustomType()){
					sb.append(declare.getCustomSqlStatement());
				}else{
					if(declare.getLength() > 0){
						sb.append("(").append(declare.getLength()).append(")");
					}
					if(StrUtils.notEmpty(declare.getDefaultValue())){
						sb.append(" :=").append(declare.getDefaultValue());
					}
					sb.append(";");
				}
				sb.append(newline());
			}
			return sb.toString();
		}
		return null;
	}

	protected String linkNextSqlStatementToken() {
		return newline()+";"+newline();
	}
}
