package com.sql.impl.statement.select.model.resultset;

import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.impl.statement.select.model.function.FunctionImpl;
import com.sql.statement.model.function.Function;
import com.sql.statement.model.resultset.ResultSet;
import com.sql.util.StrUtils;

public class ResultSetImpl extends BasicImpl implements ResultSet {
	private static final long serialVersionUID = -3701295316020326550L;
	
	private String columnName;
	private String alias;
	private Function function;
	
	public void processSqlStatement() {
		if(function != null){
			sqlStatement = function.getSqlStatement(getMainTableAlias(), columnName, alias);
		}
		if(sqlStatement == null){
			sqlStatement = getMainTableAlias() + columnName;
			if(StrUtils.notEmpty(alias)){
				sqlStatement += " " + alias;
			}
		}
	}

	public void addFunction(String functionName, String... parameters) {
		if(function == null){
			function = new FunctionImpl().setName(functionName).setParameters(parameters);
		}
	}
}
