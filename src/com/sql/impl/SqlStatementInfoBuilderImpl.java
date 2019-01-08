package com.sql.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.DatabaseType;
import com.sql.enums.SqlStatementType;
import com.sql.util.IOUtil;
import com.sql.util.StrUtils;

/**
 * sql语句 的信息builder的实现类
 * @author DougLei
 */
public class SqlStatementInfoBuilderImpl implements SqlStatementInfoBuilder{

	protected DatabaseType databaseType;
	protected SqlStatementType sqlStatementType;
	protected String json;
	
	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}
	public void setSqlStatementType(SqlStatementType sqlStatementType) {
		this.sqlStatementType = sqlStatementType;
	}

	public void setJsonConfig(String filePath) {
		if(StrUtils.isEmpty(filePath)){
			throw new NullPointerException("filePath参数不能为空");
		}
		setJsonConfig(new File(filePath));
	}

	public void setJsonConfig(File file) {
		if(!file.exists()){
			throw new NullPointerException("不存在路径为["+file.getAbsolutePath()+"]的文件");
		}
		if(file.isDirectory()){
			throw new IllegalArgumentException("["+file.getAbsolutePath()+"]路径下为文件夹对象，非文件对象");
		}
		try {
			json = IOUtil.readerToString(new FileReader(file), file.getName());
		} catch (FileNotFoundException e) {
		}
	}
	
	public void setJsonConfig(byte[] byteArray) {
		json = StrUtils.getStringByByteArray(byteArray);
	}
	
	public void setJson(String json) {
		this.json = json;
	}
	
	public SqlStatementBuilder createSqlStatementBuilder() {
		validationInfo();
		SqlStatementBuilder sqlStatementBuilder = SqlStatementBuilderFactory.createSqlStatementBuilderInstance(databaseType, sqlStatementType);
		sqlStatementBuilder.setJson(json);
		return sqlStatementBuilder;
	}
	
	/**
	 * 验证info
	 */
	private void validationInfo() {
		if(databaseType == null){
			throw new NullPointerException("数据库类型(databaseType)不能为空");
		}
		if(sqlStatementType == null){
			throw new NullPointerException("sql语句的类型(sqlStatementType)不能为空");
		}
		if(StrUtils.isEmpty(json)){
			throw new NullPointerException("配置内容(json)不能为空");
		}
	}
}
