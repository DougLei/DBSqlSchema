package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.db;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public abstract class DBElseEntity{

	protected String conditionSqlStatement;
	public DBElseEntity(List<ConditionGroup> groupList) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<groupList.size();i++){
			sb.append(groupList.get(i).getSqlStatement());
			if(i<groupList.size()-1){
				sb.append(" ").append(groupList.get(i).getNextLogicOperator()).append(" ");
			}
		}
		conditionSqlStatement = sb.toString();
	}

	public abstract String getSqlStatement(String sqlStatement);
	
	protected final char newline(){
		return '\n';
	}
}
