package com.sql.impl.statement;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.DynamicCreateTypeContext;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class Tools {

	/**
	 * 按照从左往右的顺序(即优先级)，返回第一个不为空的名称
	 * @param columnName 列名
	 * @param parameterName 参数名
	 * @return
	 */
	public static String getName(String columnName, String paramName){
		if(StrUtils.notEmpty(columnName)){
			return columnName;
		}
		if(StrUtils.notEmpty(paramName)){
			DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
			return getParamName(paramName, dbType);
		}
		return null;
	}
	
	/**
	 * 获取不同类型的数据库，对应的参数名称
	 * @param parameterName 参数名
	 * @param dbType
	 * @return
	 */
	public static String getParamName(String paramName, DatabaseType dbType){
		switch(dbType){
			case SQLSERVER:
				return "@"+DynamicCreateTypeContext.getDynamicCreateTypeName(paramName);
			case ORACLE:
				return DynamicCreateTypeContext.getDynamicCreateTypeName(paramName);
		}
		return null;
	}
}
