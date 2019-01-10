package com.sql;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.SqlStatementType;

/**
 * sql语句 builder
 * @author DougLei
 */
public interface SqlStatementBuilder {
	
	void setSqlStatementInfoBuilder(SqlStatementInfoBuilder infoBuilder);
	SqlStatementInfoBuilder getSqlStatementInfoBuilder();
	
	/**
	 * 获取json配置内容
	 * @return
	 */
	JSONObject getJson();
	
	/**
	 * 获取配置文件id
	 * @return
	 */
	String getId();
	
	/**
	 * 获取配置文件desc
	 * @return
	 */
	String getDescription();
	
	/**
	 * 获取sql语句类型
	 * @return
	 */
	SqlStatementType getSqlStatementType();
	
	/**
	 * 创建sql语句
	 * @return
	 */
	String buildSqlStatement();
}
