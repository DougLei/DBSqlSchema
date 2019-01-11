package com.sql.statement.model.where;

import java.util.Arrays;

/**
 * 数据操作类型
 * @author DougLei
 */
public enum DataOperatorType {
	
	BETWEEN("between"),
	NOT_BETWEEN("not between"),
	LIKE("like"),
	NOT_LIKE("not like"),
	IN("in"),
	NOT_IN("not in"),
	EQ("="),
	NE("!="),
	GE(">="),
	GT(">"),
	LE("<="),
	LT("<");
	
	private String sqlStatement;
	private DataOperatorType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	public static DataOperatorType toValue(String str){
		try {
			return DataOperatorType.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(DataOperatorType.values())+"]");
		}
	}
	
	public String getSqlStatement(String[] parameters) {
		if(parameters == null || parameters.length == 0){
			return sqlStatement;
		}
		DataOperatorType dot = this;
		
		StringBuilder sb = new StringBuilder(100);
		if(dot == BETWEEN || dot == NOT_BETWEEN){
			sb.append(dot.getSqlStatement()).append(" ").append(parameters[0]).append(" and ").append(parameters[1]);
		}else if(dot == LIKE || dot == NOT_LIKE){
			sb.append(dot.getSqlStatement()).append(" ").append(parameters[0]);
		}else if(dot == IN || dot == NOT_IN){
			sb.append(dot.getSqlStatement()).append(" (");
			for(int i=0;i<parameters.length;i++){
				sb.append(parameters[i]);
				if(i<parameters.length-1){
					sb.append(", ");
				}
			}
			sb.append(")");
		}else{
			sb.append(dot.getSqlStatement()).append(" ").append(parameters[0]);
		}
		return sb.toString();
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	
	public String toString(){
		return "{"+sqlStatement+"}";
	}
}
