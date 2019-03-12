package com.sql.impl.statement.complex.object.procedure.model.param;

import com.sql.impl.statement.complex.object.procedure.model.CreateTypeContext;
import com.sql.impl.statement.complex.object.procedure.model.DynamicCreateTypeContext;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_Parameter implements DBParameter{

	public String toParameterSqlStatement(boolean isFirst, boolean isEnd, ParameterEntity parameter) {
		StringBuilder sb = new StringBuilder(50);
		if(isFirst){
			sb.append("(\n");
		}
		if(parameter.isSupportAppendCustom()){
			sb.append("@").append(parameter.getName()).append(" ");
			
			if(parameter.isBaseType()){
				sb.append(parameter.getBaseDataType());
				if(parameter.getLength() > 0){
					sb.append("(").append(parameter.getLength());
					if(parameter.getPrecision() > -1){
						sb.append(", ").append(parameter.getPrecision());
					}
					sb.append(")");
				}
				if(StrUtils.notEmpty(parameter.getDefaultValue())){
					sb.append("=").append(parameter.getDefaultValue());
				}
				if(parameter.isOUT() || parameter.isIN_OUT()){
					sb.append(" ").append("output");
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
		if(parameter.isSupportDynamicCreateType()){
			DynamicCreateTypeContext.addDynamicCreateType(parameter.getName(), parameter.getDynamicCreateTypeName(), parameter.getDynamicCreateTypeSqlStatement(), parameter.getDynamicDropTypeSqlStatement());
		}
		return sb.toString();
	}
}
