package com.sql.impl.statement.complex.object.procedure.model.declare;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.CreateTypeContext;


/**
 * 
 * @author DougLei
 */
public class DeclareContext {
	/**
	 * 声明变量的sql语句，可能在存储过程体中也会声明变量，所以这里统一记录，最后统一放到存储过程delcare块中
	 */
	private static final ThreadLocal<Map<String, DeclareEntity>> declareListLocal = new ThreadLocal<Map<String, DeclareEntity>>();

	/**
	 * 是否包括declare代码块
	 * @return
	 */
	public static boolean includeDeclare() {
		Map<String, DeclareEntity> local = declareListLocal.get();
		return local != null && local.size() > 0;
	}

	/**
	 * 记录declare
	 * @param declareJson
	 * @return 
	 */
	public static DeclareEntity recordDeclare(JSONObject declareJson) {
		Map<String, DeclareEntity> local = declareListLocal.get();
		
		DeclareEntity declareEntity = new DeclareEntity(declareJson);
		if(local == null){
			local = new HashMap<String, DeclareEntity>(20);
		}else{
			if(local.containsKey(declareEntity.getName())){
				throw new IllegalArgumentException("存储过程中declare 同名的变量名：["+declareEntity.getName()+"]，请修改");
			}
		}
		local.put(declareEntity.getName(), declareEntity);
		
		if(declareEntity.isCreateType()){
			CreateTypeContext.recordCreateTypeSqlStatement(declareEntity.getCreateTypeSqlStatement());
		}
		return declareEntity;
	}
	
	/**
	 * 获取最终的declare sql语句
	 * @return
	 */
	public static String getDeclareSqlStatement() {
		if(includeDeclare()){
			Collection<DeclareEntity> declareEntityList = declareListLocal.get().values();
			StringBuilder declareSql = new StringBuilder(declareEntityList.size() * 50);
			DBDeclare dbdeclare = getDBDeclare();
			for (DeclareEntity declareEntity : declareEntityList) {
				declareSql.append(dbdeclare.toDeclareSqlStatement(declareEntity)).append('\n');
			}
			clear();
			return declareSql.toString();
		}
		throw new NullPointerException("无法获取declare的sql语句代码块");
	}

	private static void clear() {
		Map<String, DeclareEntity> map = declareListLocal.get();
		if(map != null && map.size() > 0){
			map.values().clear();
			map.clear();
		}
	}

	private static DBDeclare getDBDeclare() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new SQLSERVER_Declare();
			case ORACLE:
				return new ORACLE_Declare();
		}
		return null;
	}
}
