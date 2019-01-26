package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_NCHAR extends BaseDataType{
	private ORACLE_NCHAR(){}
	private static final ORACLE_NCHAR instance = new ORACLE_NCHAR();
	public static final ORACLE_NCHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "nchar";
	}

	protected int maxLength() {
		return 1000;
	}
}
