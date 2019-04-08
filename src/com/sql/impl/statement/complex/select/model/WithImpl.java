package com.sql.impl.statement.complex.select.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.BasicModelImpl;
import com.sql.impl.statement.basic.model.resultset.ResultSetImpl;
import com.sql.statement.basic.model.resultset.ResultSet;
import com.sql.statement.complex.select.model.With;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class WithImpl extends BasicModelImpl implements With {

	private String alias;
	private List<ResultSet> rs;
	private int rsSize;
	
	private String sqlStatement;// 存储的是处理后的select sql语句，再和alias整合
	
	public WithImpl(JSONObject json) {
		alias = json.getString("alias");
		if(StrUtils.isEmpty(alias)){
			throw new NullPointerException("with 子句的alias属性不能为空");
		}
		JSONArray resultsets = json.getJSONArray("resultSet");
		if(resultsets != null && resultsets.size() > 0){
			rsSize = resultsets.size();
			rs = new ArrayList<ResultSet>(rsSize);
			for(int i=0;i<rsSize;i++){
				rs.add(new ResultSetImpl(resultsets.getJSONObject(i)));
			}
		}
	}

	protected String processSqlStatement() {
		StringBuilder sb = new StringBuilder(150);
		sb.append(alias);
		if(rs != null){
			sb.append("(");
			for(int i=0;i<rsSize;i++){
				sb.append(rs.get(i).getSqlStatement());
				if(i < rsSize-1){
					sb.append(", ");
				}
			}
			sb.append(")");
		}
		sb.append(" as (").append(sqlStatement).append(")");
		return sb.toString();
	}

	public With setSqlStatement(StringBuilder sqlStatement) {
		this.sqlStatement = sqlStatement.toString();
		return this;
	}
}
