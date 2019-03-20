package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import com.sql.impl.statement.complex.object.procedure.model.CursorContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractCursorOp extends LogicEntity{
	protected String cursorName;
	protected CursorOpType opType;
	
	public AbstractCursorOp(ConditionEntity condition, String cursorName, CursorOpType opType) {
		super(condition, false);
		this.cursorName = cursorName;
		this.opType = opType;
	}
	
	protected String getCursorOpSql() {
		switch(opType){
			case FETCH:
				return CursorContext.getFetchSql(cursorName);
		}
		return null;
	}
}
