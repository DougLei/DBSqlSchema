package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue.impl;

import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue.SetValueEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_SetValueEntity extends SetValueEntity {

	protected String getVALUESqlStatement() {
		return "set @"+paramName+" =" + value;
	}

	protected String getFUNCTIONSqlStatement() {
		return "set @"+paramName+" =" + valueFunction.getSqlStatement();
	}

	protected String getSELECT_SQLSqlStatement() {
		StringBuilder sb = new StringBuilder(500);
		if(StrUtils.notEmpty(selectSqlId)){
			sb.append(SqlStatementBuilderContext.buildSqlStatement(selectSqlId));
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(selectSqlJson);
			sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
		}
		return "select @"+paramName+"=" + sb.substring(6);
	}
}