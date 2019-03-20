package com.sql.impl.statement.complex.object.procedure.model.step.entity;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.IsExistsCondition;

/**
 * 
 * @author DougLei
 */
public abstract class LogicEntity{

	protected String conditionSqlStatement;
	public LogicEntity(ConditionEntity condition, boolean validGroupListIsNull) {
		IsExistsCondition isExistsCondition = condition.getIsExistsCondition();
		List<ConditionGroup> groupList = condition.getConditionGroupList();
		
		if(validGroupListIsNull && isExistsCondition == null && (groupList == null || groupList.size() == 0)){
			throw new NullPointerException("条件不能为空");
		}
		
		if(isExistsCondition != null){
			conditionSqlStatement = isExistsCondition.getSqlStatement();
		}else if(groupList != null && groupList.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<groupList.size();i++){
				sb.append(groupList.get(i).getSqlStatement());
				if(i<groupList.size()-1){
					sb.append(" ").append(groupList.get(i).getNextLogicOperator()).append(" ");
				}
			}
			conditionSqlStatement = sb.toString();
		}else{
			conditionSqlStatement = "";
		}
	}

	public abstract String getSqlStatement(boolean isEnd, String sqlStatement);
	
	protected final char newline(){
		return '\n';
	}
	
	protected StringBuilder buildSqlStringBuilder(String sqlStatement){
		int length = sqlStatement==null ?0:sqlStatement.length();
		return new StringBuilder(length + conditionSqlStatement.length() + 100);
	}
}
