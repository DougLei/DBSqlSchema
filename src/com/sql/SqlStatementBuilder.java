package com.sql;

import java.io.File;

import com.sql.enums.DatabaseType;
import com.sql.enums.Encoding;

/**
 * 
 * @author DougLei
 */
public interface SqlStatementBuilder {
	
	/**
	 * 设置数据库类型
	 * @param databaseType
	 */
	void setDatabaseType(DatabaseType databaseType);
	/**
	 * 设置编码格式
	 * @param encoding
	 */
	void setEncoding(Encoding encoding);

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
	
}
