package com.sql.impl.statement.basic.model.orderby;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.orderby.OrderBy;

/**
 * 
 * @author DougLei
 */
public class OrderByImpl extends BasicModelImpl implements OrderBy {
	
	private List<OrderByColumnEntity> orderByColumnList;
	
	public OrderByImpl(JSONArray jsonarray) {
		JSONObject json = null;
		for(int i=0;i<jsonarray.size();i++){
			json = jsonarray.getJSONObject(i);
			addOrderByColumn(json.getString("columnName"), FunctionImpl.newInstance(json.getJSONObject("function")), json.getString("sort"));
		}
	}

	public void addOrderByColumn(String columnName, Function function, String sort){
		if(orderByColumnList == null){
			orderByColumnList = new ArrayList<OrderByColumnEntity>();
		}
		orderByColumnList.add(new OrderByColumnEntity(columnName, function, sort));
	}
	
	protected String processSqlStatement() {
		if(orderByColumnList != null && orderByColumnList.size() > 0){
			StringBuilder sb = new StringBuilder(100);
			for(int i=0;i<orderByColumnList.size();i++){
				sb.append(orderByColumnList.get(i).getSqlStatement());
				if(i<orderByColumnList.size()-1){
					sb.append(", ");
				}
			}
			return sb.toString();
		}
		return null;
	}
}
