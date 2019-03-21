package com.sql.impl.statement.basic.update;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.impl.statement.basic.model.set.SetImpl;
import com.sql.statement.basic.model.set.Set;
import com.sql.statement.basic.update.UpdateSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class UpdateSqlStatementBuilderImpl extends AbstractSqlStatementBuilder implements UpdateSqlStatementBuilder {
	protected StringBuilder updateSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		updateSqlStatement.append("update ");
		updateSqlStatement.append(getTable());
		
		List<Set> sets = getSetList();
		updateSqlStatement.append(newline()).append("set ");
		for (int i=0;i<sets.size();i++) {
			updateSqlStatement.append(sets.get(i).getSqlStatement());
			if(i<sets.size()-1){
				updateSqlStatement.append(", ");
			}
		}
		
		// where
		updateSqlStatement.append(getWhereSqlStatement());
		return updateSqlStatement.toString();
	}

	public String getTable() {
		String tableName = content.getString("name");
		if(StrUtils.isEmpty(tableName )){
			throw new NullPointerException("build update sql时，name属性值不能为空");
		}
		return tableName;
	}
	
	public List<Set> getSetList() {
		JSONArray setJsonarray = content.getJSONArray("set");
		if(setJsonarray == null || setJsonarray.size() == 0){
			throw new NullPointerException("build update sql时，set属性值不能为空");
		}
		List<Set> sets = new ArrayList<Set>(setJsonarray.size());
		for(int i=0;i<setJsonarray.size();i++){
			sets.add(new SetImpl(setJsonarray.getJSONObject(i)));
		}
		return sets;
	}
}
