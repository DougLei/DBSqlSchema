package com.sql.impl.statement.complex.object.procedure.model.step.entity.declare;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.StepEntityImpl;
import com.sql.statement.complex.object.procedure.model.step.StepType;

/**
 * 
 * @author DougLei
 */
public class DeclareStepEntity extends StepEntityImpl {
	public DeclareStepEntity(String stepId) {
		super(stepId);
	}

	private List<DeclareEntity> declareList;
	
	public void addDeclare(JSONObject json){
		if(declareList == null){
			declareList = new ArrayList<DeclareEntity>();
		}
		DeclareEntity declareEntity = new DeclareEntity(json.getString("name"), json.getString("dataType"), json.getIntValue("length"));
		declareList.add(declareEntity);
		
		JSONArray array = json.getJSONArray("column");
		if(array != null && array.size()>0){
			for(int i=0;i<array.size();i++){
				json = array.getJSONObject(i);
				declareEntity.addColumn(new DeclareColumnEntity(json.getString("name"), json.getString("dataType"), json.getIntValue("length")));
			}
		}
	}
	
	protected String processSqlStatement_SQLSERVER() {
		return null;
	}

	protected String processSqlStatement_ORACLE() {
		return null;
	}

	public StepType getStepType() {
		return StepType.DECLARE;
	}
}
