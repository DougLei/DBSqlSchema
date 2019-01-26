package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_IMAGE extends BaseDataType{
	private SQLSERVER_IMAGE(){}
	private static final SQLSERVER_IMAGE instance = new SQLSERVER_IMAGE();
	public static final SQLSERVER_IMAGE newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "image";
	}

	protected int maxLength() {
		return 0;
	}
}
