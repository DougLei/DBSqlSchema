package com.sql.impl.statement.basic.model.function.builtin.datetochar.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.datetochar.DateToChar;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_datetochar")
public class SQLSERVER_DateToChar extends DateToChar {

	protected String getDateStyle() {
		switch(dateStyleCode){
			case 1:
				return "120"; 
			case 2:
				return "121";
			case 3:
				return "111";
			case 4:
				return "108";
			case 5:
				return "114";
			case 6:
				return "112";
			default:
				return "121";
		}
	}

	protected int getDateStyleLength() {
		switch(dateStyleCode){
			case 1:
				return 19;
			case 2:
				return 23;
			case 3:
				return 10;
			case 4:
				return 8;
			case 5:
				return 12;
			case 6:
				return 8;
			default:
				return 23;
		}
	}
	
	public String getSqlStatement() {
		return "convert(varchar("+getDateStyleLength()+"), "+sourceParameterSqlStatement+", "+getDateStyle()+")";
	}
}
