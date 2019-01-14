package com.sql.impl.statement.insert;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.statement.model.values.ValuesEntity;
import com.sql.impl.statement.model.values.ValuesImpl;
import com.sql.statement.insert.InsertSqlStatementBuilder;
import com.sql.statement.model.values.Values;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class InsertSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements InsertSqlStatementBuilder {
	protected StringBuilder insertSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		insertSqlStatement.append("insert into ");
		
		String tableName = getTableName();
		if(StrUtils.isEmpty(tableName)){
			throw new NullPointerException("build insert sql时，tableName属性值不能为空");
		}
		insertSqlStatement.append(tableName);
		
		List<String> columnNames = getColumnNames();
		if(columnNames != null && columnNames.size() > 0){
			insertSqlStatement.append("(");
			for(int i=0;i<columnNames.size();i++){
				insertSqlStatement.append(columnNames.get(i));
				if(i<columnNames.size()-1){
					insertSqlStatement.append(", ");
				}
			}
			insertSqlStatement.append(")");
		}
		insertSqlStatement.append(newline());
		insertSqlStatement.append("values ");
		insertSqlStatement.append(newline());
		
		Values values = getValues();
		insertSqlStatement.append(values.getSqlStatement());
		
		return insertSqlStatement.toString();
	}

	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
		return content.getString("tableName");
	}
	
	/**
	 * 获取要插入的列名集合
	 * @return
	 */
	public List<String> getColumnNames() {
		JSONArray array = content.getJSONArray("column");
		if(array != null && array.size() > 0){
			List<String> columnNames = new ArrayList<String>(array.size());
			for(int i=0;i<array.size();i++){
				columnNames.add(array.getString(i));
			}
			return columnNames;
		}
		return null;
	}
	
	/**
	 * 获取values后的内容对象
	 * @return
	 */
	public Values getValues() {
		JSONObject json = content.getJSONObject("values");
		if(json == null || json.size() == 0){
			throw new NullPointerException("build insert sql时，values属性值不能为空");
		}
		
		ValuesImpl values = new ValuesImpl();
		values.setType(json.getString("type"));
		values.setSubSqlId(json.getString("subSqlId"));
		values.setSubSqlJson(json.getJSONObject("subSqlJson"));
		
		JSONArray array = json.getJSONArray("value");
		if(array != null && array.size() > 0){
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				values.addValuesEntity(new ValuesEntity(json.getString("value"), getFunction(json.getJSONObject("valueFunction"))));
			}
		}
		return values;
	}
}
