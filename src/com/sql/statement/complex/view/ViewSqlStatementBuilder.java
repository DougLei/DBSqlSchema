package com.sql.statement.complex.view;

/**
 * create view sql语句builder 接口
 * @author DougLei
 */
public interface ViewSqlStatementBuilder {
	
	/**
	 * 是否覆盖
	 * @return
	 */
	boolean isCover();
	
	/**
	 * sqlserver覆盖视图的sql语句
	 * @param viewName
	 * @return
	 */
	public String coverSqlServerSql(String viewName);
	
	/**
	 * oracle覆盖视图的sql语句
	 * @return
	 */
	public String coverOracleSql();
}
