package com.sql.impl.statement.complex.select.model;

import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.complex.select.model.With;

/**
 * 
 * @author DougLei
 */
public class WithImpl extends BasicModelImpl implements With {

	private String alias;
	private String sqlStatement;// 存储的是处理后的select sql语句，再和alias整合
	
	public WithImpl(String alias) {
		this.alias = alias;
	}

	protected String processSqlStatement() {
		return alias + " as (" + sqlStatement + ") ";
	}

	public void setSqlStatement(StringBuilder sqlStatement) {
		this.sqlStatement = sqlStatement.toString();
	}
}
