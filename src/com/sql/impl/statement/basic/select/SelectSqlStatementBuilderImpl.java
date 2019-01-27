package com.sql.impl.statement.basic.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.impl.statement.basic.model.resultset.ResultSetImpl;
import com.sql.impl.statement.basic.model.table.TableImpl;
import com.sql.statement.basic.model.groupby.GroupBy;
import com.sql.statement.basic.model.having.HavingGroup;
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
	protected StringBuilder selectSqlStatement = new StringBuilder(6000);
	
	public String buildSql() {
		Table table = getTable();
		
		selectSqlStatement.append("select ");
		selectSqlStatement.append(newline());
		
		// 查询的列名
		List<ResultSet> resultSetList = getResultSetList();
		for(int i=0;i<resultSetList.size();i++){
			selectSqlStatement.append(resultSetList.get(i).getSqlStatement());
			if(i < resultSetList.size()-1){
				selectSqlStatement.append(", ");
			}
		}
		selectSqlStatement.append(newline());
		
		// from
		selectSqlStatement.append("from ");
		selectSqlStatement.append(table.getSqlStatement());
		selectSqlStatement.append(newline());
		
		// join
		List<Join> joinList = getJoinList();
		if(joinList != null && joinList.size() > 0){
			for (Join join : joinList) {
				selectSqlStatement.append(join.getSqlStatement());
				selectSqlStatement.append(newline());
			}
		}

		// where
		selectSqlStatement.append(getWhereSqlStatement());
		
		// group by
		GroupBy groupBy = getGroupBy();
		if(groupBy != null){
			selectSqlStatement.append("group by ");
			selectSqlStatement.append(groupBy.getSqlStatement());
			selectSqlStatement.append(newline());
		}
		
		// having
		List<HavingGroup> havingGroupList = getHavingGroupList();
		if(havingGroupList != null && havingGroupList.size() > 0){
			selectSqlStatement.append("having ");
			for(int i=0;i<havingGroupList.size();i++){
				selectSqlStatement.append(havingGroupList.get(i).getSqlStatement());
				if(i < havingGroupList.size()-1){
					selectSqlStatement.append(", ");
				}
			}
			selectSqlStatement.append(newline());
		}
		
		// order by
		OrderBy orderBy = getOrderBy();
		if(orderBy != null){
			selectSqlStatement.append("order by ");
			selectSqlStatement.append(orderBy.getSqlStatement());
			selectSqlStatement.append(newline());
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
		ResultSetImpl rsi = null;
		for(int i=0;i<jsonarray.size();i++){
			json = jsonarray.getJSONObject(i);
			rsi = new ResultSetImpl();
			rsi.setColumnName(json.getString("columnName"));
			rsi.setFunction(getFunction(json.getJSONObject("columnFunction")));
			rsi.setAlias(json.getString("alias"));
			
			rs.add(rsi);
		}
		return rs;
	}

	public Table getTable() {
		JSONObject json = content.getJSONObject("table");
		if(json == null || json.size() == 0){
			throw new NullPointerException("select类型的语句，必须有table节点属性");
		}
		
		TableImpl table = new TableImpl();
		table.setTableType(json.getString("type"));
		table.setName(json.getString("name"));
		table.setFunction(getFunction(json.getJSONObject("function")));
		table.setSubSqlId(json.getString("subSqlId"));
		table.setSubSqlJson(json.getJSONObject("subSqlJson"));
		table.setAlias(json.getString("alias"));
		return table;
	}
}
