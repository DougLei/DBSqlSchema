package com.sql.impl.statement.basic.model.function.builtin.append.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.append.Append;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;


/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_append")
public class SQLSERVER_Append extends Append{

	protected String appendParameter(String parameter, boolean isLastParameter) {
		return parameter + (isLastParameter?"":"+");
	}
}
