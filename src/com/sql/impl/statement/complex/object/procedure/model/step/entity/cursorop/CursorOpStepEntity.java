package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import java.util.List;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorop.ORACLE_CURSOROP;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorop.SQLSERVER_CURSOROP;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class CursorOpStepEntity extends AbstractStepEntity {
	
	private String cursorName;
	private CursorOpType opType;
	private ConditionEntity conditionEntity;
	
	public String getSqlStatement() {
		StringBuilder sb = new StringBuilder(5000);
		sb.append(getCursorOpEntity(conditionEntity.getConditionGroupList()).getSqlStatement(true, conditionEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getCursorOpEntity(List<ConditionGroup> conditionGroupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_CURSOROP(conditionGroupList, cursorName, opType);
			case ORACLE:
				return new ORACLE_CURSOROP(conditionGroupList, cursorName, opType);
		}
		return null;
	}
	
	
	public StepType getStepType() {
		return StepType.CURSOR_OP;
	}

	public void setCursorName(String cursorName) {
		this.cursorName = cursorName;
	}
	public void setConditionEntity(ConditionEntity conditionEntity) {
		this.conditionEntity = conditionEntity;
	}
	public void setOpType(String opType) {
		this.opType = CursorOpType.toValue(opType);
	}
}