package com.test;

import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementInfoBuilderImpl;

public class SelectTest {
	public static void main(String[] args) {
		SqlStatementInfoBuilder info = new SqlStatementInfoBuilderImpl();
		info.setDatabaseType(DatabaseType.SQLSERVER);
		info.setJsonConfig("resources/select.json");
		SqlStatementBuilder builder = info.createSqlStatementBuilder();
		
		System.out.println(builder.buildSqlStatement());
	}
}
