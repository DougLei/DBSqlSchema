package com.sql.impl.statement.select.model;

import java.io.Serializable;

import com.sql.statement.model.Basic;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class BasicImpl implements Basic, Serializable {
	private static final long serialVersionUID = -6943438862731893367L;
	
	private String mainTableAlias;//主表的别名
	protected String sqlStatement;
	
	public Basic setMainTableAlias(String mainTableAlias) {
		if(StrUtils.notEmpty(mainTableAlias)){
			this.mainTableAlias = mainTableAlias+".";
		}
		return this;
	}
	
	protected String getMainTableAlias(){
		if(StrUtils.isEmpty(mainTableAlias)){
			return "";
		}
		return mainTableAlias;
	}
	
	public String getSqlStatement() {
		if(sqlStatement == null){
			processSqlStatement();
		}
		return sqlStatement;
	}
	
	/**
	 * 处理sql语句
	 */
	protected abstract void processSqlStatement();
}
