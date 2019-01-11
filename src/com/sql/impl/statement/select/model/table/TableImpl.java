package com.sql.impl.statement.select.model.table;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.statement.model.table.Table;
import com.sql.statement.model.table.TableType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class TableImpl extends BasicImpl implements Table {
												
	private String name;
	private String alias;
	private TableType tableType;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public String processSqlStatement() {
		String sqlStatement = null;
		if(tableType == TableType.TABLE){
			sqlStatement = name;
		}else if(tableType == TableType.SUB_QUERY){
			sqlStatement = "( ";
			if(StrUtils.notEmpty(subSqlId)){
				sqlStatement += SqlStatementBuilderContext.buildSqlStatement(subSqlId);
			}else{
				// TODO 创建一个select xx builder对象实例，build出结果，然后把该builder实例，也放到SqlStatementBuilderContext中去
				System.out.println(subSqlJson);
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
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setTableType(String tableType) {
		this.tableType = TableType.toValue(tableType);
	}
}
