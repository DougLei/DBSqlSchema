package com.sql.impl.statement.complex.object.procedure.model.step.entity.condition;

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
	private IsExistsCondition isExistsCondition;
	private List<ConditionGroup> groupList;
	
	public ConditionEntity(JSONObject isExistsCondition, JSONArray condition, JSONArray content) {
		super(content);
		if(!processIsExistsCondition(isExistsCondition)){
			processCondition(condition);
		}
	}

	/**
	 * 处理is exists条件值
	 * @param isExistsConditionJson
	 * @return 如果配置了is exists条件返回true，否则返回false
	 *         如果配置了is exists条件，则后续的condition条件，即使配置了也无效
	 */
	private boolean processIsExistsCondition(JSONObject isExistsConditionJson) {
		if(isExistsConditionJson != null && isExistsConditionJson.size() > 0){
			isExistsCondition = new IsExistsCondition(isExistsConditionJson);
			return true;
		}
		return false;
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
	
	public IsExistsCondition getIsExistsCondition() {
		return isExistsCondition;
	}
	public List<ConditionGroup> getConditionGroupList() {
		return groupList;
	}
}
