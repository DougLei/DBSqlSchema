package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.context.CursorContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop.CursorOpType;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractCursorOp extends LogicEntity{
	protected String cursorName;
	protected CursorOpType opType;
	
	public AbstractCursorOp(List<ConditionGroup> groupList, String cursorName, CursorOpType opType) {
		super(groupList, false);
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
