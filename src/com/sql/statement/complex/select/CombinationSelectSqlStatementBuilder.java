package com.sql.statement.complex.select;

import java.util.List;
import com.sql.statement.complex.select.model.With;

/**
 * 组合select sql语句builder 接口
 * @author DougLei
 */
public interface CombinationSelectSqlStatementBuilder {

	/**
	 * 获取with对象
	 * @return
	 */
	List<With> getWithList();
	
	// --------------------------------------------------------
	/**
	 * 获取最终的查询结果列名集合
	 * @return
	 */
	List<String> getResultSetColumnNames();
	/**
	 * 获取查询WITH语句的body
	 * <p>从from开始</p>
	 * @return
	 */
	String getWithBody();
	/**
	 * 获取查询语句的body
	 * <p>从from开始</p>
	 * @return
	 */
	String getBody();
}
