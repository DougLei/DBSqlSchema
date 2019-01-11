package com.sql.impl.statement.model.having;

import com.sql.impl.statement.model.where.WhereGroupImpl;
import com.sql.statement.model.having.Having;
import com.sql.statement.model.having.HavingGroup;

/**
 * 
 * @author DougLei
 */
public class HavingGroupImpl extends WhereGroupImpl implements HavingGroup{
	
	public void addHaving(Having having) {
		addWhere(having);
	}
	
	public void setHavingGroupCount(int havingGroupCount) {
		setWhereGroupCount(havingGroupCount);
	}
}
