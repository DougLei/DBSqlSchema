package com.sql.impl.statement.complex.object.procedure;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder beforeProcedureSqlStatement;
	protected StringBuilder procedureSqlStatement = new StringBuilder(10000);
	
	public boolean isCover() {
		return content.getBoolean("isCover");
	}
	
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
		
		procedureSqlStatement.append("procedure ").append(procedureName);
		procedureSqlStatement.append(newline());
		
		// 处理参数
		String parameterSql = getParameterSql();
		if(parameterSql != null){
			procedureSqlStatement.append(parameterSql);
		}
		
		// as 关键字
		procedureSqlStatement.append("as ");
		procedureSqlStatement.append(newline());
		
		// 处理declare
		String declareSql = getDeclareSql();
		if(declareSql != null){
			procedureSqlStatement.append(declareSql);
		}
		
		procedureSqlStatement.append("begin").append(newline());
		
		List<Step> stepList = getStepList();
		if(stepList == null || stepList.size() == 0){
			throw new NullPointerException("存储过程的step(index)属性不能为空，至少有一项");
		}
		for (Step step : stepList) {
			procedureSqlStatement.append(step.getSqlStatement());
			procedureSqlStatement.append(newline());
		}
		
		procedureSqlStatement.append("end;");
		
		if(beforeProcedureSqlStatement != null && beforeProcedureSqlStatement.length() >0){
			procedureSqlStatement.insert(0, beforeProcedureSqlStatement);
		}
		return procedureSqlStatement.toString();
	}

	protected void recordBeforeProcedureSqlStatement(String sql){
		if(beforeProcedureSqlStatement == null){
			beforeProcedureSqlStatement = new StringBuilder(1000);
		}
		beforeProcedureSqlStatement.append(sql);
		beforeProcedureSqlStatement.append(linkNextSqlStatementToken());
	}
	
	/**
	 * 连接下一条sql语句的标识
	 * @return
	 */
	protected abstract String linkNextSqlStatementToken();
	
	/**
	 * 获取参数列表
	 * @return
	 */
	protected List<ParameterEntity> getParameterEntityList() {
		JSONArray array = content.getJSONArray("parameter");
		if(array != null && array.size() > 0){
			List<ParameterEntity> parameterList = new ArrayList<ParameterEntity>(array.size());
			JSONObject json = null;
			ParameterEntity parameter = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				parameter = new ParameterEntity(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.getString("inOut"), json.getString("defaultValue"));
				parameterList.add(parameter);
			}
			return parameterList;
		}
		return null;
	}
	
	/**
	 * 获取parameter参数sql语句
	 * @return 
	 */
	protected abstract String getParameterSql();
	
	/**
	 * 获取declare列表
	 * @return
	 */
	protected List<DeclareEntity> getDeclareEntityList() {
		JSONArray array = content.getJSONArray("declare");
		if(array != null && array.size() > 0){
			List<DeclareEntity> declareList = new ArrayList<DeclareEntity>(array.size());
			JSONObject json = null;
			DeclareEntity declare = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				declare = new DeclareEntity(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.getString("defaultValue"));
				if(declare.isCustomType()){
					declare.setCustomJson(json.getJSONObject("custom"));
				}
				declareList.add(declare);
			}
			return declareList;
		}
		return null;
	}
	
	/**
	 * 获取declare参数sql语句
	 * @return
	 */
	protected abstract String getDeclareSql();
	
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
}
