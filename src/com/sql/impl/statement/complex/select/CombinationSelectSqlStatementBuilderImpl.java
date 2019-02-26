package com.sql.impl.statement.complex.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.SqlStatementInfoBuilderImpl;
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
			for (With with : withList) {
				withSqlBodyStatement.append(with.getSqlStatement());
				withSqlBodyStatement.append(newline());
			}
		}
		setWithBody(withSqlBodyStatement);
		
		JSONArray array = content.getJSONArray("selectSql");
		if(array == null || array.size() == 0){
			throw new NullPointerException("组合查询select sql语句的selectSql属性值不能为空");
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
			
			String alias = null;
			JSONObject json = null;
			WithImpl with = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				alias = json.getString("alias");
				if(StrUtils.isEmpty(alias)){
					throw new NullPointerException("with 子句的alias属性不能为空");
				}
				
				with = new WithImpl(alias);
				with.setSqlStatement(processSelectSql(false, json.getJSONArray("selectSql"), null));
				
				withList.add(with);
			}
			return withList;
		}
		return null;
	}

	/**
	 * 处理sql语句
	 * @param recordResultSetColumnName 是否记录查询结果集列名
	 * @param array
	 * @param sql
	 */
	private StringBuilder processSelectSql(boolean recordResultSetColumnName, JSONArray array, StringBuilder sql){
		if(sql == null){
			sql = new StringBuilder(3000);
		}
		JSONObject json = null;
		if(array.size() == 1){
			json = array.getJSONObject(0);
			sql.append(toSelectSql(recordResultSetColumnName, json));
		}else{
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				if(i < array.size()-1 && StrUtils.isEmpty(json.getString("unionType"))){
					throw new NullPointerException("组合查询，第"+(i+1)+"个select sql语句配置的unionType属性值不能为空");
				}
				
				if(i == 0 && recordResultSetColumnName){
					sql.append(toSelectSql(true, json));
				}else{
					sql.append(toSelectSql(false, json));
				}
				if(i < array.size()-1){
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
		SqlStatementBuilder builder = null;
		if(StrUtils.notEmpty(json.getString("selectSqlId"))){
			builder = SqlStatementBuilderContext.getSqlStatementBuilder(json.getString("selectSqlId"));
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(json.getJSONObject("selectSqlJson"));
			builder = infoBuilder.createSqlStatementBuilder();
		}
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
}
