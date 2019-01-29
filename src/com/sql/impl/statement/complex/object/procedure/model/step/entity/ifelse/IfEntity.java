package com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.ifelse.condition.ConditionGroup;
import com.sql.statement.complex.object.procedure.model.step.Step;

/**
 * 
 * @author DougLei
 */
public class IfEntity {

	private List<ConditionGroup> groupList;
	private List<Step> stepList = new ArrayList<Step>();
	
	public IfEntity(JSONArray condition, JSONArray content) {
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
		if(groupList == null || groupList.size() == 0){
			throw new NullPointerException("if语句的条件不能为空");
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

	public List<ConditionGroup> getGroupList() {
		return groupList;
	}

	public String getContent() {
		return null;
	}
}
