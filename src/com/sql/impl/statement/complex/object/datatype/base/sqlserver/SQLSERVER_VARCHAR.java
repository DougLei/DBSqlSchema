package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_VARCHAR extends BaseDataType{
	private SQLSERVER_VARCHAR(){}
	private static final SQLSERVER_VARCHAR instance = new SQLSERVER_VARCHAR();
	public static final SQLSERVER_VARCHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "varchar";
	}

	protected int maxLength() {
		return 8000;
	}
}
