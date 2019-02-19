package com.sql.impl.statement.complex.object.procedure.model.declare;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;
import com.sql.impl.statement.complex.object.procedure.model.declare.db.DBDeclare;
import com.sql.impl.statement.complex.object.procedure.model.declare.db.ORACLE_Declare;
import com.sql.impl.statement.complex.object.procedure.model.declare.db.SQLSERVER_Declare;


/**
 * 
 * @author DougLei
 */
public class DeclareVariableContext {
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
	 * @param declareEntity
	 */
	public static void recordDeclare(DeclareEntity declareEntity) {
		Map<String, DeclareEntity> local = declareListLocal.get();
		if(local == null){
			local = new HashMap<String, DeclareEntity>(20);
		}else{
			if(local.containsKey(declareEntity.getName())){
				throw new IllegalArgumentException("存储过程中declare 同名的变量名：["+declareEntity.getName()+"]，请修改");
			}
		}
		local.put(declareEntity.getName(), declareEntity);
	}
	
	/**
	 * 记录declare集合
	 * @param declareList
	 */
	public static void recordDeclare(List<DeclareEntity> declareList) {
		if(declareList != null && declareList.size() > 0){
			for (DeclareEntity declareEntity : declareList) {
				recordDeclare(declareEntity);
			}
		}
	}
	
	/**
	 * 获取最终的declare sql语句
	 * @return
	 */
	public static String getDeclareVariableSqlStatement() {
		if(includeDeclare()){
			Collection<DeclareEntity> declareEntityList = declareListLocal.get().values();
			StringBuilder declareSql = new StringBuilder(declareEntityList.size() * 50);
			DBDeclare dbdeclare = getDBDeclare();
			for (DeclareEntity declareEntity : declareEntityList) {
				declareSql.append(dbdeclare.toDeclareSqlStatement(declareEntity)).append('\n');
			}
			declareEntityList.clear();
			return declareSql.toString();
		}
		throw new NullPointerException("无法获取declare的sql语句代码块");
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
