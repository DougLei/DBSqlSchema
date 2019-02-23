package com.sql.impl.statement.complex.object.procedure.model;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.impl.statement.complex.object.procedure.InOut;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractDataType {
	private DataType dataType;
	private JSONObject customJson;
	
	private int length;
	private int precision;
	private InOut inOut;
	
	protected void setLength(int length) {
		this.length = length;
	}
	protected void setPrecision(Object precision) {
		if(StrUtils.isEmpty(precision)){
			precision = -1;
		}else{
			this.precision = Integer.valueOf(precision.toString());
		}
	}

	protected void setDataType(String dataType){
		this.dataType = DataType.toValue(dataType);
	}
	protected void setInOut(String inOut) {
		if(StrUtils.isEmpty(inOut)){
			this.inOut = InOut.IN;
		}else{
			this.inOut = InOut.toValue(inOut);
		}
	}
	
	public String getBaseDataType() {
		return dataType.getBaseDataType();
	}
	
	public int getLength() {
		return dataType.calcLength(length);
	}
	public int getPrecision() {
		return dataType.calcPrecision(precision);
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

	public boolean isIN(){
		return inOut == InOut.IN;
	}
	public boolean isOUT(){
		return inOut == InOut.OUT;
	}
	public boolean isIN_OUT(){
		return inOut == InOut.INOUT;
	}
}
