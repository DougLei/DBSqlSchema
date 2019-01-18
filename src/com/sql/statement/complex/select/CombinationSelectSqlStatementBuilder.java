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
}
