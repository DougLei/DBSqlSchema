package com.sql.impl.statement.basic.model.function.builtin.forxmlpath.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.forxmlpath.ForXmlPath;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;

/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.ORACLE}, functionName = "_for_xml_path")
public class ORACLE_ForXmlPath extends ForXmlPath{

	protected String installSqlStatement(String sqlContent) {
		return "select for_xml_path(q'/" + sqlContent +"/', " + rootNodeName + ") from dual";
	}
}
