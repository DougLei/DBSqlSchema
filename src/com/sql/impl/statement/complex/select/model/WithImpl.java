package com.sql.impl.statement.complex.select.model;

import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.complex.select.model.With;

/**
 * 
 * @author DougLei
 */
public class WithImpl extends BasicImpl implements With {

	private String alias;
	private String sqlStatement;
	
	public WithImpl(String alias) {
		this.alias = alias;
	}

	protected String processSqlStatement() {
		return alias + " (" + sqlStatement + ") ";
	}

	public void setSqlStatement(StringBuilder sqlStatement) {
		this.sqlStatement = sqlStatement.toString();
	}
}
