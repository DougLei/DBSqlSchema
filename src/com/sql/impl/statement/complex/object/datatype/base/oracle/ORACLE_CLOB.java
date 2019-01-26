package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_CLOB extends BaseDataType{
	private ORACLE_CLOB(){}
	private static final ORACLE_CLOB instance = new ORACLE_CLOB();
	public static final ORACLE_CLOB newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "clob";
	}

	protected int maxLength() {
		return 0;
	}
}
