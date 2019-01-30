package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;

/**
 * 
 * @author DougLei
 */
public class ConditionEntity {

	private List<ConditionGroup> groupList;
	private List<Step> stepList = new ArrayList<Step>();
	
	public ConditionEntity(JSONArray condition, JSONArray content) {
		processCondition(condition);
		processContent(content);
	}

	private void processCondition(JSONArray condition) {
		if(condition != null && condition.size() > 0){
			int size = condition.size();
			groupList = new ArrayList<ConditionGroup>(size);
			
			JSONObject json = null;
			JSONArray groupArray = null;
			ConditionGroup group = null;
			for(int i=0;i<size;i++){
				json = condition.getJSONObject(i);
				groupArray = json.getJSONArray("conditionGroup");
				if(groupArray != null && groupArray.size() > 0){
					group = new ConditionGroup(size, json.getString("nextLogicOperator"));
					for(int j=0;j<groupArray.size();j++){
						group.addCondition(groupArray.getJSONObject(j));
					}
					groupList.add(group);
				}
			}
		}
	}
	
	private void processContent(JSONArray content) {
		JSONObject stepJson = null;
		Step step = null;
		for(int i=0;i<content.size();i++){
			stepJson = content.getJSONObject(i).getJSONObject("stepJson");
			step = new StepImpl();
			step.setJson(-1, stepJson);
			stepList.add(step);
		}
	}

	public List<ConditionGroup> getConditionGroupList() {
		return groupList;
	}

	public String getContent() {
		StringBuilder sb = new StringBuilder(stepList.size()*500);
		for(Step s: stepList){
			sb.append(s.getSqlStatement());
		}
		return sb.toString();
	}
}
