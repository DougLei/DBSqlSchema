package com.sql.impl.statement.basic.model.function.builtin.substring.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.substring.Substring;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE}, functionName = "_substring")
public class ORACLE_Substring extends Substring{

	public String getSqlStatement() {
		if(subLength == 0){
			return "substr("+parameterSqlStatement+", "+subIndex+")";
		}else{
			return "substr("+parameterSqlStatement+", "+subIndex+", " + subLength+")";
		}
	}
}
