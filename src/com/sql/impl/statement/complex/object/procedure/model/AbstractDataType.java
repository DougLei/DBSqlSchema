package com.sql.impl.statement.complex.object.procedure.model;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.DataType;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDataType {
	private DataType dataType;
	private JSONObject customJson;
	
	protected int length;
	
	protected void setDataType(String dataType){
		this.dataType = DataType.toValue(dataType);
	}
	
	public String getBaseDataType() {
		return dataType.getBaseDataType();
	}
	
	public int getLength() {
		return dataType.calcLength(length);
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
		if(customJson == null || customJson.size() == 0){
			return false;
		}
		return customJson.getBooleanValue("isCreateType");
	}
	
	/**
	 * 获取创建类型的sql语句
	 * @return
	 */
	public String getCreateTypeSqlStatement() {
		if(dataType.isSupportCreateType()){
			return dataType.getCreateTypeSqlStatement(customJson);
		}
		return null;
	}
	
	/**
	 * 是否支持追加自定义类型语句
	 * @return
	 */
	public boolean isSupportAppendCustomSqlStatement() {
		return dataType.isSupportAppendCustomSqlStatement();
	}
	
	/**
	 * 获取要追加的自定义类型语句
	 * @return
	 */
	public String getAppendCustomSqlStatement() {
		return getAppendCustomSqlStatement(null);
	}
	/**
	 * 获取要追加的自定义类型语句
	 * @param name 参数/属性名称
	 * @return
	 */
	public String getAppendCustomSqlStatement(String name) {
		return dataType.getAppendCustomSqlStatement(name, customJson);
	}
}
