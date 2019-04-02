package com.sql.impl.statement.basic.model.function.builtin.add.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.append.db.ORACLE_Append;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE}, functionName = "_add")
public class ORACLE_Add extends ORACLE_Append{

	protected String appendParameter(String parameter, boolean isLastParameter) {
		return parameter + (isLastParameter?"":"+");
	}
}
