package com.sql.impl.statement.basic.model.function.builtin.datetochar.db;

import com.sql.impl.statement.basic.model.function.builtin.datetochar.DateToChar;

/**
 * 
 * @author DougLei
 */
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
		}
		throw new IllegalArgumentException("目前系统只支持部分日期格式化的样式，如不满足需求，请联系系统开发人员");
	}

	protected int getDateStyleLength() {
		return 0;
	}
	
	public String getSqlStatement() {
		return "to_char(" + sourceParameterSqlStatement + ", "+ getDateStyle()+")";
	}
}
