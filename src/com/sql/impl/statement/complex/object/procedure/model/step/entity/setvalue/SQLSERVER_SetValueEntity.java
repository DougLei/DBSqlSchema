package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;


/**
 * 
 * @author DougLei
 */
public class SQLSERVER_SetValueEntity extends SetValueEntity {

	protected String getVALUESqlStatement() {
		return "set @"+paramName[0]+" =" + value;
	}

	protected String getFUNCTIONSqlStatement() {
		return "set @"+paramName[0]+" =" + valueFunction.getSqlStatement();
	}

	protected String getSQL_SetParamSql(String paramName) {
		return "@"+paramName+"=";
	}
}
