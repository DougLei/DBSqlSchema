package com.sql.impl.statement.complex.object.procedure.model.step;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.basic.model.BasicImpl;
import com.sql.statement.complex.object.procedure.model.step.Step;
import com.sql.statement.complex.object.procedure.model.step.StepType;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class StepImpl extends BasicImpl implements Step {
	private String id;
	private StepType type;
	private StepEntity stepEntity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		// TODO 后续要在缓存判断该id是否重复
		// TODO 如果缓存中存在，则直接取出stepEntity，给属性stepEntity赋值
		this.id = id;
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
			setId(id.toString());
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
		}
	}
}
