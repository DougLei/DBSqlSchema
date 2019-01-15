package com.sql.impl.statement.basic.delete;

import java.util.List;
import com.sql.impl.statement.basic.AbstractSqlStatementBuilder;
import com.sql.statement.basic.delete.DeleteSqlStatementBuilder;
import com.sql.statement.basic.model.where.WhereGroup;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class DeleteSqlStatementBuilderImpl extends AbstractSqlStatementBuilder implements DeleteSqlStatementBuilder {
	protected StringBuilder deleteSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		deleteSqlStatement.append("delete from ");
		deleteSqlStatement.append(getTableName());
		
		// where
		List<WhereGroup> whereGroupList = getWhereGroupList();
		if(whereGroupList != null && whereGroupList.size() > 0){
			deleteSqlStatement.append(newline());
			deleteSqlStatement.append("where ");
			for (WhereGroup whereGroup : whereGroupList) {
				deleteSqlStatement.append(whereGroup.getSqlStatement());
			}
			deleteSqlStatement.append(newline());
		}
		return deleteSqlStatement.toString();
	}

	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
		String tableName = content.getString("tableName");
		if(StrUtils.isEmpty(tableName )){
			throw new NullPointerException("build delete sql时，tableName属性值不能为空");
		}
		return tableName;
	}
}
