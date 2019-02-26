package com.sql.impl.statement.complex.object.procedure.model.step.entity.setvalue;

import java.util.List;

import com.sql.statement.basic.select.SelectSqlStatementBuilder;
import com.sql.statement.complex.select.CombinationSelectSqlStatementBuilder;


/**
 * 
 * @author DougLei
 */
public class ORACLE_SetValueEntity extends SetValueEntity {

	protected String getVALUESqlStatement() {
		return paramName[0] +":=" + value;
	}

	protected String getFUNCTIONSqlStatement() {
		return paramName[0] +":=" + valueFunction.getSqlStatement();
	}

	protected String getSIMPLE_SELECT_SQLSqlStatement(SelectSqlStatementBuilder builder) {
		StringBuilder sb = new StringBuilder(builder.getBody().length() + builder.getResultSetColumnNames().size() * 50);
		sb.append("select \n");
		
		List<String> columnNames = builder.getResultSetColumnNames();
		int flag = columnNames.size()-1;
		for (int i=0;i<columnNames.size();i++) {
			if(i<paramName.length){
				sb.append(paramName[i]).append(" :=");
			}
			sb.append(columnNames.get(i));
			if(i<flag){
				sb.append(", ");
			}
		}
		return sb.append(builder.getBody()).toString();
	}

	protected String getCOMBINATION_SELECT_SQLSqlStatement(CombinationSelectSqlStatementBuilder builder) {
		
		return null;
	}
}
