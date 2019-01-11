package com.sql.impl.statement.model.groupby;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.statement.model.BasicImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.groupby.GroupBy;

/**
 * 
 * @author DougLei
 */
public class GroupByImpl extends BasicImpl implements GroupBy {

	private List<GroupByColumnEntity> groupByColumnList;
	
	public void addGroupByColumn(String columnName, Function function){
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
