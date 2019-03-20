package com.sql.impl.statement.basic.model.orderby;

import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.orderby.Sort;

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
		if(function == null){
			sqlStatement = columnName;
		}else{
			sqlStatement = function.getSqlStatement();
		}
		if(sqlStatement != null){
			sqlStatement += " " + sort.getSqlStatement();
		}
		return sqlStatement;
	}
}
