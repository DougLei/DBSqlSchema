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
	private String desc;
	private StepEntity stepEntity;
	
	public String getId() {
		return id;
	}
	
	public String getDesc() {
		return desc;
	}

	protected String processSqlStatement() {
		return stepEntity.getSqlStatement();
	}

	public void setJson(int stepIndex, JSONObject json) {
		if(id == null){
			Object id = null;
			if(StrUtils.isEmpty((id = json.remove("id")))){
				id = StrUtils.getIdentity();
			}
			setId(id.toString().trim());
		}
		if(type == null){
			setType(stepIndex, json.remove("type"));
		}
		if(desc == null){
			setDesc(json.remove("desc"));
		}
		if(stepEntity == null){
			stepEntity = type.buildStepEntity(this, json);
			StepContext.putStepEntityCache(stepEntity);
		}
	}
	
	private void setId(String id) {
		this.id = id;
		stepEntity = StepContext.getStepEntity(id);
		if(stepEntity != null){
			type = stepEntity.getStepType();
			desc = stepEntity.getDesc();
		}
	}
	
	private void setType(int stepIndex, Object type) {
		if(StrUtils.isEmpty(type)){
			throw new NullPointerException("[step"+stepIndex+"] 的type属性值不能为空");
		}
		this.type = StepType.toValue(type.toString());
	}

	private void setDesc(Object desc) {
		if(StrUtils.isEmpty(desc)){
			this.desc = "";
		}else{
			this.desc = desc.toString().trim();
		}
	}
}
