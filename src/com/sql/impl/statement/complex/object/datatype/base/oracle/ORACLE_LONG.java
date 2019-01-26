package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_LONG extends BaseDataType{
	private ORACLE_LONG(){}
	private static final ORACLE_LONG instance = new ORACLE_LONG();
	public static final ORACLE_LONG newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "number";
	}

	protected int maxLength() {
		return 19;
	}
	
	protected int maxPrecision() {
		return 0;
	}
}
