package com.sql.impl.statement.basic.model.where;

import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.statement.basic.model.where.Value;
import com.sql.statement.basic.model.where.ValueGroup;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ValueImpl implements Value {

	private Type type;
	
	private ValueGroup valueGroup;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public String[] getSqlStatements() {
		switch(type){
			case VALUE:
				String[] sqlStatements = new String[valueGroup.size()];
				for(int i=0;i<valueGroup.size();i++){
					sqlStatements[i] = valueGroup.getValue(i);
				}
				return sqlStatements;
			case SUB_QUERY:
				StringBuilder sb = new StringBuilder(2000);
				sb.append(" ( ");
				if(StrUtils.notEmpty(subSqlId)){
					sb.append(SqlStatementBuilderContext.buildSqlStatement(subSqlId));
				}else{
					SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
					infoBuilder.setJson(subSqlJson);
					sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
				}
				sb.append(" ) ");
				return new String[]{sb.toString()};
		}
		return null;
	}
	
	public void setType(String type) {
		this.type = Type.toValue(type);
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
	}
	public void setValueGroupArray(JSONArray valueGroupJsonarray) {
		if(valueGroupJsonarray != null && valueGroupJsonarray.size() > 0){
			if(valueGroup == null){
				valueGroup = new ValueGroupImpl();
			}
			for(int i=0;i<valueGroupJsonarray.size();i++){
				valueGroup.setValueEntity(valueGroupJsonarray.getJSONObject(i));
			}
		}
	}
	
	public boolean isNullValueType(){
		return type == Type.NULL_VALUE;
	}
	
	private enum Type {
		NULL_VALUE, 
		VALUE, 
		SUB_QUERY; 
		
		static Type toValue(String str){
			try {
				return Type.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(Type.values())+"]");
			}
		}
		
		public String toString(){
			return "{" + name() + "}";
		}
	}
}
