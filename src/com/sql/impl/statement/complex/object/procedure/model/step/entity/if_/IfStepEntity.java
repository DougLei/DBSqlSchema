package com.sql.impl.statement.complex.object.procedure.model.step.entity.if_;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.condition.ConditionGroup;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.impl.ORACLE_IF;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.if_.impl.SQLSERVER_IF;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class IfStepEntity extends AbstractStepEntity {

	public StepType getStepType() {
		return StepType.IF;
	}

	private List<ConditionGroup> groupList;
	
	public void setCondition(JSONArray condition) {
		if(condition != null && condition.size() > 0){
			groupList = new ArrayList<ConditionGroup>(condition.size());
			
			JSONObject json = null;
			JSONArray groupArray = null;
			ConditionGroup group = null;
			for(int i=0;i<condition.size();i++){
				json = condition.getJSONObject(i);
				groupArray = json.getJSONArray("conditionGroup");
				if(groupArray != null && groupArray.size() > 0){
					group = new ConditionGroup(groupArray.size(), json.getString("nextLogicOperator"));
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
	
	public String getSqlStatement() {
		return getDBIfEntity(groupList).getSqlStatement(super.getSqlStatement());
	}
	private DBIfEntity getDBIfEntity(List<ConditionGroup> groupList) {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_IF(groupList);
			case ORACLE:
				return new ORACLE_IF(groupList);
		}
		return null;
	}

	private List<Step> stepList = new ArrayList<Step>();
	public void addStep(JSONObject stepJson) {
		Step step = new StepImpl();
		step.setJson(-1, stepJson);
		stepList.add(step);
	}
}