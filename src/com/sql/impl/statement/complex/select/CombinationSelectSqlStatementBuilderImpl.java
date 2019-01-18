package com.sql.impl.statement.complex.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.complex.select.model.WithImpl;
import com.sql.statement.complex.select.CombinationSelectSqlStatementBuilder;
import com.sql.statement.complex.select.model.With;
import com.sql.util.StrUtils;

/**
 * 组合select sql语句builder 实现类
 * @author DougLei
 */
public class CombinationSelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements CombinationSelectSqlStatementBuilder {
	protected StringBuilder combinationSelectSqlStatement = new StringBuilder(8000);
	
	protected String buildSql() {
		
		List<With> withList = getWithList();
		if(withList != null){
			combinationSelectSqlStatement.append("with ");
			combinationSelectSqlStatement.append(newline());
			for (With with : withList) {
				combinationSelectSqlStatement.append(with.getSqlStatement());
				combinationSelectSqlStatement.append(newline());
			}
		}
		
		JSONArray array = content.getJSONArray("selectSql");
		if(array == null || array.size() == 0){
			throw new NullPointerException("组合查询select sql语句的selectSql属性值不能为空");
		}
		processSelectSql(array, combinationSelectSqlStatement);
		
		return combinationSelectSqlStatement.toString();
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
				with.setSqlStatement(processSelectSql(json.getJSONArray("selectSql"), null));
				
				withList.add(with);
			}
			return withList;
		}
		return null;
	}

	/**
	 * 处理sql语句
	 * @param array
	 * @param sql
	 */
	private StringBuilder processSelectSql(JSONArray array, StringBuilder sql){
		if(sql == null){
			sql = new StringBuilder(3000);
		}
		JSONObject json = null;
		if(array.size() == 1){
			json = array.getJSONObject(0);
			sql.append(toSelectSql(json));
		}else{
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				if(i < array.size()-1 && StrUtils.isEmpty(json.getString("unionType"))){
					throw new NullPointerException("组合查询，第"+(i+1)+"个select sql语句配置的unionType属性值不能为空");
				}
				
				sql.append(toSelectSql(json));
				sql.append(newline());
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
	 * @param json
	 * @return
	 */
	private String toSelectSql(JSONObject json){
		if(StrUtils.notEmpty(json.getString("selectSqlId"))){
			return SqlStatementBuilderContext.buildSqlStatement(json.getString("selectSqlId"));
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(json.getJSONObject("selectSqlJson"));
			return infoBuilder.createSqlStatementBuilder().buildSqlStatement();
		}
	}
}
