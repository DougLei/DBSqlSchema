package com.sql.statement.complex.object.procedure.model.param;

import com.sql.statement.basic.model.Basic;

/**
 * 存储过程参数
 * @author DougLei
 */
public interface Parameter extends Basic{

	void setName(String name);
	void setDataType(String dataType);
	void setLength(int length);
	void setInOut(String inOut);
}
