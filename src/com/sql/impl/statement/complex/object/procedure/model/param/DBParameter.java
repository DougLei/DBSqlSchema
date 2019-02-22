package com.sql.impl.statement.complex.object.procedure.model.param;


/**
 * 
 * @author DougLei
 */
public interface DBParameter {

	/**
	 * 将parameter对象，转换为sql语句
	 * @param isFirst 是否是第一个元素
	 * @param isEnd 是否是最后一个元素
	 * @param parameterEntity
	 * @return
	 */
	String toParameterSqlStatement(boolean isFirst, boolean isEnd, ParameterEntity parameterEntity);
}
