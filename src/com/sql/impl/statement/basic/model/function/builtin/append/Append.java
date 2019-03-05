package com.sql.impl.statement.basic.model.function.builtin.append;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.ParameterEntity;
import com.sql.statement.basic.model.function.BuiltinFunction;

/**
 * 
 * @author DougLei
 */
public abstract class Append implements BuiltinFunction{

	protected String[] parameters;
	
	public BuiltinFunction init(JSONObject confJson) {
		JSONArray parameterJsonArray = confJson.getJSONArray("parameters");
		if(parameterJsonArray != null && parameterJsonArray.size() > 0){
			ParameterEntity pe = new ParameterEntity();
			
			int size = parameterJsonArray.size();
			parameters = new String[size];
			
			for(int i=0;i<size;i++){
				parameters[i] = pe.reload(parameterJsonArray.getJSONObject(i)).getSqlStatement();
			}
		}
		return this;
	}

	public String getSqlStatement() {
		if(parameters != null && parameters.length > 0){
			int length = parameters.length;
			StringBuilder sb = new StringBuilder(length*40);
			for (int i=0;i<length;i++) {
				sb.append(appendParameter(parameters[i], (i == length-1)));
			}
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * 追加参数
	 * @param parameter
	 * @param isLastParameter
	 * @return
	 */
	protected abstract String appendParameter(String parameter, boolean isLastParameter);

	public String getFunctionName() {
		return "_append";
	}
}
