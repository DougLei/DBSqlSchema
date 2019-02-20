package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop;

import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorloop.ORACLE_CURSORLOOP;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorloop.SQLSERVER_CURSORLOOP;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class CursorLoopStepEntity extends AbstractStepEntity {
	private CursorEntity cursorEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getCursorLoopEntity(cursorEntity.getName(), cursorEntity.getVariableNameList()).getSqlStatement(true, cursorEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getCursorLoopEntity(String cursorName, List<String> variableNameList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
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

	public void setCursorEntity(CursorEntity cursorEntity) {
		this.cursorEntity = cursorEntity;
	}
}