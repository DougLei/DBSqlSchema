package com.sql.impl.statement.complex.object.procedure.model.step.entity.cursorloop;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.model.declare.DeclareContext;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.AbstractContent;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class CursorLoopEntity extends AbstractContent{
	private String name;
	private List<String> variableNameList;
	
	public CursorLoopEntity(JSONObject cursor, JSONArray content) {
		super(content);
		processName(cursor.getString("name"));
		processCursorDeclare(cursor.getJSONObject("declareCursor"));
		processVariablesDeclare(cursor.getJSONArray("declareVariable"));
	}

	private void processName(String name) {
		if(StrUtils.notEmpty(name)){
			this.name = name;
		}
	}
	
	private void processCursorDeclare(JSONObject declareCursorJson) {
		if(StrUtils.notEmpty(declareCursorJson.getString("name"))){
			this.name = declareCursorJson.getString("name");
		}
		DeclareContext.recordDeclare(declareCursorJson);
	}
	
	private void processVariablesDeclare(JSONArray declareVariableArray) {
		int size = declareVariableArray.size();
		variableNameList = new ArrayList<String>(size);
		for(int i=0;i<size;i++){
			variableNameList.add(DeclareContext.recordDeclare(declareVariableArray.getJSONObject(i)).getName());
		}
	}

	public String getName() {
		if(StrUtils.isEmpty(name)){
			throw new NullPointerException("游标变量的名称不能为空");
		}
		return name;
	}
	public List<String> getVariableNameList() {
		return variableNameList;
	}
}
