package com.test;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class SelectTest {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/select.json");
		System.out.println(sql);
	}
}
