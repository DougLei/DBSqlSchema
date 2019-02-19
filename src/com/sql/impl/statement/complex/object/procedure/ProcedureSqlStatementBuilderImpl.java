package com.sql.impl.statement.complex.object.procedure;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareVariableContext;
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
	protected StringBuilder createTypeSqlStatement;// 在存储过程中使用的自定义类型，需要先创建出来，这里存储的是创建语句，最后要在创建存储过程语句之前先执行这些创建类型的语句
	protected StringBuilder headSqlStatement = new StringBuilder(500);// create procedure 语句
	protected StringBuilder parameterSqlStatement = new StringBuilder(1000);// parameter 语句
	protected StringBuilder bodySqlStatement = new StringBuilder(10000);
	
	public boolean isCover() {
		return content.getBoolean("isCover");
	}
	
	/**
	 * 记录
	 * <p>在存储过程中使用的自定义类型，需要先创建出来，这里存储的是创建语句，最后要在创建存储过程语句之前先执行这些创建类型的语句</p>
	 * @param sql
	 */
	protected void recordCreateTypeSqlStatement(String sql){
		if(StrUtils.notEmpty(sql)){
			if(createTypeSqlStatement == null){
				createTypeSqlStatement = new StringBuilder(1000);
			}
			createTypeSqlStatement.append(sql).append(newline());
			createTypeSqlStatement.append(linkNextSqlStatementToken());
		}
	}
	
	protected String buildSql() {
		String procedureName = content.getString("procedureName");
		if(StrUtils.isEmpty(procedureName)){
			throw new NullPointerException("procedureName属性值不能为空");
		}
		
		boolean isCover = isCover();
		if(isCover){
			headSqlStatement.append(coverSqlServerSql(procedureName));
		}
		headSqlStatement.append("create ");
		if(isCover){
			headSqlStatement.append(coverOracleSql());
		}
		headSqlStatement.append("procedure ").append(procedureName);
		
		// 处理参数
		String parameterSql = getParameterSql();
		if(parameterSql != null){
			parameterSqlStatement.append(parameterSql);
		}
		
		// 处理declare
		recordDeclareEntityList();
		
		// 处理存储过程body
		List<Step> stepList = getStepList();
		if(stepList == null || stepList.size() == 0){
			throw new NullPointerException("存储过程的step(index)属性不能为空，至少有一项");
		}
		for (Step step : stepList) {
			bodySqlStatement.append(step.getSqlStatement());
			bodySqlStatement.append(newline());
		}
		return installProcedureSqlStatement();
	}

	/**
	 * 组装最后的procedure语句
	 * @return
	 */
	private String installProcedureSqlStatement() {
		if(createTypeSqlStatement != null && createTypeSqlStatement.length() > 0){
			append(createTypeSqlStatement.toString());
		}
		append(headSqlStatement);
		append(parameterSqlStatement);
		append("as ");
		append(DeclareVariableContext.getDeclareVariableSqlStatement());
		append("begin");
		append(bodySqlStatement);
		append("end;");
		return sql.toString();
	}
	private StringBuilder sql = new StringBuilder();
	private void append(StringBuilder sb){
		append(sb.toString());
	}
	private void append(String sb){
		sql.append(sb).append(newline());
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
				parameter = new ParameterEntity(json.getString("name"), json.getString("dataType"), json.getIntValue("length"), json.get("precision"), json.getString("inOut"), json.getString("defaultValue"));
				if(!parameter.isBaseType()){
					parameter.setCustomJson(json.getJSONObject("custom"));
				}
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
	 * 记录declare列表
	 * @return
	 */
	protected void recordDeclareEntityList() {
		JSONArray array = content.getJSONArray("declare");
		if(array != null && array.size() > 0){
			List<DeclareEntity> declareList = new ArrayList<DeclareEntity>(array.size());
			JSONObject json = null;
			DeclareEntity declare = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				declare = new DeclareEntity(json);
				if(declare.isCreateType()){
					recordCreateTypeSqlStatement(declare.getCreateTypeSqlStatement());
				}
				declareList.add(declare);
			}
			DeclareVariableContext.recordDeclare(declareList);
		}
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
}
