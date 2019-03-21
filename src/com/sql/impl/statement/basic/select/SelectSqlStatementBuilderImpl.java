package com.sql.impl.statement.basic.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.impl.statement.basic.model.resultset.ResultSetImpl;
import com.sql.impl.statement.basic.model.table.TableImpl;
import com.sql.statement.basic.model.groupby.GroupBy;
import com.sql.statement.basic.model.join.Join;
import com.sql.statement.basic.model.orderby.OrderBy;
import com.sql.statement.basic.model.resultset.ResultSet;
import com.sql.statement.basic.model.table.Table;
import com.sql.statement.basic.select.SelectSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public class SelectSqlStatementBuilderImpl extends AbstractSqlStatementBuilder implements SelectSqlStatementBuilder {
	
	public String buildSql() {
		StringBuilder selectSqlStatement = new StringBuilder(6000);
		
		selectSqlStatement.append("select ");
		selectSqlStatement.append(newline());
		
		// 查询的列名
		List<ResultSet> resultSetList = getResultSetList();
		setResultSetColumnNames(resultSetList);
		for(int i=0;i<resultSetList.size();i++){
			selectSqlStatement.append(resultSetList.get(i).getSqlStatement());
			if(i < resultSetList.size()-1){
				selectSqlStatement.append(", ");
			}
		}
		selectSqlStatement.append(newline());
		
		StringBuilder selectSqlBodyStatement = new StringBuilder(4000);
		// from
		selectSqlBodyStatement.append("from ");
		
		Table table = getTable();
		selectSqlBodyStatement.append(table.getSqlStatement());
		if(table.isDefaultTable()){
			setBody("");
		}else{
			selectSqlBodyStatement.append(newline());
			
			// join
			List<Join> joinList = getJoinList();
			if(joinList != null && joinList.size() > 0){
				for (Join join : joinList) {
					selectSqlBodyStatement.append(join.getSqlStatement());
					selectSqlBodyStatement.append(newline());
				}
			}

			// where
			selectSqlBodyStatement.append(getWhereSqlStatement());
			
			// group by
			GroupBy groupBy = getGroupBy();
			if(groupBy != null){
				selectSqlBodyStatement.append("group by ");
				selectSqlBodyStatement.append(groupBy.getSqlStatement());
				selectSqlBodyStatement.append(newline());
			}
			
			// having
			selectSqlBodyStatement.append(getHavingSqlStatement());
			
			// order by
			OrderBy orderBy = getOrderBy();
			if(orderBy != null){
				selectSqlBodyStatement.append("order by ");
				selectSqlBodyStatement.append(orderBy.getSqlStatement());
				selectSqlBodyStatement.append(newline());
			}
			
			setBody(selectSqlBodyStatement.toString());
			selectSqlStatement.append(selectSqlBodyStatement);
		}
		return selectSqlStatement.toString();
	}

	// ---------------------------------------------------------------------------------------
	
	public List<ResultSet> getResultSetList() {
		JSONArray jsonarray = content.getJSONArray("resultSet");
		if(jsonarray == null || jsonarray.size() == 0){
			throw new NullPointerException("select类型的语句，必须有resultSet节点属性");
		}
		List<ResultSet> rs = new ArrayList<ResultSet>(jsonarray.size());
		
		JSONObject json = null;
		for(int i=0;i<jsonarray.size();i++){
			json = jsonarray.getJSONObject(i);
			rs.add(new ResultSetImpl(json));
		}
		return rs;
	}

	public Table getTable() {
		JSONObject json = content.getJSONObject("name");
		if(json == null || json.size() == 0){
			return (Table) SqlStatementBuilderContext.getDBImplInstance("com.sql.impl.statement.basic.model.table", "TableImpl");
		}
		return new TableImpl(json);
	}

	private List<String> resultsetColumnNames;
	public List<String> getResultSetColumnNames() {
		buildSqlStatement();
		return resultsetColumnNames;
	}
	private void setResultSetColumnNames(List<ResultSet> resultSetList){
		if(resultsetColumnNames == null){
			resultsetColumnNames = new ArrayList<String>(resultSetList.size());
		}
		for (ResultSet resultSet : resultSetList) {
			resultsetColumnNames.add(resultSet.getResultSetColumnName());
		}
	}

	private String body;
	public String getBody() {
		buildSqlStatement();
		return body;
	}
	private void setBody(String selectSqlBodyStatement){
		body = selectSqlBodyStatement;
	}
}
