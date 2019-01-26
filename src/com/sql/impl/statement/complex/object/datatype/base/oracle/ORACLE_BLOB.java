package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_BLOB extends BaseDataType{
	private ORACLE_BLOB(){}
	private static final ORACLE_BLOB instance = new ORACLE_BLOB();
	public static final ORACLE_BLOB newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "blob";
	}

	protected int maxLength() {
		return 0;
	}
}
