package com.sql.impl.statement.basic.model.function.builtin.forxmlpath.db;

import com.sql.impl.statement.basic.model.function.builtin.forxmlpath.ForXmlPath;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_ForXmlPath extends ForXmlPath{

	protected void reInitRootNodeName() {
		rootNodeName = "''";
	}

	protected String installSqlStatement(String sqlContent) {
		return sqlContent + " for xml path("+rootNodeName+")";
	}
}
