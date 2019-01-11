package com.sql.impl.statement.select;

import java.util.List;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.model.groupby.GroupBy;
import com.sql.statement.model.having.Having;
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
public abstract class SelectSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements SelectSqlStatementBuilder {
	protected StringBuilder selectSqlStatement = new StringBuilder(5000);
	
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
		List<WhereGroup> whereGroupList = getWhereGroupList();
		if(whereGroupList != null && whereGroupList.size() > 0){
			selectSqlStatement.append(" where ");
			for (WhereGroup whereGroup : whereGroupList) {
				selectSqlStatement.append(whereGroup.getSqlStatement());
			}
			selectSqlStatement.append(newline());
		}
		
		// group by
		List<GroupBy> groupByList = getGroupByList();
		if(groupByList != null && groupByList.size() > 0){
			selectSqlStatement.append(" group by ");
			for(int i=0;i<groupByList.size();i++){
				selectSqlStatement.append(groupByList.get(i).getSqlStatement());
				if(i < groupByList.size()-1){
					selectSqlStatement.append(", ");
				}
			}
			selectSqlStatement.append(newline());
		}
		
		// having
		List<Having> havingList = getHavingList();
		if(havingList != null && havingList.size() > 0){
			selectSqlStatement.append(" having ");
			for(int i=0;i<havingList.size();i++){
				selectSqlStatement.append(havingList.get(i).getSqlStatement());
				if(i < havingList.size()-1){
					selectSqlStatement.append(", ");
				}
			}
			selectSqlStatement.append(newline());
		}
		
		// order by
		List<OrderBy> orderByList = getOrderByList();
		if(orderByList != null && orderByList.size() > 0){
			selectSqlStatement.append(" order by ");
			for(int i=0;i<orderByList.size();i++){
				selectSqlStatement.append(orderByList.get(i).getSqlStatement());
				if(i < orderByList.size()-1){
					selectSqlStatement.append(", ");
				}
			}
			selectSqlStatement.append(newline());
		}
		
		return selectSqlStatement.toString();
	}

}
