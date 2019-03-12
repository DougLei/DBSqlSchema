package com.sql.impl.statement.complex.object.datatype.tmptable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

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
		return null;
	}

	public boolean isSupportAppendCustom() {
		return false;
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
	
	// ------------------------------------------------------------------
	public boolean isSupportDynamicCreateType() {
		return true;
	}
	
	private transient static final SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private String dynamicCreateTypeName; 
	private void setDynamicCreateTypeName(String name){
		if(dynamicCreateTypeName == null){
			dynamicCreateTypeName = extractDbObjName(name + "_" + sdfSimple.format(new Date()) + "_" + ThreadLocalRandom.current().nextInt(1000));
		}
	}
	/**
	 * 提取数据库对象名称
	 * <p>保证名称在30个字符内，最多为30个字符</p>
	 * <p>第一个下划线前的所有字母加_，再加后续每个单词的首字母，然后加_，加后续每个单词的尾字母，最后加_，再加名称的总数量，均大写</p>
	 * <p>例如: SYS_USER_LINE，提取结果为SYS_UL_RE_13</p>
	 * @param dbObjectName
	 * @return
	 */
	private String extractDbObjName(String dbObjectName){
		if(StrUtils.isEmpty(dbObjectName)){
			return null;
		}
		if(dbObjectName.length() <= 30){
			return dbObjectName;
		}
		
		StringBuilder sb = new StringBuilder();
		StringBuilder suffix = new StringBuilder();
		
		String[] nameArr = dbObjectName.split("_");
		int length = nameArr.length;
		for(int i=0;i<length;i++){
			if(i == 0){
				sb.append(nameArr[i]).append("_");
			}else{
				if(StrUtils.notEmpty(nameArr[i])){
					sb.append(nameArr[i].substring(0, 1));
					suffix.append(nameArr[i].substring(nameArr[i].length()-1));
				}
			}
		}
		sb.append("_").append(suffix).append("_").append(dbObjectName.length());
		suffix.setLength(0);
		
		String resultName = sb.toString();
		sb.setLength(0);
		
		if(resultName.length() > 30){
			throw new IllegalArgumentException("提取数据库对象名称时，传入的名称为["+dbObjectName+"]，提取名称为["+resultName+"]，提取名称长度超过30个字符，请联系后端系统开发人员");
		}
		return resultName.toUpperCase();
	}

	public String getDynamicCreateTypeSqlStatement(String name, JSONObject customJson) {
		setDynamicCreateTypeName(name);
		JSONArray array = customJson.getJSONArray("column");
		if(array != null && array.size()>0){
			StringBuilder sb = new StringBuilder(400);
			sb.append("execute immediate 'create global temporary table ").append(dynamicCreateTypeName).append("(").append(newline());
			appendColumnSql(array, sb);
			sb.append(")").append(newline());
			sb.append(OracleTmpTableLevel.toValue(customJson.getString("level")).getSqlStatement()).append("';");
			return sb.toString();
		}
		return null;
	}

	public String getDynamicDropTypeSqlStatement(String name, JSONObject customJson) {
		setDynamicCreateTypeName(name);
		StringBuilder sb = new StringBuilder(100);
		sb.append("execute immediate 'truncate table ").append(dynamicCreateTypeName).append("';");
		sb.append(newline());
		sb.append("execute immediate 'drop table ").append(dynamicCreateTypeName).append("';");
		return sb.toString();
	}

	public String getDynamicCreateTypeName(String name) {
		setDynamicCreateTypeName(name);
		return dynamicCreateTypeName;
	}
}
