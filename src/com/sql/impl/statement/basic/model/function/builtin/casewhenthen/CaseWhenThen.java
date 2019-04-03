package com.sql.impl.statement.basic.model.function.builtin.casewhenthen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.Parameter;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE, DatabaseType.SQLSERVER}, functionName = "_case_when_then")
public class CaseWhenThen implements BuiltinFunction{
	private String columnName;
	private List<String> conditions;
	private List<String> results;
	
	public BuiltinFunction init(JSONObject confJson) {
		columnName = confJson.getString("columnName");
		
		conditions = new ArrayList<String>();
		results = new ArrayList<String>();
		
		JSONObject match;
		int index = 1;
		while((match = confJson.getJSONObject("match" + index)) != null && match.size() > 0){
			conditions.add(new Parameter(match.getJSONObject("condition")).getSqlStatement());
			results.add(new Parameter(match.getJSONObject("result")).getSqlStatement());
			index++;
		}
		return this;
	}
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(200);
		sb.append(" case ").append(columnName);
		while(!conditions.isEmpty()){
			sb.append("\n  when ").append(conditions.remove(0)).append(" then ").append(results.remove(0));
		}
		if(!results.isEmpty()){
			sb.append("\n  else ").append(results.remove(0));
		}
		sb.append("\n end");
		return sb.toString();
	}
}
