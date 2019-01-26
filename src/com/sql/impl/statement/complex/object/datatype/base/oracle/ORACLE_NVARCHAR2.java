package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_NVARCHAR2 extends BaseDataType{
	private ORACLE_NVARCHAR2(){}
	private static final ORACLE_NVARCHAR2 instance = new ORACLE_NVARCHAR2();
	public static final ORACLE_NVARCHAR2 newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "nvarchar2";
	}

	protected int maxLength() {
		return 2000;
	}
}
