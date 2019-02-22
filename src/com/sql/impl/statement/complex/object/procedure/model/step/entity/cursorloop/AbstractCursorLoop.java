package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.context.CursorContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractCursorLoop extends LogicEntity{
	protected String cursorName;
	protected List<String> variableNameList;
	
	public AbstractCursorLoop(String cursorName, List<String> variableNameList) {
		super(null, false);
		this.cursorName = cursorName;
		this.variableNameList = variableNameList;
		CursorContext.recordCursor(cursorName, variableNameList, getVariableNames());
	}
	
	protected abstract String getVariableNames();
}
