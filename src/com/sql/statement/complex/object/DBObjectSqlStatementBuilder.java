package com.sql.statement.complex.object;

/**
 * 数据库对象通用的sql builder接口
 * @author DougLei
 */
public interface DBObjectSqlStatementBuilder {
	/**
	 * 是否覆盖
	 * @return
	 */
	boolean isCover();
	
	/**
	 * sqlserver覆盖的sql语句
	 * @param procedureName
	 * @return
	 */
	public String coverSqlServerSql(String procedureName);
	
	/**
	 * oracle覆盖的sql语句
	 * @return
	 */
	public String coverOracleSql();
}
