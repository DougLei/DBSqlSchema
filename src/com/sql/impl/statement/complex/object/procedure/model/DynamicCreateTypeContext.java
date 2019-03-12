package com.sql.impl.statement.complex.object.procedure.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 在存储过程中，在begin后动态创建某些数据类型，然后再结尾end前，删除对应的数据类型
 * @author DougLei
 */
public class DynamicCreateTypeContext {
	private static final ThreadLocal<Map<String, DynamicCreateTypeEntity>> dynamicCreateTypeCache = new ThreadLocal<Map<String, DynamicCreateTypeEntity>>();

	/**
	 * 是否有动态创建类型
	 * @return
	 */
	public static boolean includeDynamicCreateType() {
		Map<String, DynamicCreateTypeEntity> dcte = dynamicCreateTypeCache.get();
		if(dcte != null && dcte.size() > 0){
			return true;
		}
		return false;
	}

	/**
	 * 获取执行动态创建sql语句
	 * @return
	 */
	public static String getDynamicCreateTypeSqlStatement() {
		Map<String, DynamicCreateTypeEntity> dcte = dynamicCreateTypeCache.get();
		Collection<DynamicCreateTypeEntity> values = dcte.values();

		StringBuilder sb = new StringBuilder(120*values.size());
		for (DynamicCreateTypeEntity dynamicCreateTypeEntity : values) {
			sb.append(dynamicCreateTypeEntity.getDynamicCreateTypeSqlStatement()).append('\n');
		}
		return sb.toString();
	}

	/**
	 * 获取执行动态drop sql语句
	 * @return
	 */
	public static String getDynamicDropTypeSqlStatement() {
		Map<String, DynamicCreateTypeEntity> dcte = dynamicCreateTypeCache.get();
		Collection<DynamicCreateTypeEntity> values = dcte.values();

		StringBuilder sb = new StringBuilder(60*values.size());
		for (DynamicCreateTypeEntity dynamicCreateTypeEntity : values) {
			sb.append(dynamicCreateTypeEntity.getDynamicDropTypeSqlStatement()).append('\n');
		}
		return sb.toString();
	}

	public static void addDynamicCreateType(String name, String dynamicCreateTypeName, String dynamicCreateTypeSqlStatement, String dynamicDropTypeSqlStatement) {
		Map<String, DynamicCreateTypeEntity> cache = null;
		if((cache = dynamicCreateTypeCache.get()) == null){
			cache = new HashMap<String, DynamicCreateTypeEntity>();
			dynamicCreateTypeCache.set(cache);
		}else{
			if(cache.containsKey(name)){
				throw new IllegalArgumentException("存储过程中， 出现同名的动态类型名：["+name+"]，请修改");
			}
		}
		cache.put(name, new DynamicCreateTypeEntity(dynamicCreateTypeName, dynamicCreateTypeSqlStatement, dynamicDropTypeSqlStatement));
	}
	
	/**
	 * 是否是动态创建类型
	 * @param name
	 */
	private static boolean isDynamicCreateType(String name){
		if(includeDynamicCreateType()){
			return dynamicCreateTypeCache.get().containsKey(name);
		}
		return false;
	}
	
	/**
	 * 获取动态创建类型的参数名
	 * @param name
	 * @return
	 */
	public static String getDynamicCreateTypeName(String name){
		if(isDynamicCreateType(name)){
			return dynamicCreateTypeCache.get().get(name).getDynamicCreateTypeName();
		}
		return name;
	}
}
