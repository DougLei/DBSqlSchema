package com.sql.impl.statement.complex.object.procedure.model.step.entityfactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.sql.SqlStepEntity;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;
import com.sql.statement.complex.object.procedure.model.step.entityfactory.StepEntityFactory;

/**
 * 
 * @author DougLei
 */
public class SqlStepEntityFactory implements StepEntityFactory {
	private SqlStepEntityFactory(){}
	private static final SqlStepEntityFactory factory = new SqlStepEntityFactory();
	public static SqlStepEntityFactory newInstance() {
		return factory;
	}
	
	public StepEntity buildEntity(JSONObject stepContent) {
		JSONArray content = stepContent.getJSONArray("content");
		SqlStepEntity sqlStepEntity = new SqlStepEntity();
		JSONObject json = null;
		for(int i=0;i<content.size();i++){
			json = content.getJSONObject(i);
			sqlStepEntity.addSql(json.getString("sqlId"), json.getJSONObject("sqlJson"));
		}
		return sqlStepEntity;
	}
}
