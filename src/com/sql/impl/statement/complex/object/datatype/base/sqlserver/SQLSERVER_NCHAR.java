package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_NCHAR extends BaseDataType{
	private SQLSERVER_NCHAR(){}
	private static final SQLSERVER_NCHAR instance = new SQLSERVER_NCHAR();
	public static final SQLSERVER_NCHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "nchar";
	}

	protected int maxLength() {
		return 4000;
	}
}
