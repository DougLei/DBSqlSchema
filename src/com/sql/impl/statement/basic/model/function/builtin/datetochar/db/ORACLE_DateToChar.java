package com.sql.impl.statement.basic.model.function.builtin.datetochar.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.datetochar.DateToChar;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE}, functionName = "_datetochar")
public class ORACLE_DateToChar extends DateToChar {

	protected String getDateStyle() {
		switch(dateStyleCode){
			case 1:
				return "yyyy-MM-dd HH24:mi:ss";
			case 2:
				return "yyyy-MM-dd HH24:mi:ss.ff3";
			case 3:
				return "yyyy/MM/dd";
			case 4:
				return "HH24:mi:ss";
			case 5:
				return "HH24:mi:ss:ff3";
			case 6:
				return "yyyyMMdd";
			default:
				return "yyyy-MM-dd HH24:mi:ss.ff3";
		}
	}

	protected int getDateStyleLength() {
		return 0;
	}
	
	public String getSqlStatement() {
		return "to_char(" + sourceParameterSqlStatement + ", "+ getDateStyle()+")";
	}
}
