package com.sql.impl;

import com.sql.SqlStatementBuilder;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {

	protected String json;
	
	public void setJson(String json) {
		this.json = json;
	}
}
