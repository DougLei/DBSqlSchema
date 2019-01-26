package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_NVARCHAR extends BaseDataType{
	private SQLSERVER_NVARCHAR(){}
	private static final SQLSERVER_NVARCHAR instance = new SQLSERVER_NVARCHAR();
	public static final SQLSERVER_NVARCHAR newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "nvarchar";
	}

	protected int maxLength() {
		return 4000;
	}
}
