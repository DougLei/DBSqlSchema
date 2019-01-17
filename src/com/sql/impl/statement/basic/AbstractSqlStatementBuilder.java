package com.sql.impl.statement.basic;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.exception.DBSqlSchemaException;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.basic.model.groupby.GroupByImpl;
import com.sql.impl.statement.basic.model.having.HavingGroupImpl;
import com.sql.impl.statement.basic.model.having.HavingImpl;
import com.sql.impl.statement.basic.model.join.JoinImpl;
import com.sql.impl.statement.basic.model.join.OnGroupImpl;
import com.sql.impl.statement.basic.model.join.OnImpl;
import com.sql.impl.statement.basic.model.orderby.OrderByImpl;
import com.sql.impl.statement.basic.model.where.ValueImpl;
import com.sql.impl.statement.basic.model.where.WhereGroupImpl;
import com.sql.impl.statement.basic.model.where.WhereImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.groupby.GroupBy;
import com.sql.statement.basic.model.having.HavingGroup;
import com.sql.statement.basic.model.join.Join;
import com.sql.statement.basic.model.orderby.OrderBy;
import com.sql.statement.basic.model.where.WhereGroup;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractSqlStatementBuilder extends SqlStatementBuilderImpl{

	/**
	 * 将jsonarray转换为数组
	 * @param jsonArray
	 * @return
	 */
	protected String[] getValues(JSONArray jsonArray) {
		if(jsonArray != null && jsonArray.size() > 0){
			String[] values = new String[jsonArray.size()];
			for(int i=0;i<jsonArray.size();i++){
				values[i] = jsonArray.getString(i);
			}
			return values;
		}
		return null;
	}
	
	/**
	 * 获取function属性对象
	 * @param function
	 * @return
	 */
	protected Function getFunction(JSONObject function){
		if(function != null){
			return FunctionImpl.newInstance(function.getString("name"), function.getJSONArray("parameters"));
		}
		return null;
	}
	
	public List<Join> getJoinList() {
		JSONArray jsonarray = content.getJSONArray("join");
		if(jsonarray != null && jsonarray.size() > 0){
			List<Join> joinList = new ArrayList<Join>(jsonarray.size());
			
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
			return joinList;
		}
		return null;
	}

	public String getWhereSqlStatement(){
		StringBuilder where = new StringBuilder(3000);
		List<WhereGroup> whereGroupList = getWhereGroupList();
		if(whereGroupList != null && whereGroupList.size() > 0){
			where.append(newline());
			where.append("where ");
			for (WhereGroup whereGroup : whereGroupList) {
				where.append(whereGroup.getSqlStatement());
			}
			where.append(newline());
		}
		return where.toString();
	}
	private List<WhereGroup> getWhereGroupList() {
		JSONArray jsonarray = content.getJSONArray("where");
		if(jsonarray != null && jsonarray.size() > 0){
			List<WhereGroup> whereGroupList = new ArrayList<WhereGroup>(jsonarray.size());
			
			JSONObject json = null;
			WhereGroupImpl whereGroup = null;
			JSONArray wheres = null;
			WhereImpl where = null;
			ValueImpl value = null;
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
						where.setNextLogicOperator(json.getString("nextLogicOperator"));
						
						json = json.getJSONObject("value");
						if(json == null || json.size() == 0){
							throw new DBSqlSchemaException("where 子句中，value属性不能为空");
						}
						value = new ValueImpl();
						value.setType(json.getString("type"));
						value.setSubSqlId(json.getString("subSqlId"));
						value.setSubSqlJson(json.getJSONObject("subSqlJson"));
						value.setValueArray(json.getJSONArray("value"));
						value.setValueFunctionArray(json.getJSONArray("valueFunction"));
						
						where.setValue(value);
						whereGroup.addWhere(where);
					}
				}
				whereGroupList.add(whereGroup);
			}
			return whereGroupList;
		}
		return null;
	}
	
	public GroupBy getGroupBy() {
		JSONArray jsonarray = content.getJSONArray("groupBy");
		if(jsonarray != null && jsonarray.size() > 0){
			GroupByImpl groupBy = new GroupByImpl();
			
			JSONObject json = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				groupBy.addGroupByColumn(json.getString("columnName"), getFunction(json.getJSONObject("columnFunction")));
			}
			return groupBy;
		}
		return null;
	}

	public List<HavingGroup> getHavingGroupList() {
		JSONArray jsonarray = content.getJSONArray("having");
		if(jsonarray != null && jsonarray.size() > 0){
			List<HavingGroup> havingGroupList = new ArrayList<HavingGroup>(jsonarray.size());
			
			JSONObject json = null;
			HavingGroupImpl havingGroup = null;
			JSONArray wheres = null;
			HavingImpl having = null;
			ValueImpl value = null;
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
						having.setNextLogicOperator(json.getString("nextLogicOperator"));
						
						json = json.getJSONObject("value");
						if(json == null || json.size() == 0){
							throw new DBSqlSchemaException("where 子句中，value属性不能为空");
						}
						value = new ValueImpl();
						value.setType(json.getString("type"));
						value.setSubSqlId(json.getString("subSqlId"));
						value.setSubSqlJson(json.getJSONObject("subSqlJson"));
						value.setValueArray(json.getJSONArray("value"));
						value.setValueFunctionArray(json.getJSONArray("valueFunction"));
						
						having.setValue(value);
						havingGroup.addHaving(having);
					}
				}
				havingGroupList.add(havingGroup);
			}
			return havingGroupList;
		}
		return null;
	}

	public OrderBy getOrderBy() {
		JSONArray jsonarray = content.getJSONArray("orderBy");
		if(jsonarray != null && jsonarray.size() > 0){
			OrderByImpl orderBy = new OrderByImpl();
			
			JSONObject json = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				orderBy.addOrderByColumn(json.getString("columnName"), getFunction(json.getJSONObject("columnFunction")), json.getString("sort"));
			}
			return orderBy;
		}
		return null;
	}
}
