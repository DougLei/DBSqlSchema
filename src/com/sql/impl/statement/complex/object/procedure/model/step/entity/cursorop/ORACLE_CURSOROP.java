package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public class ORACLE_CURSOROP extends AbstractCursorOp{
	
	public ORACLE_CURSOROP(ConditionEntity condition, String cursorName, CursorOpType opType) {
		super(condition, cursorName, opType);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		String cursorOpSql = getCursorOpSql();
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
			sb.append("begin").append(newline());
			sb.append(cursorOpSql).append(";").append(newline());
			sb.append("end if;");
		}else{
			sb.append(cursorOpSql).append(";");
		}
		sb.append(newline());
		return sb.toString();
	}
}
