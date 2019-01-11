package com.sql.statement.model.join;

/**
 * join的类型
 * @author DougLei
 */
public enum JoinType {
	INNER_JOIN("inner join"),
	LEFT_JOIN("left join"),
	RIGHT_JOIN("right join"),
	FULL_JOIN("full join");
	
	private String sqlStatement;
	private JoinType(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}
	
	public String getSqlStatement() {
		return sqlStatement;
	}
	public String toString() {
		return sqlStatement;
	}
}