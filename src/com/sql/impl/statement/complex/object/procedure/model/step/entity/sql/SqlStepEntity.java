package com.sql.impl.statement.complex.object.procedure.model.step.entity.sql;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SqlStepEntity extends AbstractStepEntity {

	public void addSql(String sqlId, JSONObject sqlJson){
		if(StrUtils.isEmpty(sqlId) && (sqlJson == null || sqlJson.size() == 0)){
			return;
		}
		addList(new SqlEntity(sqlId, sqlJson));
	}
	
	public StepType getStepType() {
		return StepType.SQL;
	}
}
