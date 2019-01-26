package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_LONG extends BaseDataType{
	private SQLSERVER_LONG(){}
	private static final SQLSERVER_LONG instance = new SQLSERVER_LONG();
	public static final SQLSERVER_LONG newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "bigint";
	}

	protected int maxLength() {
		return 0;
	}
}
