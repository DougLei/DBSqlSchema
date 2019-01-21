package com.sql.impl.statement.complex.view;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.statement.complex.view.ViewSqlStatementBuilder;
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
		if(StrUtils.notEmpty(sql.getString("sqlId"))){
			viewSqlStatement.append(SqlStatementBuilderContext.buildSqlStatement(sql.getString("sqlId")));
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(sql.getJSONObject("sqlJson"));
			viewSqlStatement.append(infoBuilder.createSqlStatementBuilder().buildSqlStatement());
		}
		return viewSqlStatement.toString();
	}

	public boolean isCover() {
		return content.getBoolean("isCover");
	}
}
