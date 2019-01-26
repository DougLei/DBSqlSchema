package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_DOUBLE extends BaseDataType{
	private SQLSERVER_DOUBLE(){}
	private static final SQLSERVER_DOUBLE instance = new SQLSERVER_DOUBLE();
	public static final SQLSERVER_DOUBLE newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "decimal";
	}

	protected int maxLength() {
		return 38;
	}
	
	protected int maxPrecision() {
		return 38;
	}
}
