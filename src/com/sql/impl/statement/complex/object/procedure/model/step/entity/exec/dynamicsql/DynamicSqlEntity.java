package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec.dynamicsql;

import com.alibaba.fastjson.JSONObject;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class DynamicSqlEntity {
	private String dynamicSqlStatement;
	private String dynamicSqlParamName;
	
	public DynamicSqlEntity(JSONObject dynamicSqlEntityJson) {
		this.dynamicSqlStatement = dynamicSqlEntityJson.getString("dynamicSqlStatement");
		this.dynamicSqlParamName = dynamicSqlEntityJson.getString("dynamicSqlParamName");
	}
	
	public boolean isSqlStatement(){
		return StrUtils.notEmpty(dynamicSqlStatement);
	}
	
	public boolean isParameter(){
		return StrUtils.notEmpty(dynamicSqlParamName);
	}
	
	public String getDynamicSql(){
		if(isSqlStatement()){
			return dynamicSqlStatement;
		}
		if(isParameter()){
			return dynamicSqlParamName;
		}
		throw new NullPointerException("执行动态sql语句时，dynamicSql属性下的dynamicSqlStatement或dynamicSqlParamName参数，必须有一个不能为空");
	}
}
