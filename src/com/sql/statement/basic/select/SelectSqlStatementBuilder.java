package com.sql.statement.basic.select;

import java.util.List;

import com.sql.statement.basic.model.groupby.GroupBy;
import com.sql.statement.basic.model.having.HavingGroup;
import com.sql.statement.basic.model.join.Join;
import com.sql.statement.basic.model.orderby.OrderBy;
import com.sql.statement.basic.model.resultset.ResultSet;
import com.sql.statement.basic.model.table.Table;

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
	 * 获取where 语句
	 * @return
	 */
	String getWhereSqlStatement();
	
	/**
	 * 获取group by语句对象
	 * @return
	 */
	GroupBy getGroupBy();
	
	/**
	 * 获取having语句对象集合
	 * @return
	 */
	List<HavingGroup> getHavingGroupList();
	
	/**
	 * 获取order by语句对象
	 * @return
	 */
	OrderBy getOrderBy();
}
