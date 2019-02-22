package com.sql.impl.statement.complex.object.procedure.model.step.entity.return_;

import java.util.List;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionGroup;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ORACLE_RETURN extends LogicEntity{

	public ORACLE_RETURN(List<ConditionGroup> groupList) {
		super(groupList, true);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		if(StrUtils.notEmpty(sqlStatement)){
			throw new IllegalArgumentException("oracle存储过程中无法使用return，如果需要return 相关数据，请改用输出参数的形式");
		}
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(" then").append(newline());
			sb.append("begin").append(newline());
			sb.append("return;").append(newline());
			sb.append("end if;");
		}else{
			sb.append("return;");
		}
		sb.append(newline());
		return sb.toString();
	}
}
