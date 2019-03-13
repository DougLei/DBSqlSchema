package com.sql.impl.statement.basic.model.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.Tools;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.table.Table;
import com.sql.statement.basic.model.table.TableType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class TableImpl extends BasicModelImpl implements Table {
	private TableType type;
	
	private String name;
	private String alias;
	
	private String paramName;
	
	private Function function;
	
	private String sqlId;
	private JSONObject sqlJson;
	
	public String processSqlStatement() {
		String sqlStatement = null;
		
		switch(type){
			case TABLE:
				sqlStatement = name;
				break;
			case PARAMETER:
				sqlStatement = Tools.getName(null, paramName);
				break;
			case FUNCTION:
				sqlStatement = function.getSqlStatement();
				break;
			case SUB_QUERY:
				sqlStatement = "( " + SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson) + " )";
				break;
		}
		
		if(StrUtils.notEmpty(alias)){
			sqlStatement += " " + alias;
		}
		return sqlStatement;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public void setSqlJson(JSONObject sqlJson) {
		this.sqlJson = sqlJson;
	}
	public void setAlias(Object alias) {
		this.alias = StrUtils.isEmpty(alias)?null:alias.toString();
	}
	public void setType(String type) {
		this.type = TableType.toValue(type);
	}
	public void setFunction(Function function) {
		this.function = function;
	}

	public boolean isDefaultTable() {
		return false;
	}
}
