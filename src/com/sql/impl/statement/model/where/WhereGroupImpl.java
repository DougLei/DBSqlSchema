package com.sql.impl.statement.model.where;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.statement.model.BasicImpl;
import com.sql.statement.model.where.LogicOperatorType;
import com.sql.statement.model.where.Where;
import com.sql.statement.model.where.WhereGroup;

public class WhereGroupImpl extends BasicImpl implements WhereGroup {

	// 记录where语句的组数数量，决定where xxx 中的xx是否需要加()，如果有多组，会是这种where (xxx) and (xxx)效果，如果只有一组，会是这种where xxx效果
	private int whereGroupCount = 1;
	private LogicOperatorType nextLogicOperator;
	private List<Where> wheres;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		if(wheres != null && wheres.size() > 0){
			StringBuilder sb = new StringBuilder(200);
			if(whereGroupCount > 1){
				sb.append("(");
			}
			for(int i=0;i<wheres.size();i++){
				sb.append(wheres.get(i).getSqlStatement());
				if(i<wheres.size()-1){
					sb.append(" ").append(wheres.get(i).getNextLogicOperator()).append(" ");
				}
			}
			if(whereGroupCount > 1){
				sb.append(")");
			}
			return sb.toString();
		}
		return null;
	}

	public void addWhere(Where where) {
		if(wheres == null){
			wheres = new ArrayList<Where>();
		}
		wheres.add(where);
	}
	
	public void setNextLogicOperator(String nextLogicOperator) {
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
	public void setWhereGroupCount(int whereGroupCount) {
		if(whereGroupCount < 1){
			whereGroupCount = 1;
		}
		this.whereGroupCount = whereGroupCount;
	}
}
