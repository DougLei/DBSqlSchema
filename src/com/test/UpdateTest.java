package com.test;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class UpdateTest {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/update.json");
		System.out.println(sql);
	}
}
