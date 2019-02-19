package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.for_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.while_.ORACLE_WHILE;

/**
 * 
 * @author DougLei
 */
public class ORACLE_FOR extends ORACLE_WHILE{

	public ORACLE_FOR(List<ConditionGroup> groupList) {
		super(groupList);
	}
}
