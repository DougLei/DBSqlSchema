package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_DATETIME extends BaseDataType{
	private SQLSERVER_DATETIME(){}
	private static final SQLSERVER_DATETIME instance = new SQLSERVER_DATETIME();
	public static final SQLSERVER_DATETIME newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "datetime";
	}

	protected int maxLength() {
		return 0;
	}
}
