package com.sql.impl.statement.select.model.table;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.statement.model.table.Table;
import com.sql.statement.model.table.TableType;
import com.sql.util.StrUtils;

public class TableImpl extends BasicImpl implements Table {
	private String name;
	private String alias;
	private TableType tableType;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public void addFunction(String functionName, String... parameters) {
		// TableImpl暂时没必要实现addFunction方法
	}

	public void processSqlStatement() {
		if(tableType == TableType.TABLE){
			sqlStatement = name;
			if(StrUtils.notEmpty(alias)){
				sqlStatement += " " + alias;
			}
		}else if(tableType == TableType.SUB_QUERY){
			if(StrUtils.notEmpty(subSqlId)){
				sqlStatement = SqlStatementBuilderContext.buildSqlStatement(subSqlId);
			}else{
				// TODO 创建一个select xx builder对象实例，build出结果，然后把该builder实例，也放到SqlStatementBuilderContext中去
			}
		}
	}

	public TableType getTableType() {
		return tableType;
	}
	public String getAlias() {
		return alias;
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
	public TableImpl setAlias(String alias) {
		this.alias = alias;
		return this;
	}
	public void setTableType(String tableType) {
		try {
			this.tableType = TableType.valueOf(tableType.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("table节点下的type节点的值错误，目前支持的值包括：["+Arrays.toString(TableType.values())+"]");
		}
	}
}
