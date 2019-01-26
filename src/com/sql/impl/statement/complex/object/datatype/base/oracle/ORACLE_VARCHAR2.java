package com.sql.impl.statement.complex.object.datatype.base.oracle;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class ORACLE_VARCHAR2 extends BaseDataType{
	private ORACLE_VARCHAR2(){}
	private static final ORACLE_VARCHAR2 instance = new ORACLE_VARCHAR2();
	public static final ORACLE_VARCHAR2 newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "varchar2";
	}

	protected int maxLength() {
		return 4000;
	}
}
