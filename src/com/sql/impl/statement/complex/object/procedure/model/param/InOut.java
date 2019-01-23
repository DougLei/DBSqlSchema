package com.sql.impl.statement.complex.object.procedure.model.param;

/**
 * 
 * @author DougLei
 */
public enum InOut {
	IN,
	OUT,
	INOUT;
	
	public static InOut toValue(String str){
		try {
			return InOut.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：[in, out, inout]");
		}
	}
}
