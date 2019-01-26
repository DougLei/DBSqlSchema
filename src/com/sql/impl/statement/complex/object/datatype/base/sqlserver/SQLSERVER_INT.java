package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_INT extends BaseDataType{
	private SQLSERVER_INT(){}
	private static final SQLSERVER_INT instance = new SQLSERVER_INT();
	public static final SQLSERVER_INT newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "int";
	}

	protected int maxLength() {
		return 0;
	}
}
