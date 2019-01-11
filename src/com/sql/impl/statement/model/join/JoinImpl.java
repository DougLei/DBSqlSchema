package com.sql.impl.statement.model.join;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.model.BasicImpl;
import com.sql.statement.model.join.Join;
import com.sql.statement.model.join.JoinType;
import com.sql.statement.model.join.OnGroup;
import com.sql.statement.model.table.TableType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class JoinImpl extends BasicImpl implements Join {
	
	private JoinType joinType;
	private TableType tableType;
	private String tableName;
	private String alias;
	private List<OnGroup> onGroups;
	
	private String subSqlId;
	private JSONObject subSqlJson;
	
	public void addOnGroup(OnGroup onGroup) {
		if(onGroups == null){
			onGroups = new ArrayList<OnGroup>();
		}
		onGroups.add(onGroup);
	}
	
	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append(joinType.getSqlStatement()).append(" ");
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
	public void setSubSqlId(String subSqlId) {
		this.subSqlId = subSqlId;
	}
	public void setSubSqlJson(JSONObject subSqlJson) {
		this.subSqlJson = subSqlJson;
	}
}
