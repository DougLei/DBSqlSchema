package com.sql.impl.statement.basic.model.table.db;

import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.table.Table;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_TableImpl extends BasicModelImpl implements Table {

	public boolean isDefaultTable() {
		return true;
	}

	protected String processSqlStatement() {
		return "";
	}
}
