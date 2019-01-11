package com.sql;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;

/**
 * sql语句对外提供builder的接口
 * @author DougLei
 */
public class DBSqlStatementBuilder {
	private SqlStatementInfoBuilder info;
	
	public DBSqlStatementBuilder(DatabaseType databaseType) {
		info = new SqlStatementInfoBuilderImpl();
		info.setDatabaseType(databaseType);
	}

	public String buildSqlStatementByFile(String filePath){
		info.setJsonConfig(filePath);
		return buildSqlStatement();
	}
	
	public String buildSqlStatementByFile(File file){
		info.setJsonConfig(file);
		return buildSqlStatement();
	}
	
	public String buildSqlStatementByByteArray(byte[] byteArray){
		info.setJsonConfig(byteArray);
		return buildSqlStatement();
	}
	
	public String buildSqlStatementByJson(String json){
		info.setJson(json);
		return buildSqlStatement();
	}
	
	public String buildSqlStatementByJson(JSONObject json){
		info.setJson(json);
		return buildSqlStatement();
	}

	private String buildSqlStatement() {
		try {
			return info.createSqlStatementBuilder().buildSqlStatement();
		} finally {
			SqlStatementBuilderContext.clear();
		}
	}
}
