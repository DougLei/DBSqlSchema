package com.test.complex;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class ProcedureTest {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.ORACLE);
		String sql = builder.buildSqlStatementByFile("resources/complex/procedure.json");
		System.out.println(sql);
	}
}
