package com.sql.impl.statement.complex.object.procedure.model.declare;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author DougLei
 */
public class DeclareVariableContext {
	/**
	 * 声明变量的sql语句，可能在存储过程体中也会声明变量，所以这里统一记录，最后统一放到存储过程delcare块中
	 */
	private static final ThreadLocal<List<DeclareEntity>> declareListLocal = new ThreadLocal<List<DeclareEntity>>();

	/**
	 * 是否包括declare代码块
	 * @return
	 */
	public static boolean includeDeclare() {
		List<DeclareEntity> local = declareListLocal.get();
		return local != null && local.size() > 0;
	}

	/**
	 * 记录declare
	 * @param declareEntity
	 */
	public static void recordDeclare(DeclareEntity declareEntity) {
		List<DeclareEntity> local = declareListLocal.get();
		if(local == null){
			local = new ArrayList<DeclareEntity>(20);
		}
		local.add(declareEntity);
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
			List<DeclareEntity> declareEntityList = declareListLocal.get();
			// TODO
			
			
			
			
			return null;
		}
		throw new NullPointerException("无法获取decalre的sql语句代码块");
	}
}
