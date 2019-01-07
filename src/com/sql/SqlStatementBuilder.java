package com.sql;

import java.io.File;

import com.sql.enums.DatabaseTypeEnum;
import com.sql.enums.EncodingEnum;

/**
 * 
 * @author DougLei
 */
public interface SqlStatementBuilder {
	
	/**
	 * 设置数据库类型
	 * @param databaseType
	 */
	void setDatabaseType(DatabaseTypeEnum databaseType);
	/**
	 * 设置编码格式
	 * @param encoding
	 */
	void setEncoding(EncodingEnum encoding);

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
