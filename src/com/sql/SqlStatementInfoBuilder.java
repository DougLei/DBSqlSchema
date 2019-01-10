package com.sql;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;

/**
 * sql语句 的信息builder
 * @author DougLei
 */
public interface SqlStatementInfoBuilder {
	
	/**
	 * 设置数据库类型
	 * @param databaseType
	 */
	SqlStatementInfoBuilder setDatabaseType(DatabaseType databaseType);
	
	/**
	 * 获取数据库类型
	 * @return
	 */
	DatabaseType getDatabaseType();
	
	/**
	 * 获取sql语句类型
	 * @return
	 */
	SqlStatementType getSqlStatementType();
	
	/**
	 * 设置json配置文件
	 * @param filePath
	 * @return
	 */
	SqlStatementInfoBuilder setJsonConfig(String filePath);
	
	/**
	 * 设置json配置文件
	 * @param file
	 * @return
	 */
	SqlStatementInfoBuilder setJsonConfig(File file);
	
	/**
	 * 设置json配置文件
	 * @param byteArray
	 * @return
	 */
	SqlStatementInfoBuilder setJsonConfig(byte[] byteArray);
	
	/**
	 * 设置json配置内容
	 * @param json
	 * @return
	 */
	SqlStatementInfoBuilder setJson(String json);
	
	/**
	 * 设置json配置内容
	 * @param json
	 * @return
	 */
	SqlStatementInfoBuilder setJson(JSONObject json);

	/**
	 * 获取json配置内容
	 * @return
	 */
	JSONObject getJson();
	
	/**
	 * 验证数据信息
	 */
	void validationInfo();
	
	/**
	 * 对外，build sql语句
	 * @return 最终的sql语句
	 */
	SqlStatementBuilder createSqlStatementBuilder();
}
