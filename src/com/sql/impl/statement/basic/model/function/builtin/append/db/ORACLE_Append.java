package com.sql.impl.statement.basic.model.function.builtin.append.db;

import com.sql.impl.statement.basic.model.function.builtin.append.Append;


/**
 * 
 * @author DougLei
 */
public class ORACLE_Append extends Append{

	protected String appendParameter(String parameter, boolean isLastParameter) {
		return parameter + (isLastParameter?"":"||");
	}
}
