package com.sql.impl.statement.complex.object.procedure.model.step.entity.sql;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractEntity;
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
	
	/**
	 * 
	 * @author DougLei
	 */
	private class SqlEntity extends AbstractEntity{
		private String sqlId;
		private JSONObject sqlJson;
		
		public SqlEntity(String sqlId, JSONObject sqlJson) {
			this.sqlId = sqlId;
			this.sqlJson = sqlJson;
		}

		public String getSqlStatement(){
			return SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson);
		}
	}
}
