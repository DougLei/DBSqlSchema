package com.sql.impl.statement.basic.model.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.values.Values;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ValuesImpl extends BasicModelImpl implements Values {

	private ValuesType type;
	private List<ValuesEntity> valuesEntities;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(800);
		switch(type){
			case VALUE:
				if(valuesEntities == null || valuesEntities.size() == 0){
					throw new NullPointerException("build insert sql时，values属性中的value数组属性不能为空");
				}
				
				sb.append("(");
				for(int i=0;i<valuesEntities.size();i++){
					sb.append(valuesEntities.get(i).getSqlStatement());
					if(i<valuesEntities.size()-1){
						sb.append(", ");
					}
				}
				sb.append(")");
				break;
			case SUB_QUERY:
				if(StrUtils.notEmpty(subSqlId)){
					sb.append(SqlStatementBuilderContext.buildSqlStatement(subSqlId));
				}else{
					SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
					infoBuilder.setJson(subSqlJson);
					sb.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
				}
				break;
		}
		return sb.toString();
	}

	public void addValuesEntity(ValuesEntity valuesEntity){
		if(valuesEntities == null){
			valuesEntities = new ArrayList<ValuesEntity>();
		}
		valuesEntities.add(valuesEntity);
	}
	public void setType(String type) {
		this.type = ValuesType.toValue(type);
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
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
