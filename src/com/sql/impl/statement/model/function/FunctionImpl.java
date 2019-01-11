package com.sql.impl.statement.model.function;

import com.sql.impl.statement.model.BasicImpl;
import com.sql.statement.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class FunctionImpl extends BasicImpl implements Function {
	private String name;
	private String[] parameters;
	
	
	
	public FunctionImpl(String name, String[] parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	public FunctionImpl() {
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

	protected String processSqlStatement() {
		if(isValid()){
			StringBuilder function = new StringBuilder(200);
			function.append(name).append("(");
			
			for(int i=0;i<parameters.length;i++){
				function.append(parameters[i]);
				if(i<parameters.length-1){
					function.append(", ");
				}
			}
			function.append(")");
			return function.toString();
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
}
