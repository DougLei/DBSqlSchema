package com.sql.impl.statement.model.join;

import com.sql.impl.statement.model.where.WhereGroupImpl;
import com.sql.statement.model.join.On;
import com.sql.statement.model.join.OnGroup;

/**
 * 
 * @author DougLei
 */
public class OnGroupImpl extends WhereGroupImpl implements OnGroup {

	public void addOn(On on) {
		addWhere(on);
	}
}
