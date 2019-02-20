package com.sql.impl.statement.complex.object.procedure.model.step.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.StepImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;

/**
 * stepX中content属性的抽象父类
 * @author DougLei
 */
public abstract class AbstractContent {
	protected List<Step> stepList = new ArrayList<Step>();
	
	public AbstractContent(JSONArray content) {
		processContent(content);
	}

	private void processContent(JSONArray content) {
		if(content != null && content.size() > 0){
			JSONObject stepJson = null;
			Step step = null;
			for(int i=0;i<content.size();i++){
				stepJson = content.getJSONObject(i).getJSONObject("stepJson");
				step = new StepImpl();
				step.setJson(-1, stepJson);
				stepList.add(step);
			}
		}
	}

	public String getContent() {
		if(stepList != null && stepList.size() > 0){
			StringBuilder sb = new StringBuilder(stepList.size()*500);
			for(Step s: stepList){
				sb.append(s.getSqlStatement());
			}
			return sb.toString();
		}
		return null;
	}
	
}
