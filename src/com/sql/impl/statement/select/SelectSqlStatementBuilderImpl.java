package com.sql.impl.statement.select;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.model.function.FunctionImpl;
import com.sql.impl.statement.model.groupby.GroupByImpl;
import com.sql.impl.statement.model.having.HavingGroupImpl;
import com.sql.impl.statement.model.having.HavingImpl;
import com.sql.impl.statement.model.join.JoinImpl;
import com.sql.impl.statement.model.join.OnGroupImpl;
import com.sql.impl.statement.model.join.OnImpl;
import com.sql.impl.statement.model.orderby.OrderByImpl;
import com.sql.impl.statement.model.resultset.ResultSetImpl;
import com.sql.impl.statement.model.table.TableImpl;
import com.sql.impl.statement.model.where.WhereGroupImpl;
import com.sql.impl.statement.model.where.WhereImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.groupby.GroupBy;
import com.sql.statement.model.having.HavingGroup;
import com.sql.statement.model.join.Join;
import com.sql.statement.model.orderby.OrderBy;
import com.sql.statement.model.resultset.ResultSet;
import com.sql.statement.model.table.Table;
import com.sql.statement.model.where.WhereGroup;
import com.sql.statement.select.SelectSqlStatementBuilder;

/**
 * 
 * @author DougLei
 */
public class SelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements SelectSqlStatementBuilder {
	protected StringBuilder selectSqlStatement = new StringBuilder(8000);
	
	public String buildSql() {
		Table table = getTable();
		
		selectSqlStatement.append(" select ");
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
		selectSqlStatement.append(" from ");
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
		List<WhereGroup> whereGroupList = getWhereGroupList();
		if(whereGroupList != null && whereGroupList.size() > 0){
			selectSqlStatement.append(" where ");
			for (WhereGroup whereGroup : whereGroupList) {
				selectSqlStatement.append(whereGroup.getSqlStatement());
			}
			selectSqlStatement.append(newline());
		}
		
		// group by
		GroupBy groupBy = getGroupBy();
		if(groupBy != null){
			selectSqlStatement.append(" group by ");
			selectSqlStatement.append(groupBy.getSqlStatement());
			selectSqlStatement.append(newline());
		}
		
		// having
		List<HavingGroup> havingGroupList = getHavingGroupList();
		if(havingGroupList != null && havingGroupList.size() > 0){
			selectSqlStatement.append(" having ");
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
			selectSqlStatement.append(" order by ");
			selectSqlStatement.append(orderBy.getSqlStatement());
			selectSqlStatement.append(newline());
		}
		return selectSqlStatement.toString();
	}

	// ---------------------------------------------------------------------------------------
	
	private Function getFunction(JSONObject function){
		if(function != null){
			Object name = function.get("name");
			JSONArray parameters = function.getJSONArray("parameters");
			if(name != null && parameters != null && parameters.size() > 0){
				String[] ps = new String[parameters.size()];
				for(int i=0;i<parameters.size();i++){
					ps[i] = parameters.getString(i);
				}
				return FunctionImpl.newInstance(name.toString(), ps);
			}
		}
		return null;
	}
	
