package com.sql.impl.statement.complex.object.procedure.model.step.entity.exec;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractExecStepEntity extends AbstractStepEntity{
	protected ConditionEntity conditionEntity;
	protected List<ExecParameter> execParameterList;
	
	public AbstractExecStepEntity(JSONArray execParameter, JSONArray condition) {
		setExecParameterList(execParameter);
		conditionEntity = new ConditionEntity(condition, null);
	}
	
	private void setExecParameterList(JSONArray execParameter) {
		if(execParameter != null && execParameter.size()>0){
			int size = execParameter.size();
			execParameterList = new ArrayList<ExecParameter>(size);
			for(int i=0;i<size;i++){
				execParameterList.add(new ExecParameter(execParameter.getJSONObject(i)));
			}
		}
	}
}
