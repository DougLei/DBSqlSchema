package com.sql.impl.statement.complex.object.procedure.model;

import com.sql.enums.DataType;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDataType {
	protected DataType dataType;
	protected String dataTypeStr;
	
	public void setDataType(String dataType){
		this.dataType = DataType.toValue(dataType);
		if(this.dataType == DataType.USER_DEFINED){
			this.dataTypeStr = dataType.trim();
		}else{
			this.dataTypeStr = this.dataType.getDataType();
		}
	}
	
	public boolean isUserDefinedType(){
		return dataType.isUserDefinedType();
	}
	
	public String getDataType() {
		return dataTypeStr;
	}
}
