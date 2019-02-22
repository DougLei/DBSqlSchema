package com.sql.impl.statement.complex.object.procedure.model.param;

import com.sql.impl.statement.complex.object.procedure.model.CreateTypeContext;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ORACLE_Parameter implements DBParameter{

	public String toParameterSqlStatement(boolean isFirst, boolean isEnd, ParameterEntity parameter) {
		boolean isInputParameter = false;
		
		StringBuilder sb = new StringBuilder(50);
		if(isFirst){
			sb.append("(\n");
		}
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
		}
		if(isEnd){
			sb.append("\n)");
		}
		if(parameter.isCreateType()){
			CreateTypeContext.recordCreateTypeSqlStatement(parameter.getCreateTypeSqlStatement());
		}
		return sb.toString();
	}
}
