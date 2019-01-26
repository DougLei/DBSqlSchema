package com.sql.impl.statement.complex.object.procedure.datatype.tmptable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.impl.statement.complex.object.procedure.datatype.AbstractCustomDataType;
import com.sql.impl.statement.complex.object.procedure.datatype.DataType;
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

	public String getAppendCustomSqlStatement(JSONObject customJson) {
		return null;
	}

	public boolean isSupportAppendCustomSqlStatement() {
		return false;
	}
	
	protected DataType getCustomDataType() {
		return DataType.TMP_TABLE;
	}

	protected String getCreateTypeSql(JSONObject customJson) {
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(400);
			sb.append("create global temporary table ").append(customJson.getString("typeName")).append("(").append(newline());
			appendColumnSql(array, sb);
			sb.append(")").append(newline());
			sb.append(OracleTmpTableLevel.toValue(customJson.getString("level")).getSqlStatement());
			return sb.toString();
		}
		return null;
	}
	
	public boolean isSupportCreateType() {
		return true;
	}
	
	/**
	 * 
	 * @author DougLei
	 */
	private enum OracleTmpTableLevel {
		SESSION("on commit preserve rows"),
		TRANSACTION("on commit delete rows");
		
		private String sqlStatement;
		private OracleTmpTableLevel(String sqlStatement) {
			this.sqlStatement = sqlStatement;
		}

		public String getSqlStatement() {
			return sqlStatement;
		}
		
		public static OracleTmpTableLevel toValue(String str){
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
