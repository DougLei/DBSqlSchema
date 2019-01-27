package com.sql.impl.statement.complex.object.procedure.model.step.entity.sql;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractStepEntity;
import com.sql.statement.complex.object.procedure.model.step.StepType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SqlStepEntity extends AbstractStepEntity {

	private List<SqlEntity> list;
	
	public void addSql(String sqlId, JSONObject sqlJson){
		if(StrUtils.isEmpty(sqlId) && (sqlJson == null || sqlJson.size() == 0)){
			return;
		}
		if(list == null){
			list = new ArrayList<SqlEntity>();
		}
		list.add(new SqlEntity(sqlId, sqlJson));
	}
	
	public StepType getStepType() {
		return StepType.SQL;
	}

	public String getSqlStatement() {
		if(list != null && list.size() > 0){
			StringBuilder sb = new StringBuilder(list.size()*500);
			for (SqlEntity se : list) {
				sb.append(se.getSqlStatement()).append(";").append(newline());
			}
			return sb.toString();
		}
		return null;
	}
}
