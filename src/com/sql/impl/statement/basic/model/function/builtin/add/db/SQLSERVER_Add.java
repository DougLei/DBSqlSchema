package com.sql.impl.statement.basic.model.function.builtin.add.db;

import com.sql.enums.DatabaseType;
import com.sql.impl.statement.basic.model.function.builtin.append.db.SQLSERVER_Append;
import com.sql.statement.basic.model.function.BuiltinFunctionAnnotation;


/**
 * 
 * @author DougLei
 */
@BuiltinFunctionAnnotation(supportDtabaseType = {DatabaseType.SQLSERVER}, functionName = "_add")
public class SQLSERVER_Add extends SQLSERVER_Append{

}
