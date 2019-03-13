package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;


/**
 * 
 * @author DougLei
 */
public class SQLSERVER_SetValueEntity extends SetValueEntity {

	protected String getVALUESqlStatement() {
		return "set @"+paramNames[0]+" =" + value;
	}

	protected String getFUNCTIONSqlStatement() {
		return "set @"+paramNames[0]+" =" + function.getSqlStatement();
	}

	protected String getSQL_SetParamSql(String paramName) {
		return "@"+paramName+"=";
	}

	protected String getPARAMETERSqlStatement() {
		return "@"+ paramName;
	}
}
