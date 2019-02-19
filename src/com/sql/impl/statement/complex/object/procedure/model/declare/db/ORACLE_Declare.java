package com.sql.impl.statement.complex.object.procedure.model.declare.db;

import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ORACLE_Declare implements DBDeclare{

	public String toDeclareSqlStatement(DeclareEntity declare) {
		StringBuilder sb = new StringBuilder(50);
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
		}
		return sb.toString();
	}
}
