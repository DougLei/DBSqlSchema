package com.sql.statement.complex.object.procedure.model.step;

import java.util.Arrays;

/**
 * 步骤类型
 * @author DougLei
 */
public enum StepType {
	DECLARE("declare");
	
	private String type;
	private StepType(String type) {
		this.type = type;
	}

	public static StepType toValue(String str){
		try {
			return StepType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(StepType.values())+"]");
		}
	}
	
	public String toString(){
		return "{" + type + "}";
	}
}
