package com.sql.statement.model.where;

import java.util.List;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public interface Where extends Basic {
	
	/**
	 * 获取同一组的where语句对象集合
	 * @return
	 */
	List<Where> getWhereList();
}
