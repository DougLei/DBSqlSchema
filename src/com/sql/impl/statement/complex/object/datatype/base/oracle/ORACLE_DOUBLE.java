package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_DOUBLE extends BaseDataType{
	private ORACLE_DOUBLE(){}
	private static final ORACLE_DOUBLE instance = new ORACLE_DOUBLE();
	public static final ORACLE_DOUBLE newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "number";
	}

	protected int maxLength() {
		return 38;
	}
	
	protected int maxPrecision() {
		return 38;
	}
}
