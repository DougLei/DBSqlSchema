package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop.CursorOpType;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class ORACLE_CURSOROP extends AbstractCursorOp{
	
	public ORACLE_CURSOROP(List<ConditionGroup> groupList, String cursorName, CursorOpType opType) {
		super(groupList, cursorName, opType);
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
