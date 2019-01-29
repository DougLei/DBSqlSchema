package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.statement.basic.model.where.LogicOperatorType;

public class ConditionGroup {
	public ConditionGroup(int conditionGroupCount, String nextLogicOperator) {
		setConditionGroupCount(conditionGroupCount);
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
	
	// 记录condition语句的组数数量，决定 xxx 中的xx是否需要加()，如果有多组，会是这种 (xxx) and (xxx)效果，如果只有一组，会是这种 xxx效果
	private int conditionGroupCount = 1;
	private LogicOperatorType nextLogicOperator;
	private List<Condition> conditions;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		if(conditions != null && conditions.size() > 0){
			StringBuilder sb = new StringBuilder(200);
			if(conditionGroupCount > 1){
				sb.append("(");
			}
			for(int i=0;i<conditions.size();i++){
				sb.append(conditions.get(i).getSqlStatement());
				if(i<conditions.size()-1){
					sb.append(" ").append(conditions.get(i).getNextLogicOperator()).append(" ");
				}
			}
			if(conditionGroupCount > 1){
				sb.append(")");
			}
			return sb.toString();
		}
		return null;
	}

	public void addCondition(JSONObject json) {
		if(conditions == null){
			conditions = new ArrayList<Condition>();
		}
		conditions.add(new Condition(json));
	}
	
	private void setConditionGroupCount(int conditionGroupCount) {
		if(conditionGroupCount < 1){
			conditionGroupCount = 1;
		}
		this.conditionGroupCount = conditionGroupCount;
	}
}
