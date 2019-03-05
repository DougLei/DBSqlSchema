package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

/**
 * 
 * @author DougLei
 */
public class ORACLE_SetValueEntity extends SetValueEntity {

	protected String getVALUESqlStatement() {
		return paramNames[0] +":=" + value;
	}

	protected String getFUNCTIONSqlStatement() {
		return paramNames[0] +":=" + valueFunction.getSqlStatement();
	}

	protected String getSQL_SetParamSql(String paramName) {
		return paramName+" :=";
	}
	
	protected String getPARAMETERSqlStatement() {
		return paramName;
	}
}
