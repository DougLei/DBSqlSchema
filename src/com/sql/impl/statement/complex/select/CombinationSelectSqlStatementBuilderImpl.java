package com.sql.impl.statement.complex.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.complex.select.model.WithImpl;
import com.sql.statement.basic.select.SelectSqlStatementBuilder;
import com.sql.statement.complex.select.CombinationSelectSqlStatementBuilder;
import com.sql.statement.complex.select.UnionType;
import com.sql.statement.complex.select.model.With;
import com.sql.util.StrUtils;

/**
 * 组合select sql语句builder 实现类
 * @author DougLei
 */
public class CombinationSelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements CombinationSelectSqlStatementBuilder {

	protected String buildSql() {
		StringBuilder withSqlBodyStatement = new StringBuilder(8000);
		StringBuilder selectSqlBodyStatement = new StringBuilder(5000);
		
		List<With> withList = getWithList();
		if(withList != null){
			withSqlBodyStatement.append("with ");
			withSqlBodyStatement.append(newline());
			int loopCount = withList.size();
			for(int i=0;i<loopCount;i++){
				withSqlBodyStatement.append(withList.get(i).getSqlStatement());
				withSqlBodyStatement.append(newline());
				if(loopCount>1 && i<(loopCount-1)){
					withSqlBodyStatement.append(", ");
				}
			}
		}
		setWithBody(withSqlBodyStatement);
		
		JSONArray array = content.getJSONArray("sqlJson");
		if(array == null || array.size() == 0){
			throw new NullPointerException("组合查询select sql语句的sqlJson属性值不能为空");
		}
		processSelectSql(true, array, selectSqlBodyStatement);
		setBody(selectSqlBodyStatement);
		
		withSqlBodyStatement.append(selectSqlBodyStatement);
		return withSqlBodyStatement.toString();
	}
	
	public List<With> getWithList() {
		JSONArray array = content.getJSONArray("with");
		if(array != null && array.size() > 0){
			List<With> withList = new ArrayList<With>(array.size());
			
			JSONObject json = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				withList.add(new WithImpl(json).setSqlStatement(processSelectSql(false, json.getJSONArray("sql"), null)));
			}
			return withList;
		}
		return null;
	}

	/**
	 * 处理sql语句
	 * @param recordResultSetColumnName 是否记录查询结果集列名
	 * @param sqlArray
	 * @param sql
	 */
	private StringBuilder processSelectSql(boolean recordResultSetColumnName, JSONArray sqlArray, StringBuilder sql){
		if(sql == null){
			sql = new StringBuilder(3000);
		}
		JSONObject json = null;
		if(sqlArray.size() == 1){
			json = sqlArray.getJSONObject(0);
			sql.append(toSelectSql(recordResultSetColumnName, json));
		}else{
			if(recordResultSetColumnName){
				isUnionQuery = true;
			}
			for(int i=0;i<sqlArray.size();i++){
				json = sqlArray.getJSONObject(i);
				if(i < sqlArray.size()-1 && StrUtils.isEmpty(json.getString("unionType"))){
					throw new NullPointerException("组合查询，第"+(i+1)+"个select sql语句配置的unionType属性值不能为空");
				}
				
				if(i == 0 && recordResultSetColumnName){
					sql.append(toSelectSql(true, json));
				}else{
					sql.append(toSelectSql(false, json));
				}
				if(i < sqlArray.size()-1){
					sql.append(UnionType.toValue(json.getString("unionType")).getSqlStatement());
					sql.append(newline());
				}
			}
		}
		return sql;
	}
	
	/**
	 * 从json中转换出select sql语句
	 * @param recordResultSetColumnName
	 * @param json
	 * @return
	 */
	private String toSelectSql(boolean recordResultSetColumnName, JSONObject json){
		SqlStatementBuilder builder = SqlStatementBuilderContext.getSqlStatementBuilder(json.getString("sqlId"), json.getJSONObject("sqlJson"));
		if(builder instanceof SelectSqlStatementBuilder){
			setResultSetColumnNames(((SelectSqlStatementBuilder)builder).getResultSetColumnNames());
		}
		return builder.buildSqlStatement();
	}

	private List<String> resultsetColumnNames;
	public List<String> getResultSetColumnNames() {
		buildSqlStatement();
		return resultsetColumnNames;
	}
	private void setResultSetColumnNames(List<String> resultsetColumnNames){
		this.resultsetColumnNames = resultsetColumnNames;
	}

	private String withBody;
	public String getWithBody() {
		buildSqlStatement();
		return withBody;
	}
	private void setWithBody(StringBuilder withSqlBodyStatement){
		withBody = withSqlBodyStatement.toString();
	}

	private String body;
	public String getBody() {
		buildSqlStatement();
		return body;
	}
	private void setBody(StringBuilder selectSqlBodyStatement){
		body = selectSqlBodyStatement.toString();
	}

	private boolean isUnionQuery;
	public boolean isUnionQuery() {
		return isUnionQuery;
	}
}
