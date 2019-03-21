package com.sql.impl.statement.basic;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.basic.model.groupby.GroupByImpl;
import com.sql.impl.statement.basic.model.join.JoinImpl;
import com.sql.impl.statement.basic.model.join.OnGroupImpl;
import com.sql.impl.statement.basic.model.join.OnImpl;
import com.sql.impl.statement.basic.model.orderby.OrderByImpl;
import com.sql.impl.statement.basic.model.where.WhereGroupImpl;
import com.sql.impl.statement.basic.model.where.WhereImpl;
import com.sql.statement.basic.model.groupby.GroupBy;
import com.sql.statement.basic.model.join.Join;
import com.sql.statement.basic.model.join.OnGroup;
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
	
	// ------------------------------------------------
	public List<Join> getJoinList() {
		JSONArray jsonarray = content.getJSONArray("join");
		if(jsonarray != null && jsonarray.size() > 0){
			List<Join> joinList = new ArrayList<Join>(jsonarray.size());
			
			JSONObject json = null;
			Join ji = null;
			JSONArray onGroups = null;
			OnGroup onGroup = null;
			JSONArray ons = null;
			for(int i=0;i<jsonarray.size();i++){
				json = jsonarray.getJSONObject(i);
				ji = new JoinImpl(json);
				
				onGroups = json.getJSONArray("on");
				if(onGroups != null && onGroups.size() > 0){
					for(int j=0;j<onGroups.size();j++){
						json = onGroups.getJSONObject(j);
						onGroup = new OnGroupImpl(json, onGroups.size());
						
						ons = json.getJSONArray("onGroup");
						if(ons != null && ons.size() > 0){
							for(int k=0;k<ons.size();k++){
								onGroup.addOn(new OnImpl(ons.getJSONObject(k)));
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

	// ------------------------------------------------
	public String getWhereSqlStatement(){
		return getWhereSqlStatement("where", getWhereGroupList());
	}
	private List<WhereGroup> getWhereGroupList() {
		return getWhereGroupList("where", "whereGroup");
	}
	
	// ------------------------------------------------

	public GroupBy getGroupBy() {
		JSONArray jsonarray = content.getJSONArray("groupBy");
		if(jsonarray != null && jsonarray.size() > 0){
			return new GroupByImpl(jsonarray);
		}
		return null;
	}

	// ------------------------------------------------
	public String getHavingSqlStatement(){
		return getWhereSqlStatement("having", getHavingGroupList());
	}
	private List<WhereGroup> getHavingGroupList() {
		return getWhereGroupList("having", "havingGroup");
	}

	// ------------------------------------------------
	public OrderBy getOrderBy() {
		JSONArray jsonarray = content.getJSONArray("orderBy");
		if(jsonarray != null && jsonarray.size() > 0){
			return new OrderByImpl(jsonarray);
		}
		return null;
	}
	
	// ------------------------------------------------
	private String getWhereSqlStatement(String keyword, List<WhereGroup> whereGroupList){
		StringBuilder where = new StringBuilder(3000);
		if(whereGroupList != null && whereGroupList.size() > 0){
			where.append(newline());
			where.append(keyword).append(" ");
			for(int i=0;i<whereGroupList.size();i++){
				where.append(whereGroupList.get(i).getSqlStatement());
				if(i<whereGroupList.size()-1){
					where.append(" ").append(whereGroupList.get(i).getNextLogicOperator()).append(" ");
				}
			}
			where.append(newline());
		}
		return where.toString();
	}
	private List<WhereGroup> getWhereGroupList(String name, String groupName) {
		JSONArray jsonarray = content.getJSONArray(name);
		if(jsonarray != null && jsonarray.size() > 0){
			int size = jsonarray.size();
			List<WhereGroup> whereGroupList = new ArrayList<WhereGroup>(size);
			
			JSONObject json = null;
			WhereGroupImpl whereGroup = null;
			JSONArray wheres = null;
			for(int i=0;i<size;i++){
				json = jsonarray.getJSONObject(i);
				whereGroup = new WhereGroupImpl(json, size);
				
				wheres = json.getJSONArray(groupName);
				if(wheres != null && wheres.size() > 0){
					for(int j=0;j<wheres.size();j++){
						whereGroup.addWhere(new WhereImpl(wheres.getJSONObject(j)));
					}
				}
				whereGroupList.add(whereGroup);
			}
			return whereGroupList;
		}
		return null;
	}
}
