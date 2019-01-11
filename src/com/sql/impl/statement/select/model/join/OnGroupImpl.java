package com.sql.impl.statement.select.model.join;

import java.util.ArrayList;
import java.util.List;

import com.sql.impl.statement.select.model.BasicImpl;
import com.sql.statement.model.join.On;
import com.sql.statement.model.join.OnGroup;
import com.sql.statement.model.where.LogicOperatorType;

/**
 * 
 * @author DougLei
 */
public class OnGroupImpl extends BasicImpl implements OnGroup {

	// 记录on语句的组数数量，决定on(xxx)中的xx是否需要加()，如果有多组，会是这种on((xxx) and (xxx))效果，如果只有一组，会是这种on(xxx)效果
	private int onGroupCount = 1;
	private LogicOperatorType nextLogicOperator;
	private List<On> ons;
	
	public String getNextLogicOperator() {
		return nextLogicOperator.getSqlStatement();
	}

	protected String processSqlStatement() {
		if(ons != null && ons.size() > 0){
			StringBuilder sb = new StringBuilder(200);
			if(onGroupCount > 1){
				sb.append("(");
			}
			for(int i=0;i<ons.size();i++){
				sb.append(ons.get(i).getSqlStatement());
				if(i<ons.size()-1){
					sb.append(" ").append(ons.get(i).getNextLogicOperator()).append(" ");
				}
			}
			if(onGroupCount > 1){
				sb.append(")");
			}
		}
		return null;
	}

	public void addOn(On on) {
		if(ons == null){
			ons = new ArrayList<On>();
		}
		ons.add(on);
	}
	
	public void setNextLogicOperator(String nextLogicOperator) {
		this.nextLogicOperator = LogicOperatorType.toValue(nextLogicOperator);
	}
	public void setOnGroupCount(int onGroupCount) {
		if(onGroupCount < 1){
			onGroupCount = 1;
		}
		this.onGroupCount = onGroupCount;
	}
}
