package com.sql.impl.statement.complex.object.datatype.tmptable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.datatype.DataType;
import com.sql.util.StrUtils;

/**
 * 
 * @author DougLei
 */
public class ORACLE_TMPTABLE extends AbstractCustomDataType{
	private ORACLE_TMPTABLE(){}
	private static final ORACLE_TMPTABLE tmptableDataType = new ORACLE_TMPTABLE();
	public static final ORACLE_TMPTABLE newInstance(){
		return tmptableDataType;
	}

	public String getAppendCustomSqlStatement(String name, JSONObject customJson) {
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(400);
			sb.append("execute immediate 'create global temporary table ").append(name).append("(").append(newline());
			appendColumnSql(array, sb);
			sb.append(")").append(newline());
			sb.append(OracleTmpTableLevel.toValue(customJson.getString("level")).getSqlStatement());
			sb.append("';");
			return sb.toString();
		}
		return null;
	}

	public boolean isSupportAppendCustomSqlStatement() {
		return true;
	}
	
	protected DataType getCustomDataType() {
		return DataType.TMP_TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		return null;
	}
	
	public boolean isSupportCreateType() {
		return false;
	}
	
	private enum OracleTmpTableLevel {
		SESSION("on commit preserve rows"),// 会话结束，临时表中的数据就会消失
		TRANSACTION("on commit delete rows");// 事务完成，临时表中的数据就会消失
		
		private String sqlStatement;
		private OracleTmpTableLevel(String sqlStatement) {
			this.sqlStatement = sqlStatement;
		}

		String getSqlStatement() {
			return sqlStatement;
		}
		
		static OracleTmpTableLevel toValue(String str){
			if(StrUtils.notEmpty(str)){
				str = str.trim().toUpperCase();
				for(OracleTmpTableLevel level : OracleTmpTableLevel.values()){
					if(level.name().equals(str)){
						return level;
					}
				}
			}
			return TRANSACTION;
		}
	}
}
