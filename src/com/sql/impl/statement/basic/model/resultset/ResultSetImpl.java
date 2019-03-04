package com.sql.impl.statement.basic.model.resultset;

import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.util.NameUtil;
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
	
	public String processSqlStatement() {
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = NameUtil.getName(columnName, paramName);
		}
		
		if(StrUtils.notEmpty(alias)){
			sqlStatement += " " + alias;
		}
		return sqlStatement;
	}

	public void setFunction(Function function) {
		if(function == null){
			this.function = function;
		}
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getResultSetColumnName() {
		return getSqlStatement();
	}
}
