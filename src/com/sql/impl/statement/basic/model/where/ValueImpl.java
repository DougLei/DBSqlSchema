package com.sql.impl.statement.basic.model.where;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.where.Value;
import com.sql.statement.basic.model.where.ValueType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ValueImpl implements Value {

	private ValueType type;
	private String[] values;
	private Function[] functions;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public String[] getSqlStatements() {
		if(type == ValueType.VALUE){
			List<String> list = new ArrayList<String>();
			if(values != null){
				for (String v : values) {
					list.add(v);
				}
			}
			if(functions != null){
				for (Function f : functions) {
					if(f != null){
						list.add(f.getSqlStatement());
					}
				}
			}
			
			String[] sqlStatements = new String[list.size()];
			for(int i=0;i<list.size();i++){
				sqlStatements[i] = list.get(i);
			}
			return sqlStatements;
		}else if(type == ValueType.SUB_QUERY){
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
		this.type = ValueType.toValue(type);
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
	}
	public void setValueArray(JSONArray valueJsonarray) {
		if(valueJsonarray != null && valueJsonarray.size() > 0){
			values = new String[valueJsonarray.size()];
			for(int i=0;i<valueJsonarray.size();i++){
				values[i] = valueJsonarray.getString(i);
			}
		}
	}
	public void setValueFunctionArray(JSONArray functionJsonarray) {
		if(functionJsonarray != null && functionJsonarray.size() > 0){
			functions = new Function[functionJsonarray.size()];
			JSONObject func = null;
			for(int i=0;i<functionJsonarray.size();i++){
				func = functionJsonarray.getJSONObject(i);
				functions[i] = FunctionImpl.newInstance(func.getString("name"), func.getJSONArray("parameters"));
			}
		}
	}
}
