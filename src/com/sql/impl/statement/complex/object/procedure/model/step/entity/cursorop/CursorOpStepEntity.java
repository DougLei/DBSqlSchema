package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
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
		sb.append(getCursorOpEntity().getSqlStatement(true, conditionEntity.getContent()));
		sb.append(newline());
		return sb.toString();
	}
	
	private LogicEntity getCursorOpEntity() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_CURSOROP(conditionEntity, cursorName, opType);
			case ORACLE:
				return new ORACLE_CURSOROP(conditionEntity, cursorName, opType);
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