package com.test;

import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;
import com.sql.impl.SqlStatementInfoBuilderImpl;

public class ProcedureTest {
	public static void main(String[] args) {
		SqlStatementInfoBuilder info = new SqlStatementInfoBuilderImpl();
		info.setDatabaseType(DatabaseType.SQLSERVER);
		info.setSqlStatementType(SqlStatementType.PROCEDURE);
		info.setJsonConfig("resources/procedure.json");
		SqlStatementBuilder builder = info.createSqlStatementBuilder();
		
		System.out.println(builder.buildSqlStatement());
	}
}
