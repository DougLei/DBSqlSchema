package com.sql.impl.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.statement.complex.object.procedure.model.step.StepType;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class StepImpl extends BasicModelImpl implements Step {
	private String id;
	private StepType type;
	private StepEntity stepEntity;
	
	public String getId() {
		return id;
	}
	
	protected String processSqlStatement() {
		return stepEntity.getSqlStatement();
	}

	public void setType(String type) {
		this.type = StepType.toValue(type);
	}

	public void setJson(int stepIndex, JSONObject json) {
		if(id == null){
			Object id = null;
			if(StrUtils.isEmpty((id = json.remove("id")))){
				throw new NullPointerException("[step"+stepIndex+"] 的id属性值不能为空");
			}
			setId(id.toString().trim());
		}
		if(type == null){
			Object type = null;
			if(StrUtils.isEmpty((type = json.remove("type")))){
				throw new NullPointerException("[step"+stepIndex+"] 的type属性值不能为空");
			}
			setType(type.toString());
		}
		if(stepEntity == null){
			stepEntity = type.buildStepEntity(id, json);
			StepContext.putStepEntityCache(stepEntity);
		}
	}
	
	private void setId(String id) {
		this.id = id;
		stepEntity = StepContext.getStepEntity(id);
		if(stepEntity != null){
			type = stepEntity.getStepType();
		}
	}
}
