package com.sql.impl.statement.complex.object.procedure.model.declare;

import com.sql.impl.statement.complex.object.procedure.model.DynamicCreateTypeContext;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_Declare implements DBDeclare{
	
	public String toDeclareSqlStatement(DeclareEntity declare) {
		StringBuilder sb = new StringBuilder(50);
		if(declare.isSupportAppendCustom()){
			sb.append("declare ");
			
			if(declare.isBaseType()){
				sb.append("@").append(declare.getName()).append(" ").append(declare.getBaseDataType());
				if(declare.getLength() > 0){
					sb.append("(").append(declare.getLength());
					if(declare.getPrecision() > -1){
						sb.append(", ").append(declare.getPrecision());
					}
					sb.append(")");
				}
				if(StrUtils.notEmpty(declare.getDefaultValue())){
					sb.append(" =").append(declare.getDefaultValue());
				}
				
			}else{
				sb.append(declare.getAppendCustomSqlStatement());
			}
		}
		if(declare.isSupportDynamicCreateType()){
			DynamicCreateTypeContext.addDynamicCreateType(declare.getName(), declare.getDynamicCreateTypeName(), declare.getDynamicCreateTypeSqlStatement(), declare.getDynamicDropTypeSqlStatement());
		}
		return sb.toString();
	}
}
