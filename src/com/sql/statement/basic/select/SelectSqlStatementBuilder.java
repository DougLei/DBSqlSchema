package com.sql.statement.basic.select;

import java.util.List;

import com.sql.statement.basic.model.groupby.GroupBy;
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
	 * 获取having 语句
	 * @return
	 */
	String getHavingSqlStatement();
	
	/**
	 * 获取order by语句对象
	 * @return
	 */
	OrderBy getOrderBy();
	
	// --------------------------------------------------------
	/**
	 * 获取最终的查询结果列名集合
	 * @return
	 */
	List<String> getResultSetColumnNames();
	/**
	 * 获取查询语句的body
	 * <p>从from开始</p>
	 * @return
	 */
	String getBody();
}
