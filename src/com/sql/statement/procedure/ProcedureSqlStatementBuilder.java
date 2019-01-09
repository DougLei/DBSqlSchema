package com.sql.statement.procedure;

/**
 * procedure sql语句builder 接口
 * @author DougLei
 */
public interface ProcedureSqlStatementBuilder {
	
	/**
	 * 是否覆盖之前的存储过程
	 * 如果为true，则buildCoverProcedureSql()
	 * 否则，没有buildCoverProcedureSql()语句
	 * 默认为true
	 * @return
	 */
	boolean isCover();
	
	/**
	 * 生成 判断存储过程如果存在，则删除的sql语句
	 * @return
	 */
	String buildCoverProcedureSql();
	
	/**
	 * 获取存储过程名称
	 * @return
	 */
	String getProcedureName();
	
	/**
	 * 生成 参数的sql语句
	 * @return
	 */
	String buildParameterSql();
	
	/**
	 * 生成 主体的sql语句
	 * @return
	 */
	String buildBodySql();
}
