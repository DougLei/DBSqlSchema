package com.sql;

import java.io.File;

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
	void setDatabaseType(DatabaseType databaseType);
	
	/**
	 * 设置sql语句类型
	 * @param sqlStatementType
	 */
	void setSqlStatementType(SqlStatementType sqlStatementType);
	
	/**
	 * 设置json配置文件
	 * @param filePath
	 * @return
	 */
	void setJsonConfig(String filePath);
	
	/**
	 * 设置json配置文件
	 * @param file
	 * @return
	 */
	void setJsonConfig(File file);
	
	/**
	 * 设置json配置文件
	 * @param byteArray
	 * @return
	 */
	void setJsonConfig(byte[] byteArray);
	
	/**
	 * 设置json配置内容
	 * @param json
	 * @return
	 */
	void setJson(String json);
	
	/**
	 * 对外，build sql语句
	 * @return 最终的sql语句
	 */
	SqlStatementBuilder createSqlStatementBuilder();
}
