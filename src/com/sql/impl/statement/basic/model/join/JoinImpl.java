package com.sql.impl.statement.basic.model.join;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.join.Join;
import com.sql.statement.basic.model.join.JoinType;
import com.sql.statement.basic.model.join.OnGroup;
import com.sql.statement.basic.model.table.TableType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class JoinImpl extends BasicModelImpl implements Join {
	
	private JoinType joinType;
	private TableType tableType;
	private String tableName;
	private String alias;
	private List<OnGroup> onGroups;
	
	private String sqlId;
	private JSONObject sqlJson;
	
	public void addOnGroup(OnGroup onGroup) {
		if(onGroups == null){
			onGroups = new ArrayList<OnGroup>();
		}
		onGroups.add(onGroup);
	}
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(500);
		sb.append(joinType.getSqlStatement()).append(" ");
		if(tableType == TableType.TABLE){
			sb.append(tableName);
		}else if(tableType == TableType.SUB_QUERY){
			sb.append("( ").append(SqlStatementBuilderContext.getSqlStatement(sqlId, sqlJson)).append(" )");
		}
		
		if(StrUtils.notEmpty(alias)){
			sb.append(" ").append(alias);
		}
		
		// 处理on
		if(onGroups != null && onGroups.size() > 0){
			sb.append(" on (");
			for(int i=0;i<onGroups.size();i++){
				sb.append(onGroups.get(i).getSqlStatement());
				if(i<onGroups.size()-1){
					sb.append(" ").append(onGroups.get(i).getNextLogicOperator()).append(" ");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	public void setJoinType(String joinType) {
		this.joinType = JoinType.toValue(joinType);
	}
	public void setTableType(String tableType) {
		this.tableType = TableType.toValue(tableType);
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public void setSqlJson(JSONObject sqlJson) {
		this.sqlJson = sqlJson;
	}
}
