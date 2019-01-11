package com.sql.statement.model.where;

/**
 * 
 * @author DougLei
 */
public interface WhereGroup extends Where{

	void addWhere(Where where);
}
