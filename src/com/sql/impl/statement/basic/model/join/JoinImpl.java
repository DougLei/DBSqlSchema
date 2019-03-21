package com.sql.impl.statement.basic.model.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.statement.basic.model.join.Join;
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
	
	public JoinImpl(JSONObject json) {
		this.joinType = JoinType.toValue(json.getString("type"));
		this.tableType = TableType.toValue(json.getString("tableType"));
		this.tableName = json.getString("tableName");
		this.alias = json.getString("alias");
		this.sqlId = json.getString("sqlId");
		this.sqlJson = json.getJSONObject("sqlJson");
	}

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
	
	/**
	 * join的类型
	 * @author DougLei
	 */
	private enum JoinType {
		INNER("inner join"),
		LEFT("left join"),
		RIGHT("right join"),
		FULL("full join");
		
		private String sqlStatement;
		private JoinType(String sqlStatement) {
			this.sqlStatement = sqlStatement;
		}
		
		public static JoinType toValue(String str){
			try {
				return JoinType.valueOf(str.trim().toUpperCase());
			} catch (Exception e) {
				throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(JoinType.values())+"]");
			}
		}
		
		public String getSqlStatement() {
			return sqlStatement;
		}
		public String toString() {
			return "{"+sqlStatement+"}";
		}
	}
}
