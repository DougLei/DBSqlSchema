package com.sql.impl.statement.complex.object.procedure.model;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDataType {
	private DataType dataType;
	private JSONObject customJson;
	
	protected void setDataType(String dataType){
		this.dataType = DataType.toValue(dataType);
	}
	
	public String getDataType() {
		return dataType.getDataType();
	}
	
	/**是否是基础类型*/
	public boolean isBaseType(){ 
		return dataType.isBaseType();
	}
	
	public void setCustomJson(JSONObject customJson) {
		this.customJson = customJson;
	}
	
	/**
	 * 是否要创建类型
	 * @return
	 */
	public boolean isCreateType() {
		return customJson.getBooleanValue("isCreateType");
	}
	
	/**
	 * 获取创建类型的sql语句
	 * @return
	 */
	public String getCreateTypeSqlStatement() {
		return dataType.getCreateTypeSqlStatement(customJson);
	}
	
	/**
	 * 获取要追加的自定义类型语句
	 * @return
	 */
	public String getAppendCustomSqlStatement() {
		return dataType.getAppendCustomSqlStatement(customJson);
	}
}
