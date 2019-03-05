package com.sql.impl.statement.basic.model.function.builtin.append;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_Append extends Append{

	protected String appendParameter(String parameter, boolean isLastParameter) {
		return parameter + (isLastParameter?"":"+");
	}
}
