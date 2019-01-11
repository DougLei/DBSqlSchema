package com.sql.statement.select;

import java.util.List;

import com.sql.statement.model.groupby.GroupBy;
import com.sql.statement.model.having.Having;
import com.sql.statement.model.join.Join;
import com.sql.statement.model.orderby.OrderBy;
import com.sql.statement.model.resultset.ResultSet;
import com.sql.statement.model.table.Table;
import com.sql.statement.model.where.WhereGroup;

/**
 * select sql语句builder 接口
 * @author DougLei
 */
public interface SelectSqlStatementBuilder {
	
	/**
	 * 获取select 结果列对象集合
	 * @return
	 */
	List<ResultSet> getResultSetList();
	
	/**
	 * 获取from 表对象
	 * @return
	 */
	Table getTable();
	
	/**
	 * 获取join语句对象集合
	 * @return
	 */
	List<Join> getJoinList();
	
	/**
	 * 获取whereGroup语句对象集合
	 * @return
	 */
	List<WhereGroup> getWhereGroupList();
	
	/**
	 * 获取group by语句对象集合
	 * @return
	 */
	List<GroupBy> getGroupByList();
	
	/**
	 * 获取having语句对象集合
	 * @return
	 */
	List<Having> getHavingList();
	
	/**
	 * 获取order by语句对象集合
	 * @return
	 */
	List<OrderBy> getOrderByList();
}
