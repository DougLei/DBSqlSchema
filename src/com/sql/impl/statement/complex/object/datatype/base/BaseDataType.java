package com.sql.impl.statement.complex.object.datatype.base;

/**
 * 
 * @author DougLei
 */
public abstract class BaseDataType {

	public String getSqlStatement() {
		return dataTypeName();
	}
	
	public int calcLength(int length){
		if(length < minLength()){
			return 0;
		}
		if(length > maxLength()){
			length = maxLength();
		}
		return length;
	}

	protected int minLength() {
		return 1;
	}
	
	public abstract String dataTypeName();
	protected abstract int maxLength();
}
