package com.sql.impl.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class StepImpl extends BasicImpl implements Step {

	private StepType type;
	private JSONObject content;
	
	protected String processSqlStatement() {
		return null;
	}

	public void setType(Object type) {
		if(type == null){
			throw new NullPointerException("step的type属性值不能为空");
		}
		this.type = StepType.toValue(type.toString());
	}

	public void setContent(JSONObject content) {
		this.content = content;
	}
}
