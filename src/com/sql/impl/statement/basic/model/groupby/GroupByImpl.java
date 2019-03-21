package com.sql.impl.statement.basic.model.groupby;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.groupby.GroupBy;

/**
 * 
 * @author DougLei
 */
public class GroupByImpl extends BasicModelImpl implements GroupBy {

	private List<GroupByColumnEntity> groupByColumnList;
	
	public GroupByImpl(JSONArray jsonarray) {
		JSONObject json = null;
		for(int i=0;i<jsonarray.size();i++){
			json = jsonarray.getJSONObject(i);
			addGroupByColumn(json.getString("columnName"), FunctionImpl.newInstance(json.getJSONObject("function")));
		}
	}

	private void addGroupByColumn(String columnName, Function function){
		if(groupByColumnList == null){
			groupByColumnList = new ArrayList<GroupByColumnEntity>();
		}
		groupByColumnList.add(new GroupByColumnEntity(columnName, function));
	}
	
	protected String processSqlStatement() {
		if(groupByColumnList != null && groupByColumnList.size() > 0){
			StringBuilder sb = new StringBuilder(100);
			for(int i=0;i<groupByColumnList.size();i++){
				sb.append(groupByColumnList.get(i).getSqlStatement());
				if(i<groupByColumnList.size()-1){
					sb.append(", ");
				}
			}
			return sb.toString();
		}
		return null;
	}
}
