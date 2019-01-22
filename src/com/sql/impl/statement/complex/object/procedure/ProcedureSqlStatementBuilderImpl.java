package com.sql.impl.statement.complex.object.procedure;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.param.Parameter;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder procedureSqlStatement = new StringBuilder(10000);
	
	protected String buildSql() {
		String procedureName = content.getString("procedureName");
		if(StrUtils.isEmpty(procedureName)){
			throw new NullPointerException("procedureName属性值不能为空");
		}
		
		boolean isCover = isCover();
		if(isCover){
			procedureSqlStatement.append(coverSqlServerSql(procedureName));
		}
		procedureSqlStatement.append("create ");
		if(isCover){
			procedureSqlStatement.append(coverOracleSql());
		}
		
		procedureSqlStatement.append("procedure ");
		procedureSqlStatement.append(newline());
		
		// 处理参数
		List<Parameter> parameterList = getParameterList();
		if(parameterList != null && parameterList.size() > 0){
			for(int i=0;i<parameterList.size();i++){
				procedureSqlStatement.append(parameterList.get(i).getSqlStatement());
				if(i<parameterList.size()-1){
					procedureSqlStatement.append(",");
				}
				procedureSqlStatement.append(newline());
			}
		}
		
		// as 关键字
		procedureSqlStatement.append("as ");
		procedureSqlStatement.append(newline());
		
		List<Step> stepList = getStepList();
		if(stepList == null || stepList.size() == 0){
			throw new NullPointerException("存储过程的step(index)属性不能为空，至少有一项");
		}
		for (Step step : stepList) {
			procedureSqlStatement.append(step.getSqlStatement());
			procedureSqlStatement.append(newline());
		}
		
		return procedureSqlStatement.toString();
	}
	
	/**
	 * 获取参数列表
	 * @return
	 */
	protected List<Parameter> getParameterList() {
		JSONArray array = content.getJSONArray("parameter");
		if(array != null && array.size() > 0){
			List<Parameter> parameterList = new ArrayList<Parameter>(array.size());
			JSONObject json = null;
			Parameter parameter = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				parameter = new ParameterImpl();
				parameter.setName(json.getString("name"));
				parameter.setDataType(json.getString("dataType"));
				parameter.setLength(json.getIntValue("length"));
				parameter.setInOut(json.getString("inOut"));
				parameterList.add(parameter);
			}
		}
		return null;
	}
	
	/**
	 * 获取step列表
	 * @return
	 */
	protected List<Step> getStepList() {
		List<Step> stepList = new ArrayList<Step>(20);
		
		int stepIndex = 1;
		JSONObject json = null;
		Step step = null;
		while((json=content.getJSONObject("step"+stepIndex)) != null){
			step = new StepImpl();
			step.setJson(stepIndex, json);
			stepList.add(step);
			stepIndex++;
		}
		
		if(stepList.size() == 0){
			return null;
		}
		return stepList;
	}

	public boolean isCover() {
		return content.getBoolean("isCover");
	}
}
