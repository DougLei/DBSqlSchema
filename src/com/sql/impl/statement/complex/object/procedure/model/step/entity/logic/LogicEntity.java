package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition.ConditionGroup;

/**
 * 
 * @author DougLei
 */
public abstract class LogicEntity{

	protected String conditionSqlStatement;
	public LogicEntity(List<ConditionGroup> groupList, boolean validGroupListIsNull) {
		if(validGroupListIsNull && groupList == null || groupList.size() == 0){
			throw new NullPointerException("条件不能为空");
		}
		
		if(groupList != null && groupList.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<groupList.size();i++){
				sb.append(groupList.get(i).getSqlStatement());
				if(i<groupList.size()-1){
					sb.append(" ").append(groupList.get(i).getNextLogicOperator()).append(" ");
				}
			}
			conditionSqlStatement = sb.toString();
		}else{
			conditionSqlStatement="";
		}
	}

	public abstract String getSqlStatement(boolean isEnd, String sqlStatement);
	
	protected final char newline(){
		return '\n';
	}
	
	protected StringBuilder buildSqlStringBuilder(String sqlStatement){
		return new StringBuilder(sqlStatement.length() + conditionSqlStatement.length() + 100);
	}
}
