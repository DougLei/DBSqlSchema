package com.sql.statement.basic.model.table;

import com.sql.statement.BasicModel;

/**
 * 
 * @author DougLei
 */
public interface Table extends BasicModel{

	/**
	 * 是否是默认的表
	 * @return
	 */
	boolean isDefaultTable();
}
