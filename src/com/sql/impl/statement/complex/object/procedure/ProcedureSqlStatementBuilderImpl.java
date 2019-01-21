package com.sql.impl.statement.complex.object.procedure;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.complex.object.procedure.ProcedureSqlStatementBuilder;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder procedureSqlStatement = new StringBuilder(10000);
	
	protected String buildSql() {
		String procedureName = content.getString("procedureName");
		if(StrUtils.isEmpty(procedureName)){
			throw new NullPointerException("procedureName属性值不能为空");
		}
		
		boolean isCover = isCover();
		if(isCover){
			procedureSqlStatement.append(coverSqlServerSql(procedureName));
		}
		procedureSqlStatement.append("create ");
		if(isCover){
			procedureSqlStatement.append(coverOracleSql());
		}
		
		procedureSqlStatement.append("procedure ");
		procedureSqlStatement.append(newline());
		
		
		
		
		
		
		
		
		
		return procedureSqlStatement.toString();
	}
	
	public boolean isCover() {
		return content.getBoolean("isCover");
	}
}
