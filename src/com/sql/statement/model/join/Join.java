package com.sql.statement.model.join;

import com.sql.statement.model.Basic;

/**
 * 
 * @author DougLei
 */
public interface Join extends Basic{
	
	void addOn(On on);
}
