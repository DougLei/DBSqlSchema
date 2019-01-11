package com.sql.impl.statement.select.model.resultset;

import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.impl.statement.select.model.function.FunctionImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.resultset.ResultSet;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ResultSetImpl extends BasicImpl implements ResultSet {
	private String columnName;
	private String alias;
	private Function function;
	
	public String processSqlStatement() {
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = columnName;
		}
		
		if(StrUtils.notEmpty(alias)){
			sqlStatement += " " + alias;
		}
		return sqlStatement;
	}

	public void addFunction(String functionName, String... parameters) {
		if(function == null){
			function = new FunctionImpl(functionName, parameters);
		}
	}
}
