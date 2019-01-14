package com.sql.impl.statement.basic.model.having;

import com.sql.impl.statement.basic.model.where.WhereGroupImpl;
import com.sql.statement.basic.model.having.Having;
import com.sql.statement.basic.model.having.HavingGroup;

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
