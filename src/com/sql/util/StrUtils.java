package com.sql.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.sql.enums.Encoding;

/**
 * 字符串工具类
 * @author DougLei
 */
public class StrUtils {
	
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		if(object == null || "".equals(object.toString().trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否不为空
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str){
		if(str != null && !"".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否不为空
	 * @param object
	 * @return
	 */
	public static boolean notEmpty(Object object){
		if(object != null && !"".equals(object.toString().trim())){
			return true;
		}
		return false;
	}
	
	// --------------------------------------------------------------------------------------------------
	/**
	 * 获取唯一标识id
	 * @return
	 */
	public static String getIdentity(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	// --------------------------------------------------------------------------------------------------
	/**
	 * 根据byte数组，获取字符串内容
	 * <p>默认为utf-8编码格式</p>
	 * @param byteArray
	 * @return
	 */
	public static String getStringByByteArray(byte[] byteArray){
		return getStringByByteArray(byteArray, Encoding.UTF_8);
	}
	
	/**
	 * 根据byte数组，获取字符串内容
	 * @param byteArray
	 * @param encoding
	 * @return
	 */
	public static String getStringByByteArray(byte[] byteArray, Encoding encoding){
		if(byteArray != null && byteArray.length > 0 && encoding != null){
			try {
				return new String(byteArray, encoding.getEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException("在将byte数组转换成"+encoding+"编码格式的字符串时出现异常：" + ExceptionUtil.getErrMsg(e));
			}
		}
		return null;
	}
}
