package com.sql.impl.statement.basic.model.function.builtin.datetochar.db;

import com.sql.impl.statement.basic.model.function.builtin.datetochar.DateToChar;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_DateToChar extends DateToChar {

	protected String getDateStyle() {
		switch(dateStyleCode){
			case 1:
				return "120"; // yyyy-MM-dd HH24:mi:ss
			case 2:
				return "121"; // yyyy-MM-dd HH24:mi:ss.ff3
			case 3:
				return "111"; // yyyy/MM/dd
			case 4:
				return "108"; // HH24:mi:ss
			case 5:
				return "114"; // HH24:mi:ss:ff3
			case 6:
				return "112"; // yyyyMMdd
		}
		throw new IllegalArgumentException("目前系统只支持部分日期格式化的样式，如不满足需求，请联系系统开发人员");
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
		}
	throw new IllegalArgumentException("目前系统只支持部分日期格式化的样式，如不满足需求，请联系系统开发人员");
	}
	
	public String getSqlStatement() {
		return "convert(varchar("+getDateStyleLength()+"), "+sourceParameterSqlStatement+", "+getDateStyle()+")";
	}
}
