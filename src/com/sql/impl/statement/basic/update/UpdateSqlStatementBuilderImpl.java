package com.sql.impl.statement.basic.update;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.basic.update.UpdateSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class UpdateSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements UpdateSqlStatementBuilder {
	protected StringBuilder updateSqlStatement = new StringBuilder(3000);
	
	protected String buildSql() {
		updateSqlStatement.append("insert into ");
		updateSqlStatement.append(getTableName());
		
//		List<String> columnNames = getColumnNames();
//		if(columnNames != null && columnNames.size() > 0){
//			insertSqlStatement.append("(");
//			for(int i=0;i<columnNames.size();i++){
//				insertSqlStatement.append(columnNames.get(i));
//				if(i<columnNames.size()-1){
//					insertSqlStatement.append(", ");
//				}
//			}
//			insertSqlStatement.append(")");
//		}
//		insertSqlStatement.append(newline());
//		insertSqlStatement.append("values ");
//		insertSqlStatement.append(newline());
//		
//		Values values = getValues();
//		insertSqlStatement.append(values.getSqlStatement());
		
		return updateSqlStatement.toString();
	}

	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
		String tableName = content.getString("tableName");
		if(StrUtils.isEmpty(tableName )){
			throw new NullPointerException("build update sql时，tableName属性值不能为空");
		}
		return tableName;
	}
	
}
