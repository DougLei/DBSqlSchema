package com.sql.impl.statement.basic.model.function.builtin.forxmlpath.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.forxmlpath.ForXmlPath;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_for_xml_path")
public class SQLSERVER_ForXmlPath extends ForXmlPath{

	protected void reInitRootNodeName() {
		rootNodeName = "''";
	}

	protected String installSqlStatement(String sqlContent) {
		return sqlContent + " for xml path("+rootNodeName+")";
	}
}
