package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_DATE extends BaseDataType{
	private ORACLE_DATE(){}
	private static final ORACLE_DATE instance = new ORACLE_DATE();
	public static final ORACLE_DATE newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "date";
	}

	protected int maxLength() {
		return 0;
	}
}
