package com.test.busi.procedure;

import com.sql.DBSqlStatementBuilder;
import com.sql.enums.DatabaseType;

public class procinsertSCM_PURCHASEDELIVER {
	public static void main(String[] args) {
		DBSqlStatementBuilder builder = new DBSqlStatementBuilder(DatabaseType.SQLSERVER);
		String sql = builder.buildSqlStatementByFile("resources/busi/procedure/procinsertSCM_PURCHASEDELIVER.json");
		System.out.println(sql);
	}
}
