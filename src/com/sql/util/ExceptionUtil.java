package com.sql.util;


/**
 * 异常操作工具类
 * @author DougLei
 */
public class ExceptionUtil {
	
	/**
	 * 获取异常信息
	 * @param exception
	 * @return
	 */
	public static String getErrMsg(Exception exception) {
		exception.printStackTrace();
		if(exception.getCause() != null){
			return exception.getCause().getMessage();
		}
		return exception.getMessage();
	}
}
