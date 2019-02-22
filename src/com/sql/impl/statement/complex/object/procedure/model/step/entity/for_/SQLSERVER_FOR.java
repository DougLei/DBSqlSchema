package com.sql.impl.statement.complex.object.procedure.model.step.entity.for_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.while_.SQLSERVER_WHILE;

/**
 * 底层调用的是SQLSERVER_WHILE
 * @author DougLei
 */
public class SQLSERVER_FOR extends SQLSERVER_WHILE{

	public SQLSERVER_FOR(List<ConditionGroup> groupList) {
		super(groupList);
	}
}
