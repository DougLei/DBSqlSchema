package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_CHAR extends BaseDataType{
	private SQLSERVER_CHAR(){}
	private static final SQLSERVER_CHAR instance = new SQLSERVER_CHAR();
	public static final SQLSERVER_CHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "char";
	}

	protected int maxLength() {
		return 8000;
	}
}
