package com.test.busi.procedure;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class DeleteMaterialinfo {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/busi/procedure/DeleteMaterialinfo.json");
		System.out.println(sql);
	}
}
