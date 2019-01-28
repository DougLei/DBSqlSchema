package com.sql.impl.statement.complex.object.procedure.model.step.entity;

import java.util.ArrayList;
import java.util.List;

import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public abstract class AbstractStepEntity implements StepEntity {

	private String stepId;
	private String desc;
	
	private List<AbstractEntity> list;
	
	public String getSqlStatement() {
		if(list != null && list.size() > 0){
			StringBuilder sb = new StringBuilder(list.size()*500);
			for (AbstractEntity ae : list) {
				sb.append(ae.getSqlStatement()).append(";").append(newline());
			}
			return sb.toString();
		}
		return null;
	}
	
	protected void addList(AbstractEntity entity){
		if(list == null){
			list = new ArrayList<AbstractEntity>();
		}
		list.add(entity);
	}
	
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStepId() {
		return stepId;
	}
	public String getDesc() {
		return desc;
	}
	
	protected static final char newline(){
		return '\n';
	}
}
