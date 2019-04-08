package com.test.busi.procedure;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class SuperSelectSql {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/busi/procedure/SuperSelectSql.json");
		System.out.println(sql);
	}
}
