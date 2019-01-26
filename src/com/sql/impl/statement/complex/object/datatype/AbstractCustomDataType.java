package com.sql.impl.statement.complex.object.datatype;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractCustomDataType implements CustomDataType{
	
	public String getCreateTypeSqlStatement(JSONObject customJson) {
		if(customJson.getBooleanValue("isCreateType")){
			String typeName = customJson.getString("typeName");
			if(!CustomDataTypeContext.customDataTypeIsExists(getCustomDataType(), typeName)){
				return getCreateTypeSql(customJson);
			}
		}
		return null;
	}
	
	protected abstract DataType getCustomDataType();
	protected abstract String getCreateTypeSql(JSONObject customJson);
	
	protected static final char newline(){
		return '\n';
	}
	
	protected void appendColumnSql(JSONArray columnJsonarray, StringBuilder sb){
		if(columnJsonarray != null && columnJsonarray.size()>0){
			JSONObject json = null;
			DataType dataType = null;
			int length = 0;
			int precision = -1;
			for(int i=0;i<columnJsonarray.size();i++){
				length = 0;
				precision = -1;
				
				json = columnJsonarray.getJSONObject(i);
				dataType = DataType.toValue(json.getString("dataType"));
				sb.append(json.getString("name")).append(" ").append(dataType.getBaseDataType());
				
				if((length = dataType.calcLength(json.getIntValue("length"))) > 0 ){
					sb.append("(").append(length);
					if(StrUtils.notEmpty(json.get("precision")) && (precision = dataType.calcPrecision(json.getIntValue("precision"))) > -1){
						sb.append(", ").append(precision);
					}
					sb.append(")");
				}
				if(i<columnJsonarray.size()-1){
					sb.append(",");
				}
				sb.append(newline());
			}
		}
	}
}
