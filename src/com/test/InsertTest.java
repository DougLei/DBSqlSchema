package com.test;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class InsertTest {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/insert.json");
		System.out.println(sql);
		
		
		
	}
}
