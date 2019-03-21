package com.sql.impl.statement.basic.insert;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.impl.statement.basic.model.values.ValuesEntity;
import com.sql.impl.statement.basic.model.values.ValuesImpl;
import com.sql.statement.basic.insert.InsertSqlStatementBuilder;
import com.sql.statement.basic.model.values.Values;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class InsertSqlStatementBuilderImpl extends AbstractSqlStatementBuilder implements InsertSqlStatementBuilder {
	protected StringBuilder insertSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		insertSqlStatement.append("insert into ");
		insertSqlStatement.append(getTable());
		
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
		
		Values values = getValues();
		insertSqlStatement.append(values.getSqlStatement());
		
		return insertSqlStatement.toString();
	}

	/**
	 * 获取表名
	 * @return
	 */
	public String getTable() {
		String tableName = content.getString("name");
		if(StrUtils.notEmpty(tableName)){
			return tableName;
		}
		String paramName = content.getString("paramName");
		if(StrUtils.notEmpty(paramName)){
			return Tools.getName(null, paramName);
		}
		throw new NullPointerException("build insert sql时，name属性值和paramName属性值不能都为空");
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
		
		Values values = new ValuesImpl(json);
		JSONArray array = json.getJSONArray("value");
		if(array != null && array.size() > 0){
			for(int i=0;i<array.size();i++){
				values.addValuesEntity(new ValuesEntity(array.getJSONObject(i)));
			}
		}
		return values;
	}
}
