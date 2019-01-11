package com.sql.enums;

import java.util.Arrays;

/**
 * 编码格式
 * @author DougLei
 */
public enum Encoding {
	
	ISO8859_1("iso8859-1"),
	UTF_8("utf-8"),
	GBK("gbk"),
	GB2312("gb2312");
	
	private String encoding;
	private Encoding(String encoding) {
		this.encoding = encoding;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public static Encoding toValue(String str){
		try {
			return Encoding.valueOf(str.trim().toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("值[\""+str+"\"]错误，目前支持的值包括：["+Arrays.toString(Encoding.values())+"]");
		}
	}
}
