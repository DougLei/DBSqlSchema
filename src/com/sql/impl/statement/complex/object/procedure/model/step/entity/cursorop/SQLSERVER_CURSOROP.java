package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_CURSOROP extends AbstractCursorOp{
	
	public SQLSERVER_CURSOROP(List<ConditionGroup> groupList, String cursorName, CursorOpType opType) {
		super(groupList, cursorName, opType);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		String cursorOpSql = getCursorOpSql();
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append(cursorOpSql).append(newline());
			sb.append("end");
		}else{
			sb.append(cursorOpSql);
		}
		sb.append(newline());
		return sb.toString();
	}
}
