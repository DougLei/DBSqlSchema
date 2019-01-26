package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_DATE extends BaseDataType{
	private SQLSERVER_DATE(){}
	private static final SQLSERVER_DATE instance = new SQLSERVER_DATE();
	public static final SQLSERVER_DATE newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "date";
	}

	protected int maxLength() {
		return 0;
	}
}
