package com.sql.impl.statement.complex.object.procedure.model.step.entity;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.statement.complex.object.procedure.model.step.entity.StepEntity;

/**
 * 
 * @author DougLei
 */
public abstract class StepEntityImpl implements StepEntity{
	private String sqlStatement;
	private boolean flag;
	
	public String getSqlStatement() {
		if(!flag && sqlStatement == null){
			sqlStatement = processSqlStatement();
			flag = true;
		}
		if(sqlStatement != null && sqlStatement.trim().length() == 0){
			sqlStatement = null;
		}
		return sqlStatement;
	}
	
	private String processSqlStatement(){
		DatabaseType databaseType = SqlStatementBuilderContext.getDatabaseType();
		switch(databaseType){
			case ORACLE:
				return processSqlStatement_ORACLE();
			case SQLSERVER:
				return processSqlStatement_SQLSERVER();
		}
		throw new IllegalArgumentException("在buil procedure时，处理step属性时出现异常: StepEntity没有匹配到对应的数据库");
	}
	
	/**
	 * 处理SQLSERVER sql语句
	 * 如果没有就返回null
	 * @return
	 */
	protected abstract String processSqlStatement_SQLSERVER();
	
	/**
	 * 处理ORACLE sql语句
	 * 如果没有就返回null
	 * @return
	 */
	protected abstract String processSqlStatement_ORACLE();
}
