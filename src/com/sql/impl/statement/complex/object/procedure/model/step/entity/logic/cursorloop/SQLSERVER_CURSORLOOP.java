package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.cursorloop;

import java.util.List;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_CURSORLOOP extends AbstractCursorLoop{
	
	public SQLSERVER_CURSORLOOP(String cursorName, List<String> variableNameList) {
		super(cursorName, variableNameList);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		sb.append("open ").append(cursorName).append(newline());
		sb.append("fetch next from ").append(cursorName).append(" into ").append(getVariableNames()).append(newline());
		sb.append("while @@fetch_status = 0").append(newline());
		sb.append("begin").append(newline());
		sb.append(sqlStatement).append(newline());
		sb.append("end").append(newline());
		sb.append("close ").append(cursorName).append(newline());
		sb.append("deallocate ").append(cursorName).append(newline());
		return sb.toString();
	}

	protected String getVariableNames() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<variableNameList.size();i++){
			sb.append("@").append(variableNameList.get(i));
			if(i < variableNameList.size()-1){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
