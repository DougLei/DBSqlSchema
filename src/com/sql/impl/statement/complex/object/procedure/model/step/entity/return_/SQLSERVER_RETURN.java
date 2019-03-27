package com.sql.impl.statement.complex.object.procedure.model.step.entity.return_;

import com.sql.impl.statement.complex.object.procedure.model.step.entity.LogicEntity;
import com.sql.impl.statement.complex.object.procedure.model.step.entity.condition.ConditionEntity;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class SQLSERVER_RETURN extends LogicEntity{

	public SQLSERVER_RETURN(ConditionEntity condition) {
		super(condition, false);
	}

	public String getSqlStatement(boolean isEnd, String sqlStatement) {
		if(StrUtils.notEmpty(sqlStatement)){
			throw new IllegalArgumentException("系统不支持sqlserver存储过程return数据，如果需要return 相关数据，请改用输出参数的形式");
		}
		
		StringBuilder sb = buildSqlStringBuilder(sqlStatement);
		if(conditionSqlStatement.length() > 0){
			sb.append("if ").append(conditionSqlStatement).append(newline());
			sb.append("begin").append(newline());
			sb.append("return").append(newline());
			sb.append("end");
		}else{
			sb.append("return");
		}
		sb.append(newline());
		return sb.toString();
	}
}
