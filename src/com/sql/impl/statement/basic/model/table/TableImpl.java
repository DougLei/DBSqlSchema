package com.sql.impl.statement.basic.model.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementInfoBuilder;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.SqlStatementInfoBuilderImpl;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.function.Function;
import com.sql.statement.basic.model.table.Table;
import com.sql.statement.basic.model.table.TableType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class TableImpl extends BasicModelImpl implements Table {
												
	private String name;
	private String alias;
	private TableType tableType;
	
	private Function function;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public String processSqlStatement() {
		String sqlStatement = null;
		if(tableType == TableType.TABLE){
			sqlStatement = name;
		}else if(tableType == TableType.FUNCTION){
			sqlStatement = function.getSqlStatement();
		}else if(tableType == TableType.SUB_QUERY){
			sqlStatement = "( ";
			if(StrUtils.notEmpty(subSqlId)){
				sqlStatement += SqlStatementBuilderContext.buildSqlStatement(subSqlId);
			}else{
				SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
				infoBuilder.setJson(subSqlJson);
				sqlStatement += infoBuilder.createSqlStatementBuilder().buildSqlStatement();
			}
			sqlStatement += " )";
		}
		if(StrUtils.notEmpty(alias)){
			sqlStatement += " " + alias;
		}
		return sqlStatement;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
	}
	public void setAlias(Object alias) {
		this.alias = StrUtils.isEmpty(alias)?null:alias.toString();
	}
	public void setTableType(String tableType) {
		this.tableType = TableType.toValue(tableType);
	}
	public void setFunction(Function function) {
		this.function = function;
	}
}
