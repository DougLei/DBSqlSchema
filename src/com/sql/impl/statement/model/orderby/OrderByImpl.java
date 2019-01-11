package com.sql.impl.statement.model.orderby;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.statement.model.BasicImpl;
import com.sql.impl.statement.model.function.FunctionImpl;
import com.sql.statement.model.orderby.OrderBy;

/**
 * 
 * @author DougLei
 */
public class OrderByImpl extends BasicImpl implements OrderBy {
	
private List<OrderByColumnEntity> orderByColumnList;
	
	public void addOrderByColumn(String columnName, String functionName, String[] parameters, String sort){
		if(orderByColumnList == null){
			orderByColumnList = new ArrayList<OrderByColumnEntity>();
		}
		orderByColumnList.add(new OrderByColumnEntity(columnName, FunctionImpl.newInstance(functionName, parameters), sort));
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
