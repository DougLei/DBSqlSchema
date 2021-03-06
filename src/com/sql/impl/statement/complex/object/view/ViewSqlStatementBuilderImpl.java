package com.sql.impl.statement.complex.object.view;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.complex.object.view.ViewSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ViewSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ViewSqlStatementBuilder {
	protected StringBuilder viewSqlStatement = new StringBuilder(8000);
	
	protected String buildSql() {
		String viewName = content.getString("viewName");
		if(StrUtils.isEmpty(viewName)){
			throw new NullPointerException("viewName属性值不能为空");
		}
		
		boolean isCover = isCover();
		if(isCover){
			viewSqlStatement.append(coverSqlServerSql(viewName));
		}
		viewSqlStatement.append("create ");
		if(isCover){
			viewSqlStatement.append(coverOracleSql());
		}
		
		viewSqlStatement.append("view as ");
		viewSqlStatement.append(newline());
		
		
		JSONObject sql = content.getJSONObject("sql");
		viewSqlStatement.append(SqlStatementBuilderContext.getSqlStatement(sql.getString("sqlId"), sql.getJSONObject("sqlJson")));
		return viewSqlStatement.toString();
	}

	public boolean isCover() {
		return content.getBoolean("isCover");
	}
}
