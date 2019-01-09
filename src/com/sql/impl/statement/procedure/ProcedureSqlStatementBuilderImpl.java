package com.sql.impl.statement.procedure;

import com.sql.impl.SqlStatementBuilderImpl;
import com.sql.statement.procedure.ProcedureSqlStatementBuilder;

/**
 * procedure sql语句builder 抽象父类
 * @author DougLei
 */
public abstract class ProcedureSqlStatementBuilderImpl extends SqlStatementBuilderImpl implements ProcedureSqlStatementBuilder {
	protected StringBuilder procedure = new StringBuilder(10000);
	
	public boolean isCover(){
		if(json.get("isCover") == null){
			return true;
		}
		return json.getBooleanValue("isCover");
	}
	
	protected String buildSql() {
		if(isCover()){
			procedure.append(buildCoverProcedureSql()).append(newLine());
		}
		procedure.append("create procdure ").append(getProcedureName()).append(newLine());
		procedure.append(buildParameterSql()).append(newLine());
		procedure.append("as ").append(newLine());
		procedure.append("begin ").append(newLine());
		procedure.append(buildBodySql());
		procedure.append("end");
		return procedure.toString();
	}
}
