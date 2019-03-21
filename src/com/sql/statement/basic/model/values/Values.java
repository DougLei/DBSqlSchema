package com.sql.statement.basic.model.values;

import com.sql.impl.statement.basic.model.values.ValuesEntity;
import com.sql.statement.BasicModel;

/**
 * 
 * @author DougLei
 */
public interface Values extends BasicModel{

	/**
	 * 添加values实体
	 * @param valuesEntity
	 */
	void addValuesEntity(ValuesEntity valuesEntity);
}
