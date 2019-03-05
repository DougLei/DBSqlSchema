package com.sql.impl.statement.basic.model.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.builtin.BuiltinFunctionContext;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.Function;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class FunctionImpl extends BasicModelImpl implements Function {
	/**函数名*/
	private String name;
	private ParameterEntity[] parameters;
	
	public static Function newInstance(JSONObject function){
		if(function != null && function.size() > 0){
			String name = function.getString("name");
			
			BuiltinFunction bfFunction = BuiltinFunctionContext.getBuiltinFunctionNewInstance(name);
			if(bfFunction != null){
				return bfFunction.init(function);
			}
			
			JSONArray paramJsonarray = function.getJSONArray("parameter");
			if(StrUtils.notEmpty(name) && paramJsonarray!= null && paramJsonarray.size() > 0){
				ParameterEntity[] parameters = new ParameterEntity[paramJsonarray.size()];
				for(int i=0;i<paramJsonarray.size();i++){
					parameters[i] = new ParameterEntity(paramJsonarray.getJSONObject(i));
				}
				return new FunctionImpl(name, parameters);
			}
		}
		return null;
	}
	
	private FunctionImpl(String name, ParameterEntity[] parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	protected String processSqlStatement() {
		StringBuilder function = new StringBuilder(200);
		function.append(name).append("(");
		
		if(parameters != null && parameters.length > 0){
			for(int i=0;i<parameters.length;i++){
				function.append(parameters[i].getSqlStatement());
				if(i<parameters.length-1){
					function.append(", ");
				}
			}
		}
		function.append(")");
		return function.toString();
	}
}
