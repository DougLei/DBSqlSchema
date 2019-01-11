package com.sql.impl.statement.select.model.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.statement.model.join.Join;
import com.sql.statement.model.join.JoinType;
import com.sql.statement.model.join.On;
import com.sql.statement.model.table.TableType;
import com.sql.util.StrUtils;

public class JoinImpl extends BasicImpl implements Join {
	private StringBuilder sb = new StringBuilder();
	
	private JoinType joinType;
	private TableType tableType;
	private String tableName;
	private String alias;
	private List<On> ons;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public void addOn(On on) {
		if(ons == null){
			ons = new ArrayList<On>();
		}
		ons.add(on);
	}
	
	public void addFunction(String functionName, String... parameters) {
	}

	protected void processSqlStatement() {
		sb.append(joinType.toString()).append(" ");
		if(tableType == TableType.TABLE){
			sb.append(tableName);
		}else if(tableType == TableType.SUB_QUERY){
			sb.append("( ");
			if(StrUtils.notEmpty(subSqlId)){
				sb.append(SqlStatementBuilderContext.buildSqlStatement(subSqlId));
			}else{
				// TODO 创建一个select xx builder对象实例，build出结果，然后把该builder实例，也放到SqlStatementBuilderContext中去
				System.out.println(subSqlJson);
			}
			sb.append(" )");
		}
		
		if(StrUtils.notEmpty(alias)){
			sb.append(" ").append(alias);
		}
		
		// 处理on
		if(ons != null && ons.size() > 0){
			sb.append(" on (");
			
			for(int i=0;i<ons.size();i++){
				sb.append(ons.get(i).getSqlStatement());
				if(i<ons.size()-1){
					sb.append(" ").append(ons.get(i).getNextLogicOperator()).append(" ");
				}
			}
			sb.append(")");
		}
		sqlStatement = sb.toString();
	}
	
	public void setJoinType(String joinType) {
		try {
			this.joinType = JoinType.valueOf(joinType.trim().toUpperCase());
		} catch (Exception e) {
		}
		throw new IllegalArgumentException("join节点下的type节点的值["+joinType+"]错误，目前支持的值包括：["+Arrays.toString(JoinType.values())+"]");
	}
	public void setTableType(String tableType) {
		try {
			this.tableType = TableType.valueOf(tableType.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("join节点下的tableType节点的值["+tableType+"]错误，目前支持的值包括：["+Arrays.toString(TableType.values())+"]");
		}
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
	}
}
