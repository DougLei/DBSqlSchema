package com.sql.impl.statement.basic.model.function.builtin.substring.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.substring.Substring;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_substring")
public class SQLSERVER_Substring extends Substring{

	public String getSqlStatement() {
		if(subIndex > 0){
			return "substring("+ parameterSqlStatement+", " + subIndex+", "+ subLength +")";
		}else{
			String right = "right("+ parameterSqlStatement+", " + Math.abs(subIndex) + ")";
			if(subLength == 0){
				return right;
			}else{
				return "left("+right+", "+subLength+")";
			}
		}
	}
}