	private String[] getValues(JSONArray jsonArray) {
		if(jsonArray != null && jsonArray.size() > 0){
			String[] values = new String[jsonArray.size()];
			for(int i=0;i<jsonArray.size();i++){
				values[i] = jsonArray.getString(i);
			}
		}
		return null;
	}
	
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
		table.setSubSqlId(json.getString("subSqlId"));
		table.setSubSqlJson(json.getJSONObject("subSqlJson"));
		table.setAlias(json.getString("alias"));
		return table;
	}

	public List<Join> getJoinList() {
		List<Join> joinList = null;
		JSONArray jsonarray = content.getJSONArray("join");
		if(jsonarray != null && jsonarray.size() > 0){
			joinList = new ArrayList<Join>(jsonarray.size());
			
			JSONObject json = null;
			JoinImpl ji = null;
			JSONArray onGroups = null;
			OnGroupImpl onGroup = null;
			JSONArray ons = null;
			OnImpl on = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				ji = new JoinImpl();
				ji.setJoinType(json.getString("type"));
				ji.setTableType(json.getString("tableType"));
				ji.setTableName(json.getString("tableName"));
				ji.setAlias(json.getString("alias"));
				
				onGroups = json.getJSONArray("on");
				if(onGroups != null && onGroups.size() > 0){
					for(int j=0;j<onGroups.size();j++){
						json = onGroups.getJSONObject(j);
						onGroup = new OnGroupImpl();
						onGroup.setNextLogicOperator(json.getString("nextLogicOperator"));
						
						ons = json.getJSONArray("onGroup");
						if(ons != null && ons.size() > 0){
							for(int k=0;k<ons.size();k++){
								json = ons.getJSONObject(k);
								on = new OnImpl();
								on.setLeftColumnName(json.getString("leftColumnName"));
								on.setLeftFunction(getFunction(json.getJSONObject("leftFunction")));
								on.setDataOperator(json.getString("operator"));
								on.setRightColumnName(json.getString("rightColumnName"));
								on.setRightFunction(getFunction(json.getJSONObject("rightFunction")));
								on.setNextLogicOperator(json.getString("nextLogicOperator"));
								
								onGroup.addOn(on);
							}
						}
						ji.addOnGroup(onGroup);
					}
				}
				joinList.add(ji);
			}
		}
		return joinList;
	}

	public List<WhereGroup> getWhereGroupList() {
		List<WhereGroup> whereGroupList = null;
		JSONArray jsonarray = content.getJSONArray("where");
		if(jsonarray != null && jsonarray.size() > 0){
			whereGroupList = new ArrayList<WhereGroup>(jsonarray.size());
			
			JSONObject json = null;
			WhereGroupImpl whereGroup = null;
			JSONArray wheres = null;
			WhereImpl where = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				whereGroup = new WhereGroupImpl();
				whereGroup.setNextLogicOperator(json.getString("nextLogicOperator"));
				
				wheres = json.getJSONArray("whereGroup");
				if(wheres != null && wheres.size() > 0){
					for(int j=0;j<wheres.size();j++){
						json = wheres.getJSONObject(j);
						where = new WhereImpl();
						where.setColumnName(json.getString("columnName"));
						where.setColumnFunction(getFunction(json.getJSONObject("columnFunction")));
						where.setDataOperator(json.getString("operator"));
						where.setValues(getValues(json.getJSONArray("values")));
						where.setValueFunction(getFunction(json.getJSONObject("valueFunction")));
						where.setNextLogicOperator(json.getString("nextLogicOperator"));
						
						whereGroup.addWhere(where);
					}
				}
				whereGroupList.add(whereGroup);
			}
		}
		return whereGroupList;
	}
	
	public GroupBy getGroupBy() {
		GroupByImpl groupBy = null;
		JSONArray jsonarray = content.getJSONArray("groupBy");
		if(jsonarray != null && jsonarray.size() > 0){
			groupBy = new GroupByImpl();
			
			JSONObject json = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				groupBy.addGroupByColumn(json.getString("columnName"), getFunction(json.getJSONObject("columnFunction")));
			}
		}
		return groupBy;
	}

	public List<HavingGroup> getHavingGroupList() {
		List<HavingGroup> havingGroupList = null;
		JSONArray jsonarray = content.getJSONArray("having");
		if(jsonarray != null && jsonarray.size() > 0){
			havingGroupList = new ArrayList<HavingGroup>(jsonarray.size());
			
			JSONObject json = null;
			HavingGroupImpl havingGroup = null;
			JSONArray wheres = null;
			HavingImpl having = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				havingGroup = new HavingGroupImpl();
				havingGroup.setNextLogicOperator(json.getString("nextLogicOperator"));
				
				wheres = json.getJSONArray("havingGroup");
				if(wheres != null && wheres.size() > 0){
					for(int j=0;j<wheres.size();j++){
						json = wheres.getJSONObject(j);
						having = new HavingImpl();
						having.setColumnName(json.getString("columnName"));
						having.setColumnFunction(getFunction(json.getJSONObject("columnFunction")));
						having.setDataOperator(json.getString("operator"));
						having.setValues(getValues(json.getJSONArray("values")));
						having.setValueFunction(getFunction(json.getJSONObject("valueFunction")));
						having.setNextLogicOperator(json.getString("nextLogicOperator"));
						
						havingGroup.addHaving(having);
					}
				}
				havingGroupList.add(havingGroup);
			}
		}
		return havingGroupList;
	}

	public OrderBy getOrderBy() {
		OrderByImpl orderBy = null;
		JSONArray jsonarray = content.getJSONArray("orderBy");
		if(jsonarray != null && jsonarray.size() > 0){
			orderBy = new OrderByImpl();
			
			JSONObject json = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				orderBy.addOrderByColumn(json.getString("columnName"), getFunction(json.getJSONObject("columnFunction")), json.getString("sort"));
			}
		}
		return orderBy;
	}
}
