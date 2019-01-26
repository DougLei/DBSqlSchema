package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_SHORT extends BaseDataType{
	private SQLSERVER_SHORT(){}
	private static final SQLSERVER_SHORT instance = new SQLSERVER_SHORT();
	public static final SQLSERVER_SHORT newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "smallint";
	}

	protected int maxLength() {
		return 0;
	}
}
