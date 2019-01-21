package com.test.complex;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class ViewTest {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/complex/view.json");
		System.out.println(sql);
	}
}
