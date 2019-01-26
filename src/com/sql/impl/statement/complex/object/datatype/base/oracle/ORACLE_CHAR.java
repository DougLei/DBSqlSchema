package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_CHAR extends BaseDataType{
	private ORACLE_CHAR(){}
	private static final ORACLE_CHAR instance = new ORACLE_CHAR();
	public static final ORACLE_CHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "char";
	}

	protected int maxLength() {
		return 2000;
	}
}
