package com.sql.impl.statement.model.orderby;

import com.sql.statement.model.function.Function;
import com.sql.statement.model.orderby.Sort;

/**
 * 
 * @author DougLei
 */
public class OrderByColumnEntity {
	private String columnName;
	private Function function;
	private Sort sort;
	
	public OrderByColumnEntity(String columnName, Function function, String sort) {
		this.columnName = columnName;
		this.function = function;
		this.sort = Sort.toValue(sort);
	}
	
	public String getSqlStatement(){
		String sqlStatement = null;
		if(function != null){
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement == null){
			sqlStatement = columnName;
		}
		if(sqlStatement != null){
			sqlStatement += " " + sort.getSqlStatement();
		}
		return sqlStatement;
	}
}
