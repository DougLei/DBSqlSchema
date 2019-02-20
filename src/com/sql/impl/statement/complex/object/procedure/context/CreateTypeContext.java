package com.sql.impl.statement.complex.object.procedure.context;

import java.util.Map;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareEntity;

/**
 * 
 * @author DougLei
 */
public class CreateTypeContext {
	/**
	 * 声明变量的sql语句，可能在存储过程体中也会声明变量，所以这里统一记录，最后统一放到存储过程delcare块中
	 */
	private static final ThreadLocal<Map<String, DeclareEntity>> declareListLocal = new ThreadLocal<Map<String, DeclareEntity>>();
}
