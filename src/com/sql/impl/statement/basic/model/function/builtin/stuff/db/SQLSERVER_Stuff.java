package com.sql.impl.statement.basic.model.function.builtin.stuff.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.stuff.Stuff;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_stuff")
public class SQLSERVER_Stuff extends Stuff{

	public String getSqlStatement() {
		if(startIndex != -1 && subLength != -1){
			return "stuff(" + source + ", "+startIndex + ", "+ subLength +", " + replaceTarget + ")";
		}
		return "stuff(" + source + ", "+startIndexParam + ", "+ subLengthParam +", " + replaceTarget + ")";
	}
}
