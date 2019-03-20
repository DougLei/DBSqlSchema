package com.sql.impl.statement.basic.model.function.builtin.rownumberover;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.impl.statement.basic.model.orderby.OrderByImpl;
import com.sql.statement.basic.model.function.BuiltinFunction;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.orderby.OrderBy;

/**
 * 
 * @author DougLei
 */
public abstract class RowNumberOver implements BuiltinFunction{
	private RowNumberOver_Partition partition;
	private RowNumberOver_OrderBy orderby;
	
	public BuiltinFunction init(JSONObject confJson) {
		JSONArray array = confJson.getJSONArray("partition");
		if(array != null && array.size() > 0){
			partition = new RowNumberOver_Partition(array);
		}
		
		array = confJson.getJSONArray("orderBy");
		if(array != null && array.size() > 0){
			orderby = new RowNumberOver_OrderBy(array);
		}
		return this;
	}

	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(120);
		sb.append("row_number() over(");
		if(partition != null){
			sb.append(partition.getSqlStatement());
		}
		if(orderby != null){
			sb.append(" ");
			sb.append(orderby.getSqlStatement());
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public String getFunctionName() {
		return "_rownumberover";
	}
	
	// --------------------------------------------------------------------
	/** 分组 */
	private class RowNumberOver_Partition{
		private Partition partition;
		public RowNumberOver_Partition(JSONArray array) {
			partition = new Partition(array);
		}
		public String getSqlStatement(){
			return "partition by " + partition.getSqlStatement();
		}
	}
	private class Partition{
		private List<PartitionEntity> list;
		
		public Partition(JSONArray array) {
			JSONObject json = null;
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				addPartitionEntity(json.getString("columnName"), FunctionImpl.newInstance(json.getJSONObject("function")));
			}
		}
		private void addPartitionEntity(String columnName, Function function) {
			if(list == null){
				list = new ArrayList<PartitionEntity>();
			}
			list.add(new PartitionEntity(columnName, function));
		}
		public String getSqlStatement() {
			if(list != null && list.size() > 0){
				StringBuilder sb = new StringBuilder(100);
				for(int i=0;i<list.size();i++){
					sb.append(list.get(i).getSqlStatement());
					if(i<list.size()-1){
						sb.append(", ");
					}
				}
				return sb.toString();
			}
			return null;
		}
	}
	private class PartitionEntity{
		private String columnName;
		private Function function;
		public PartitionEntity(String columnName, Function function) {
			this.columnName = columnName;
			this.function = function;
		}
		public String getSqlStatement(){
			if(function == null){
				return columnName;
			}else{
				return function.getSqlStatement();
			}
		}
	}
	
	// --------------------------------------------------------------------
	/** 排序 */
	private class RowNumberOver_OrderBy{
		private OrderBy orderBy;
		public RowNumberOver_OrderBy(JSONArray array) {
			orderBy = new OrderByImpl(array);
		}
		public String getSqlStatement(){
			return "order by " + orderBy.getSqlStatement();
		}
	}
}
