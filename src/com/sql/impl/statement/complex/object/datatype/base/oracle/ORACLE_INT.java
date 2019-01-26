package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_INT extends BaseDataType{
	private ORACLE_INT(){}
	private static final ORACLE_INT instance = new ORACLE_INT();
	public static final ORACLE_INT newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "number";
	}

	protected int maxLength() {
		return 10;
	}
	
	protected int maxPrecision() {
		return 0;
	}
}
