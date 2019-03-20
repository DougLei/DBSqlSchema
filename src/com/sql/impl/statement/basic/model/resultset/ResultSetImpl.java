package com.sql.impl.statement.basic.model.resultset;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.resultset.ResultSet;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ResultSetImpl extends BasicModelImpl implements ResultSet {
	private String columnName;
	private String paramName;
	private String alias;
	private Function function;
	
	public ResultSetImpl(JSONObject json) {
		this.columnName = json.getString("columnName");
		this.paramName = json.getString("paramName");
		this.function = FunctionImpl.newInstance(json.getJSONObject("function"));
		this.alias = json.getString("alias");
	}
	
	public String processSqlStatement() {
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = Tools.getName(columnName, paramName);
		}
		
		if(StrUtils.notEmpty(alias)){
			sqlStatement += " " + alias;
		}
		return sqlStatement;
	}

	public String getResultSetColumnName() {
		return getSqlStatement();
	}
}
