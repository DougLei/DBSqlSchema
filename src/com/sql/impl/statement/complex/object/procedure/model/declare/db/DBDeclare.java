package com.sql.impl.statement.complex.object.procedure.model.declare.db;

import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;

/**
 * 
 * @author DougLei
 */
public interface DBDeclare {

	/**
	 * 将declare对象，转换为sql语句
	 * @param declareEntity
	 * @return
	 */
	String toDeclareSqlStatement(DeclareEntity declareEntity);
}
