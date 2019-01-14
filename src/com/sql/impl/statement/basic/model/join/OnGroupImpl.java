package com.sql.impl.statement.basic.model.join;

import com.sql.impl.statement.basic.model.where.WhereGroupImpl;
import com.sql.statement.basic.model.join.On;
import com.sql.statement.basic.model.join.OnGroup;

/**
 * 
 * @author DougLei
 */
public class OnGroupImpl extends WhereGroupImpl implements OnGroup {

	public void addOn(On on) {
		addWhere(on);
	}
	
	public void setOnGroupCount(int onGroupCount) {
		setWhereGroupCount(onGroupCount);
	}
}
