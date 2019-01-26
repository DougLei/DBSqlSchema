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
		if(length < 1){
			return 0;
		}
		if(length > maxLength()){
			length = maxLength();
		}
		return length;
	}
	
	public int calcPrecision(int precision){
		if(precision < 0){
			return -1;
		}
		if(precision > maxPrecision()){
			precision = maxPrecision();
		}
		return precision;
	}
	
	public abstract String dataTypeName();
	protected abstract int maxLength();
	
	protected int maxPrecision() {
		return -1;
	}
}
