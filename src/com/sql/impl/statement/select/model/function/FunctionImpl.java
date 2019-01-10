package com.sql.impl.statement.select.model.function;

import java.io.Serializable;

import com.sql.statement.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class FunctionImpl implements Function, Serializable {
	private static final long serialVersionUID = 5982397732296694795L;
	
	private String name;
	private String[] parameters;
	
	public FunctionImpl setName(String name) {
		this.name = name;
		return this;
	}
	public FunctionImpl setParameters(String... parameters) {
		this.parameters = parameters;
		return this;
	}
	
	/**
	 * 函数是否有效，有效则用函数的方式生成sql语句
	 * @return
	 */
	private boolean isValid(){
		if(StrUtils.notEmpty(name) && parameters != null && parameters.length > 0){
			return true;
		}
		return false;
	}

	public String getSqlStatement(String mainTableAlias, String columnName, String columnAlias) {
		if(isValid()){
			StringBuilder function = new StringBuilder(200);
			function.append(name).append("(");
			
			for(int i=0;i<parameters.length;i++){
				if(parameters[i].equals(columnName)){
					function.append(mainTableAlias);
				}
				function.append(parameters[i]);
				if(i<parameters.length-1){
					function.append(", ");
				}
			}
			
			function.append(")");
			if(StrUtils.notEmpty(columnAlias)){
				function.append(" ").append(columnAlias);
			}
			return function.toString();
		}
		return null;
	}
}
