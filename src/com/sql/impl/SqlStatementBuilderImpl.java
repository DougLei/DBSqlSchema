package com.sql.impl;

import com.sql.SqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * sql语句 builder实现类
 * @author DougLei
 */
public abstract class SqlStatementBuilderImpl implements SqlStatementBuilder {

	protected String json;
	
	public void setJson(String json) {
		this.json = json;
	}

	public String buildSqlStatement() {
		if(StrUtils.isEmpty(json)){
			throw new NullPointerException("配置内容(json)不能为空");
		}
		return buildFinalSql();
	}
	
	/**
	 * 创建最终的sql语句
	 * @return
	 */
	protected abstract String buildFinalSql();
}
