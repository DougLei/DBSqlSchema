package com.sql.impl.statement.basic.model.join;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.where.WhereGroupImpl;
import com.sql.statement.basic.model.join.On;
import com.sql.statement.basic.model.join.OnGroup;

/**
 * 
 * @author DougLei
 */
public class OnGroupImpl extends WhereGroupImpl implements OnGroup {

	public OnGroupImpl(JSONObject json, int size) {
		super(json, size);
	}

	public void addOn(On on) {
		addWhere(on);
	}
}
