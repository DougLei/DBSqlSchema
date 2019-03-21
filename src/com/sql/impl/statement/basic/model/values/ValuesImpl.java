package com.sql.impl.statement.basic.model.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.values.Values;

/**
 * 
 * @author DougLei
 */
public class ValuesImpl extends BasicModelImpl implements Values {

	private ValuesType type;
	private List<ValuesEntity> valuesEntities;
	
	private String sqlId;
	private JSONObject sqlJson;
	
	public ValuesImpl(JSONObject json) {
		this.type = ValuesType.toValue(json.getString("type"));
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
	}
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(800);
		sb.append("(");
		switch(type){
			case VALUE:
				if(valuesEntities == null || valuesEntities.size() == 0){
					throw new NullPointerException("build insert sql时，values属性中的value数组属性不能为空");
				}
				
				sb.append("values \n");
				for(int i=0;i<valuesEntities.size();i++){
					sb.append(valuesEntities.get(i).getSqlStatement());
					if(i<valuesEntities.size()-1){
						sb.append(", ");
					}
				}
				break;
			case SUB_QUERY:
				sb.append(SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson));
				break;
		}
		sb.append(")");
		return sb.toString();
	}

	public void addValuesEntity(ValuesEntity valuesEntity){
		if(valuesEntities == null){
			valuesEntities = new ArrayList<ValuesEntity>();
		}
		valuesEntities.add(valuesEntity);
	}
	
	// insert into values 后的类型
	private enum ValuesType {
		VALUE, // 值
		SUB_QUERY; // 子查询
		
		public static ValuesType toValue(String str){
			try {
				return ValuesType.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(ValuesType.values())+"]");
			}
		}
		public String toString(){
			return "{" + name() + "}";
		}
	}
}
