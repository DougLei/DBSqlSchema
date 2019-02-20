package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorloop;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.context.CursorContext;

/**
 * 
 * @author DougLei
 */
public class ORACLE_CURSORLOOP extends AbstractCursorLoop{

	public ORACLE_CURSORLOOP(String cursorName, List<String> variableNameList) {
		super(cursorName, variableNameList);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("open ").append(cursorName).append(";").append(newline());
		sb.append(CursorContext.getFetchSql(cursorName)).append(newline());
		sb.append("while ").append(cursorName).append("%found loop").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end loop;").append(newline());
		sb.append("close ").append(cursorName).append(";").append(newline());
		return sb.toString();
	}

	protected String getVariableNames() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<variableNameList.size();i++){
			sb.append(variableNameList.get(i));
			if(i < variableNameList.size()-1){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
