package com.sql.impl.statement.complex.object.datatype;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author DougLei
 */
public class CustomDataTypeContext {
	/**
	 * 自定义的数据类型
	 */
	private static final ThreadLocal<Map<String, List<String>>> customDataTypeCache = new ThreadLocal<Map<String,List<String>>>();
	
	public static void clear(){
		Map<String, List<String>> cache = null;
		if((cache = customDataTypeCache.get()) != null && cache.size() > 0){
			Collection<List<String>> cl = cache.values();
			for (List<String> list : cl) {
				if(list != null && list.size() > 0){
					list.clear();
				}
			}
			cache.clear();
		}
	}
	
	/**
	 * 自定义的数据类型是否存在
	 * @param dataType 类型
	 * @param dataTypeName 类型名称
	 * @return
	 */
	public static boolean customDataTypeIsExists(DataType dataType, String dataTypeName){
		Map<String, List<String>> cache = customDataTypeCache.get();
		if(cache == null){
			cache = new HashMap<String, List<String>>();
			customDataTypeCache.set(cache);
		}
		
		List<String> list = cache.get(dataType.name());
		if(list == null){
			list = new ArrayList<String>();
			cache.put(dataType.name(), list);
		}
		
		if(list.contains(dataTypeName)){
			return true;
		}
		list.add(dataTypeName);
		return false;
	}
}
