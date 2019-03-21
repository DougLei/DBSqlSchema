package com.sql.impl.statement.basic.delete;

import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.statement.basic.delete.DeleteSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class DeleteSqlStatementBuilderImpl extends AbstractSqlStatementBuilder implements DeleteSqlStatementBuilder {
	protected StringBuilder deleteSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		deleteSqlStatement.append("delete from ");
		deleteSqlStatement.append(getTable());
		
		// where
		deleteSqlStatement.append(getWhereSqlStatement());
		
		return deleteSqlStatement.toString();
	}

	/**
	 * 获取表名
	 * @return
	 */
	public String getTable() {
		String tableName = content.getString("name");
		if(StrUtils.isEmpty(tableName )){
			throw new NullPointerException("build delete sql时，name属性值不能为空");
		}
		return tableName;
	}
}
