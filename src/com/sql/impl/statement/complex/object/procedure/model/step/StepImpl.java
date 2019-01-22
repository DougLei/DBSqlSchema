package com.sql.impl.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.statement.complex.object.procedure.model.step.StepType;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public class StepImpl extends BasicImpl implements Step {

	private StepType type;
	private StepEntity stepEntity;
	
	protected String processSqlStatement() {
		return stepEntity.getSqlStatement();
	}

	public void setType(String type) {
		this.type = StepType.toValue(type);
	}

	public void setJson(int stepIndex, JSONObject json) {
		if(type == null){
			Object type = null;
			if((type = json.remove("type")) == null){
				throw new NullPointerException("[step"+stepIndex+"] 的type属性值不能为空");
			}
			setType(type.toString());
		}
		stepEntity = type.buildStepEntity(json);
	}
}
