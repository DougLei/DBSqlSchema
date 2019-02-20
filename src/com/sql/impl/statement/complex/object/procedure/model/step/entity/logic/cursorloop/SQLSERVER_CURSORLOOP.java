package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorloop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.LogicEntity;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_CURSORLOOP extends LogicEntity{

	public SQLSERVER_CURSORLOOP(String cursorName, List<String> variableNameList) {
		super(null, false);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("while ").append(conditionSqlStatement).append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end").append(newline());
		return sb.toString();
	}
}
