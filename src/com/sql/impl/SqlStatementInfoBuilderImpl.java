package com.sql.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.alibaba.fastjson.JSONObject;
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
	protected JSONObject json;
	
	protected SqlStatementType sqlStatementType;
	
	public DatabaseType getDatabaseType() {
		return databaseType;
	}
	public SqlStatementType getSqlStatementType() {
		return sqlStatementType;
	}
	public JSONObject getJson() {
		return json;
	}
	public SqlStatementInfoBuilder setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
		return this;
	}
	
	public SqlStatementInfoBuilder setJsonConfig(String filePath) {
		if(StrUtils.isEmpty(filePath)){
			throw new NullPointerException("filePath参数不能为空");
		}
		return setJsonConfig(new File(filePath));
	}

	public SqlStatementInfoBuilder setJsonConfig(File file) {
		if(!file.exists()){
			throw new NullPointerException("不存在路径为["+file.getAbsolutePath()+"]的文件");
		}
		if(file.isDirectory()){
			throw new IllegalArgumentException("["+file.getAbsolutePath()+"]路径下为文件夹对象，非文件对象");
		}
		try {
			return setJson(IOUtil.readerToString(new FileReader(file), file.getName()));
		} catch (FileNotFoundException e) {
		}
		return this;
	}
	
	public SqlStatementInfoBuilder setJsonConfig(byte[] byteArray) {
		return setJson(StrUtils.getStringByByteArray(byteArray));
	}
	
	public SqlStatementInfoBuilder setJson(String json) {
		if(StrUtils.isEmpty(json)){
			throw new NullPointerException("配置内容(json)不能为空");
		}
		if(!json.trim().startsWith("{")){
			throw new NullPointerException("配置内容(json)格式错误，应为json对象");
		}
		return setJson(JSONObject.parseObject(json));
	}
	
	public SqlStatementInfoBuilder setJson(JSONObject json) {
		this.json = json;
		return this;
	}
	
	public SqlStatementBuilder createSqlStatementBuilder() {
		validationInfo();
		SqlStatementBuilder sqlStatementBuilder = SqlStatementBuilderFactory.createSqlStatementBuilderInstance(sqlStatementType);
		sqlStatementBuilder.setSqlStatementInfoBuilder(this);
		return sqlStatementBuilder;
	}
	
	private boolean isValid;
	public void validationInfo() {
		if(!isValid){
			if(databaseType == null && SqlStatementBuilderContext.getDatabaseType() == null){
				throw new NullPointerException("数据库类型(databaseType)不能为空");
			}
			if(json == null || json.size() == 0){
				throw new NullPointerException("配置内容(json)不能为空");
			}
			if(StrUtils.isEmpty(json.get("id"))){
				json.put("id", StrUtils.getIdentity());
			}
			if(StrUtils.isEmpty(json.get("type"))){
				throw new NullPointerException("配置内容(json)的属性[type]值不能为空");
			}
			this.sqlStatementType = SqlStatementType.toValue(json.getString("type"));
			
			JSONObject tmp = json.getJSONObject("content");
			if(tmp == null || tmp.size() == 0){
				throw new NullPointerException("配置内容(json)的属性content值不能为空");
			}
			
			SqlStatementBuilderContext.setDatabaseType(databaseType);
			isValid = true;
		}
	}
}
