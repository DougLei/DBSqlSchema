package com.sql.impl.statement.basic.model.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.Tools;
import com.sql.impl.statement.basic.model.function.FunctionImpl;
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
	
	public TableImpl(JSONObject json) {
		this.type = TableType.toValue(json.getString("type"));
		this.name = json.getString("name");
		this.paramName = json.getString("paramName");
		this.function = FunctionImpl.newInstance(json.getJSONObject("function"));
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
		this.alias = StrUtils.isEmpty(json.getString("alias"))?null:json.getString("alias").toString();
	}
	
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

	public boolean isDefaultTable() {
		return false;
	}
}
