package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import java.util.Arrays;

/**
 * 
 * @author DougLei
 */
public enum CursorOpType{
	FETCH;
	
	public static CursorOpType toValue(String str){
		try {
			return CursorOpType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(CursorOpType.values())+"]");
		}
	}
	
	public String toString(){
		return name();
	}
}
