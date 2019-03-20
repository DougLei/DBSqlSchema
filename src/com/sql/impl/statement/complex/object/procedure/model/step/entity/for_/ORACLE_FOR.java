package com.sql.impl.statement.complex.object.procedure.model.step.entity.for_;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.while_.ORACLE_WHILE;

/**
 * 底层调用的是ORACLE_WHILE
 * @author DougLei
 */
public class ORACLE_FOR extends ORACLE_WHILE{

	public ORACLE_FOR(ConditionEntity condition) {
		super(condition);
	}
}
