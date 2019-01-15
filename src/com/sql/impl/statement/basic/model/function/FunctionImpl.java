package com.sql.impl.statement.basic.model.function;

import com.alibaba.fastjson.JSONArray;
import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class FunctionImpl extends BasicImpl implements Function {
	private String name;
	private String[] parameters;
	
	public static Function newInstance(String name, JSONArray paramJsonarray){
		if(StrUtils.notEmpty(name) && paramJsonarray!= null && paramJsonarray.size() > 0){
			String[] parameters = new String[paramJsonarray.size()];
			for(int i=0;i<paramJsonarray.size();i++){
				parameters[i] = paramJsonarray.getString(i);
			}
			return new FunctionImpl(name, parameters);
		}
		return null;
	}
	
	private FunctionImpl(String name, String[] parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	private FunctionImpl() {
	}

	protected String processSqlStatement() {
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

	public void setName(String name) {
		this.name = name;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
}
