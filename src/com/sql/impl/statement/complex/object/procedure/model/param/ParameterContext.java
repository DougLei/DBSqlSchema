package com.sql.impl.statement.complex.object.procedure.model.param;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.CreateTypeContext;

/**
 * 
 * @author DougLei
 */
public class ParameterContext {
	/**
	 * 参数的处理，对于oracle数据库时，要将inout的declare数据，放到parameter中
	 * 
	 * sqlserver				oracle
	 * declare时可以有inout		declare不能有inout
	 */
	private static final ThreadLocal<Map<String, ParameterEntity>> parameterListLocal = new ThreadLocal<Map<String, ParameterEntity>>();

	/**
	 * 是否包括parameter代码块
	 * @return
	 */
	public static boolean includeParameter() {
		Map<String, ParameterEntity> local = parameterListLocal.get();
		return local != null && local.size() > 0;
	}

	/**
	 * 记录parameter
	 * @param parameterJson
	 * @return 
	 */
	public static ParameterEntity recordParameter(JSONObject parameterJson) {
		Map<String, ParameterEntity> local = parameterListLocal.get();
		
		ParameterEntity parameter = new ParameterEntity(parameterJson);
		if(!parameter.isBaseType()){
			parameter.setCustomJson(parameterJson.getJSONObject("custom"));
		}
		
		if(local == null){
			local = new HashMap<String, ParameterEntity>(20);
			parameterListLocal.set(local);
		}else{
			if(local.containsKey(parameter.getName())){
				throw new IllegalArgumentException("存储过程中parameter 同名的参数名：["+parameter.getName()+"]，请修改");
			}
		}
		local.put(parameter.getName(), parameter);
		
		if(parameter.isCreateType()){
			CreateTypeContext.recordCreateTypeSqlStatement(parameter.getCreateTypeSqlStatement());
		}
		return parameter;
	}
	
	/**
	 * 获取最终的parameter sql语句
	 * @return
	 */
	public static String getParameterSqlStatement() {
		if(includeParameter()){
			Collection<ParameterEntity> parameterEntityList = parameterListLocal.get().values();
			int size = parameterEntityList.size();
			
			StringBuilder parameterSql = new StringBuilder(size * 50);
			DBParameter dbparameter = getDBParameter();
			
			int i=0, loopCount = size-1;
			for (ParameterEntity parameterEntity : parameterEntityList) {
				parameterSql.append(dbparameter.toParameterSqlStatement(i==0, i==loopCount, parameterEntity));
				if(i<loopCount){
					parameterSql.append(",");
					i++;
				}
				parameterSql.append("\n");
			}
			clear();
			return parameterSql.toString();
		}
		throw new NullPointerException("无法获取parameter的sql语句代码块");
	}

	private static void clear() {
		Map<String, ParameterEntity> map = parameterListLocal.get();
		if(map != null && map.size() > 0){
			map.values().clear();
			map.clear();
		}
	}

	private static DBParameter getDBParameter() {
		switch(SqlStatementBuilderContext.getDatabaseType()){
			case SQLSERVER:
				return new SQLSERVER_Parameter();
			case ORACLE:
				return new ORACLE_Parameter();
		}
		return null;
	}
}
