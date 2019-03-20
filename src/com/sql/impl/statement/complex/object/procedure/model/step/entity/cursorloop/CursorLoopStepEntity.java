package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop;

import java.util.List;

import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class CursorLoopStepEntity extends AbstractStepEntity {
	private CursorLoopEntity cursorEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getCursorLoopEntity(cursorEntity.getName(), cursorEntity.getVariableNameList()).getSqlStatement(true, cursorEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getCursorLoopEntity(String cursorName, List<String> variableNameList) {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_CURSORLOOP(cursorName, variableNameList);
			case ORACLE:
				return new ORACLE_CURSORLOOP(cursorName, variableNameList);
		}
		return null;
	}
	
	public StepType getStepType() {
		return StepType.CURSOR_LOOP;
	}

	public void setCursorEntity(CursorLoopEntity cursorEntity) {
		this.cursorEntity = cursorEntity;
	}
}