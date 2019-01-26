package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_SHORT extends BaseDataType{
	private ORACLE_SHORT(){}
	private static final ORACLE_SHORT instance = new ORACLE_SHORT();
	public static final ORACLE_SHORT newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "number";
	}

	protected int maxLength() {
		return 5;
	}
	
	protected int maxPrecision() {
		return 0;
	}
}
