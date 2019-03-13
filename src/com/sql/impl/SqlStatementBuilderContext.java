package com.sql.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sql.SqlStatementBuilder;
import com.sql.SqlStatementInfoBuilder;
import com.sql.enums.DatabaseType;
import com.sql.impl.statement.complex.object.datatype.CustomDataTypeContext;
import com.sql.impl.statement.complex.object.procedure.model.step.StepContext;
import com.sql.util.ReflectUtil;
import com.sql.util.StrUtils;

/**
 * sql语句builder的上下文
 * @author DougLei
 */
public class SqlStatementBuilderContext {
	private static final ThreadLocal<JSONArray> localConfJsonRefContent = new ThreadLocal<JSONArray>();
	private static final ThreadLocal<DatabaseType> localDatabaseType = new ThreadLocal<DatabaseType>();
	private static final ThreadLocal<Map<String, SqlStatementBuilder>> localBuilder = new ThreadLocal<Map<String,SqlStatementBuilder>>();
	
	public static void setDatabaseType(DatabaseType databaseType){
		if(databaseType != null){
			if(localDatabaseType.get() == null){
				localDatabaseType.set(databaseType);
			}else if(localDatabaseType.get() != databaseType){
				throw new IllegalArgumentException("当前处理的是["+localDatabaseType.get()+"]数据库类型，无法同时处理["+databaseType+"]数据库类型");
			}
		}
	}
	
	public static DatabaseType getDatabaseType(){
		return localDatabaseType.get();
	}
	
	/**
	 * 存储整个配置文件引用内容对象集合
	 * @param jsonarray
	 */
	public static void setConfJsonRefContent(JSONArray jsonarray){
		if(jsonarray != null && jsonarray.size() > 0){
			if(localConfJsonRefContent.get() == null){
				localConfJsonRefContent.set(jsonarray);
			}else{
				localConfJsonRefContent.get().addAll(jsonarray);
			}
		}
	}
	
	public static void setSqlStatementBuilder(SqlStatementBuilder builder){
		Map<String, SqlStatementBuilder> builderMap = localBuilder.get();
		if(builderMap == null){
			builderMap = new HashMap<String, SqlStatementBuilder>();
		}else{
			if(builderMap.containsKey(builder.getId())){
				throw new IllegalArgumentException("配置文件中出现了相同的id值["+builder.getId()+"]，请检查并修改");
			}
		}
		builderMap.put(builder.getId(), builder);
		localBuilder.set(builderMap);
	}
	
	public static SqlStatementBuilder getSqlStatementBuilder(String builderId){
		if(StrUtils.isEmpty(builderId)){
			throw new NullPointerException("SqlStatementBuilderContext.getSqlStatementBuilder方法的参数(builderId)值不能为空");
		}
		Map<String, SqlStatementBuilder> builderMap = localBuilder.get();
		if(builderMap == null){
			builderMap = new HashMap<String, SqlStatementBuilder>();
		}
		SqlStatementBuilder builder = builderMap.get(builderId);
		if(builder == null){
			JSONArray array = localConfJsonRefContent.get();
			if(array != null && array.size() > 0){
				JSONObject json = null;
				for(int i=0;i<array.size();i++){
					json = array.getJSONObject(i);
					if(StrUtils.isEmpty(json.get("id"))){
						json.put("id", StrUtils.getIdentity());
					}
					if(builderId.equals(json.getString("id"))){
						SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
						infoBuilder.setJson(json);
						builder = infoBuilder.createSqlStatementBuilder();
						break;
					}
				}
			}
		}
		if(builder == null){
			throw new NullPointerException("不存在id为["+builderId+"]的配置信息");
		}
		return builder;
	}
	
	public static String buildSqlStatement(String builderId){
		return getSqlStatementBuilder(builderId).buildSqlStatement();
	}
	
	public static void clear(){
		Map<String, SqlStatementBuilder> builders = localBuilder.get();
		if(builders != null && builders.size() > 0){
			Collection<SqlStatementBuilder> bs = builders.values();
			for (SqlStatementBuilder sqlStatementBuilder : bs) {
				if(sqlStatementBuilder != null){
					sqlStatementBuilder.clear();
				}
			}
			builders.clear();
		}
		
		JSONArray confJsonRefContent = localConfJsonRefContent.get();
		JSONObject json = null;
		if(confJsonRefContent != null && confJsonRefContent.size() > 0){
			for(int i=0;i<confJsonRefContent.size();i++){
				json = confJsonRefContent.getJSONObject(i);
				if(json != null && json.size() > 0){
					json.clear();
				}
			}
			confJsonRefContent.clear();
		}
		
		CustomDataTypeContext.clear();
		StepContext.clear();
	}
	
	// ---------------------------------------------------------------------
	/**
	 * 获取各个数据库实现类的实例
	 * @param dbImplInstancePackage
	 * @param dbImplInstancePackageSuffix
	 * @return
	 */
	public static Object getDBImplInstance(String dbImplInstancePackage, String dbImplInstancePackageSuffix){
		DatabaseType dbType = getDatabaseType();
		String dbImplInstance = dbImplInstancePackage + ".db." + dbType.getDatabaseTypeUpperCase() + (StrUtils.isEmpty(dbImplInstancePackageSuffix)?"":("_" + dbImplInstancePackageSuffix));
		return ReflectUtil.newInstance(dbImplInstance);
	}
	
	// ---------------------------------------------------------------------
	/**
	 * 获取sql语句builder实例
	 * @param sqlId
	 * @param sqlJson
	 * @return
	 */
	public static SqlStatementBuilder getSqlStatementBuilder(String sqlId, JSONObject sqlJson){
		if(StrUtils.notEmpty(sqlId)){
			return SqlStatementBuilderContext.getSqlStatementBuilder(sqlId);
		}else{
			SqlStatementInfoBuilder infoBuilder = new SqlStatementInfoBuilderImpl();
			infoBuilder.setJson(sqlJson);
			return infoBuilder.createSqlStatementBuilder();
		}
	}
	
	/**
	 * 获取sql语句
	 * @param sqlId
	 * @param sqlJson
	 * @return
	 */
	public static String getSqlStatement(String sqlId, JSONObject sqlJson){
		return getSqlStatementBuilder(sqlId, sqlJson).buildSqlStatement();
	}
}
