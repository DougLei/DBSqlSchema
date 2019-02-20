package com.sql.impl.statement.complex.object.procedure.model.step.entity.logic.condition;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractContent;

/**
 * 
 * @author DougLei
 */
public class ConditionEntity extends AbstractContent{
	private List<ConditionGroup> groupList;
	
	public ConditionEntity(JSONArray condition, JSONArray content) {
		super(content);
		processCondition(condition);
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
	
	public List<ConditionGroup> getConditionGroupList() {
		return groupList;
	}
}
