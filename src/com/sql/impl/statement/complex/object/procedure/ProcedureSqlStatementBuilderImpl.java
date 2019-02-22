package com.sql.impl.statement.complex.object.procedure;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.complex.object.procedure.model.CreateTypeContext;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareContext;
import com.sql.impl.statement.complex.object.procedure.model.param.ParameterContext;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder headSqlStatement = new StringBuilder(500);// create procedure 语句
	protected StringBuilder bodySqlStatement = new StringBuilder(10000);
	
	public boolean isTransaction(){
		return content.getBoolean("isTransaction");
	}
	
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
			headSqlStatement.append(coverSqlServerSql(procedureName));
		}
		headSqlStatement.append("create ");
		if(isCover){
			headSqlStatement.append(coverOracleSql());
		}
		headSqlStatement.append("procedure ").append(procedureName);
		
		// 处理参数
		processParameter();
		
		// 处理declare
		processDeclare();
		
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
		if(CreateTypeContext.includeCreateType()){
			append(CreateTypeContext.getCreateTypeSqlStatement());
		}
		append(headSqlStatement);
		append(ParameterContext.getParameterSqlStatement());
		append("as ");
		append(DeclareContext.getDeclareSqlStatement());
		append("begin");
		
		boolean isTransaction = isTransaction();
		if(isTransaction){
			append(beginTransaction());
		}
		append(bodySqlStatement);
		if(isTransaction){
			append(commit());
		}
		append("end;");
		return sql.toString();
	}
	
	protected String beginTransaction() {
		return "begin transaction;";
	}
	private String commit() {
		return "commit;";
	}
	
	private StringBuilder sql = new StringBuilder();
	private void append(StringBuilder sb){
		append(sb.toString());
	}
	private void append(String sb){
		sql.append(sb).append(newline());
	}

	/**
	 * 处理(记录)parameter 集合
	 * @return 
	 */
	protected void processParameter(){
		JSONArray array = content.getJSONArray("parameter");
		if(array != null && array.size() > 0){
			for(int i=0;i<array.size();i++){
				ParameterContext.recordParameter(array.getJSONObject(i));
			}
		}
	}
	
	/**
	 * 处理(记录)declare 集合
	 * @return
	 */
	protected void processDeclare() {
		JSONArray array = content.getJSONArray("declare");
		if(array != null && array.size() > 0){
			for(int i=0;i<array.size();i++){
				DeclareContext.recordDeclare(array.getJSONObject(i));
			}
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
