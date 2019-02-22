package com.sql.impl.statement.complex.object.procedure.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sql.enums.DatabaseType;
import com.sql.impl.SqlStatementBuilderContext;

/**
 * 
 * @author DougLei
 */
public class CursorContext {
	private static final ThreadLocal<Map<String, Cursor>> cursorCache = new ThreadLocal<Map<String,Cursor>>();
	
	public static void recordCursor(String cursorName, List<String> variableNameList, String variableNameSql) {
		Map<String, Cursor> cc = cursorCache.get();
		if(cc == null){
			cc = new HashMap<String, Cursor>();
			cursorCache.set(cc);
		}else{
			if(cursorExists(cursorName)){
				return;
			}
		}
		cc.put(cursorName, new Cursor(cursorName, variableNameList, variableNameSql));
	}
	
	/**
	 * 获取对游标进行fetch操作的sql语句
	 * @param cursorName
	 */
	public static final String getFetchSql(String cursorName){
		if(cursorExists(cursorName)){
			Cursor cursor = cursorCache.get().get(cursorName);
			return getDBCursorOp().getCursorFetchSql(cursor);
		}
		throw new NullPointerException("不存在名为["+cursorName+"]的游标对象");
	}
	
	private static boolean cursorExists(String cursorName) {
		Map<String, Cursor> cc = cursorCache.get();
		if(cc != null){
			return cc.containsKey(cursorName);
		}
		return false;
	}

	private static ICursorOp getDBCursorOp() {
		DatabaseType dbType = SqlStatementBuilderContext.getDatabaseType();
		switch(dbType){
			case SQLSERVER:
				return new ICursorOp() {
					public String getCursorFetchSql(Cursor cursor) {
						return "fetch next from "+cursor.cursorName+" into " + cursor.variableNameSql;
					}
				};
			case ORACLE:
				return new ICursorOp() {
					public String getCursorFetchSql(Cursor cursor) {
						return "fetch "+cursor.cursorName+" into "+cursor.variableNameSql+";";
					}
				};
		}
		return null;
	}

	private interface ICursorOp{
		String getCursorFetchSql(Cursor cursor);
	}
}

class Cursor{
	public String cursorName;
	public List<String> variableNames;
	public String variableNameSql;
	
	public Cursor(String cursorName, List<String> variableNames, String variableNameSql){
		this.cursorName = cursorName;
		this.variableNames = variableNames;
		this.variableNameSql = variableNameSql;
	}
}
