package com.sql.impl.statement.complex.object.datatype.base.sqlserver;

import com.sql.impl.statement.complex.object.datatype.base.BaseDataType;

/**
 * 
 * @author DougLei
 */
public final class SQLSERVER_TEXT extends BaseDataType{
	private SQLSERVER_TEXT(){}
	private static final SQLSERVER_TEXT instance = new SQLSERVER_TEXT();
	public static final SQLSERVER_TEXT newInstance(){
		return instance;
	}
	
	public String dataTypeName() {
		return "text";
	}

	protected int maxLength() {
		return 0;
	}
}
